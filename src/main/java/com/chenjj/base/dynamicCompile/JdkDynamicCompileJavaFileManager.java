package com.chenjj.base.dynamicCompile;

import javax.tools.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JavaFileManager是Java文件的抽象管理器，它用于管理常规的Java文件，但是不局限于文件，也可以管理其他来源
 * 的Java类文件数据。
 * 内置的JavaFileManager是面向类路径下的Java源码文件进行加载，这里也需要自行实现JavaFileManager。
 */
public class JdkDynamicCompileJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private final JdkDynamicCompileClassLoader classLoader;
    private final Map<URI, JavaFileObject> javaFileObjectMap = new ConcurrentHashMap<>();

    public JdkDynamicCompileJavaFileManager(JavaFileManager fileManager, JdkDynamicCompileClassLoader classLoader) {
        super(fileManager);
        this.classLoader = classLoader;
    }

    private static URI fromLocation(Location location, String packageName, String relativeName) {
        try {
            return new URI(location.getName() + '/' + packageName + '/' + relativeName);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        JavaFileObject javaFileObject = javaFileObjectMap.get(fromLocation(location, packageName, relativeName));
        if (null != javaFileObject) {
            return javaFileObject;
        }
        return super.getFileForInput(location, packageName, relativeName);
    }

    /**
     * 这里是编译器返回的同(源)Java文件对象,替换为CharSequenceJavaFileObject实现
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        JavaFileObject javaFileObject = new CharSequenceJavaFileObject(className, kind);
        classLoader.addJavaFileObject(className, javaFileObject);
        return javaFileObject;
    }

    /**
     * 这里覆盖原来的类加载器
     */
    @Override
    public ClassLoader getClassLoader(Location location) {
        return classLoader;
    }

    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        if (file instanceof CharSequenceJavaFileObject) {
            return file.getName();
        }
        return super.inferBinaryName(location, file);
    }

    @Override
    public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
        Iterable<JavaFileObject> superResult = super.list(location, packageName, kinds, recurse);
        List<JavaFileObject> result = new ArrayList<>();
        // 这里要区分编译的Location以及编译的Kind
        if (location == StandardLocation.CLASS_PATH && kinds.contains(JavaFileObject.Kind.CLASS)) {
            // .class文件以及classPath下
            for (JavaFileObject file : javaFileObjectMap.values()) {
                if (file.getKind() == JavaFileObject.Kind.CLASS && file.getName().startsWith(packageName)) {
                    result.add(file);
                }
            }
            // 这里需要额外添加类加载器加载的所有Java文件对象
            result.addAll(classLoader.listJavaFileObject());
        } else if (location == StandardLocation.SOURCE_PATH && kinds.contains(JavaFileObject.Kind.SOURCE)) {
            // .java文件以及编译路径下
            for (JavaFileObject file : javaFileObjectMap.values()) {
                if (file.getKind() == JavaFileObject.Kind.SOURCE && file.getName().startsWith(packageName)) {
                    result.add(file);
                }
            }
        }
        for (JavaFileObject javaFileObject : superResult) {
            result.add(javaFileObject);
        }
        return result;
    }

    /**
     * 自定义方法,用于添加和缓存待编译的源文件对象
     */
    public void addJavaFileObject(Location location, String packageName, String relativeName, JavaFileObject javaFileObject) {
        javaFileObjectMap.put(fromLocation(location, packageName, relativeName), javaFileObject);
    }
}
