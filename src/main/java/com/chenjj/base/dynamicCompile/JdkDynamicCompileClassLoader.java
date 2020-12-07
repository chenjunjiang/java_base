package com.chenjj.base.dynamicCompile;

import javax.tools.JavaFileObject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 只要简单继承ClassLoader即可，关键是要覆盖原来的ClassLoader#findClass()方法，用于搜索自定义的
 * JavaFileObject实例，从而提取对应的字节码字节数组进行装载，为了实现这一点可以添加一个哈希表作为缓存，
 * 键-值分别是全类名的别名（xx.yy.MyClass形式，而非URI模式）和目标类对应的JavaFileObject实例。
 */
public class JdkDynamicCompileClassLoader extends ClassLoader {
    public static final String CLASS_EXTENSION = ".class";
    private final Map<String, JavaFileObject> javaFileObjectMap = new ConcurrentHashMap<>();

    public JdkDynamicCompileClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        JavaFileObject javaFileObject = javaFileObjectMap.get(name);
        if (javaFileObject == null) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        CharSequenceJavaFileObject charSequenceJavaFileObject = (CharSequenceJavaFileObject) javaFileObject;
        byte[] byteCode = charSequenceJavaFileObject.getByteCode();
        return this.defineClass(name, byteCode, 0, byteCode.length);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        if (name.endsWith(CLASS_EXTENSION)) {
            String qualifiedClassName = name.substring(0, name.length() - CLASS_EXTENSION.length()).replace("/", ".");
            CharSequenceJavaFileObject charSequenceJavaFileObject = (CharSequenceJavaFileObject) javaFileObjectMap.get(qualifiedClassName);
            if (charSequenceJavaFileObject != null && charSequenceJavaFileObject.getByteCode() != null) {
                return new ByteArrayInputStream(charSequenceJavaFileObject.getByteCode());
            }
        }
        return super.getResourceAsStream(name);
    }

    void addJavaFileObject(String qualifiedClassName, JavaFileObject javaFileObject) {
        javaFileObjectMap.put(qualifiedClassName, javaFileObject);
    }

    Collection<JavaFileObject> listJavaFileObject() {
        return Collections.unmodifiableCollection(javaFileObjectMap.values());
    }
}
