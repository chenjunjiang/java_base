package com.chenjj.base.dynamicCompile;

import javax.tools.SimpleJavaFileObject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 内置的JavaFileObject标准实现SimpleJavaFileObject是面向类源码文件，由于我们的例子动态编译时候输入的是
 * 类源码文件的内容字符串，所以需要自行实现。
 */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {
    public static final String CLASS_EXTENSION = ".class";

    public static final String JAVA_EXTENSION = ".java";

    private static URI fromClassName(String className) {
        try {
            return new URI(className);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(className, e);
        }
    }

    private ByteArrayOutputStream byteCode;
    private final CharSequence sourceCode;

    public CharSequenceJavaFileObject(String className, CharSequence sourceCode) {
        super(fromClassName(className + JAVA_EXTENSION), Kind.SOURCE);
        this.sourceCode = sourceCode;
    }

    public CharSequenceJavaFileObject(String fullClassName, Kind kind) {
        super(fromClassName(fullClassName), kind);
        this.sourceCode = null;
    }

    public CharSequenceJavaFileObject(URI uri, Kind kind) {
        super(uri, kind);
        this.sourceCode = null;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return sourceCode;
    }

    @Override
    public InputStream openInputStream() {
        return new ByteArrayInputStream(getByteCode());
    }

    // 注意这个方法是编译结果回调的OutputStream，回调成功后就能通过下面的getByteCode()方法获取目标类编译后的字节码字节数组
    @Override
    public OutputStream openOutputStream() {
        return byteCode = new ByteArrayOutputStream();
    }

    public byte[] getByteCode() {
        return byteCode.toByteArray();
    }
}