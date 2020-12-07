package com.chenjj.base.dynamicCompile;

/**
 * 通过JavaCompiler去编译字符串表示的源码
 */
public class Client {
    // 通过字符串SOURCE_CODE定义一个类
    static String SOURCE_CODE = "package com.chenjj.base.dynamicCompile;\n" +
            "\n" +
            "public class JdkDynamicCompileHelloService implements HelloService{\n" +
            "\n" +
            "    @Override\n" +
            "    public void sayHello(String name) {\n" +
            "        System.out.println(String.format(\"%s say hello [by jdk dynamic compile]\", name));\n" +
            "    }\n" +
            "}";

    public static void main(String[] args) throws Exception {
        HelloService instance = JdkCompiler.compile("com.chenjj.base.dynamicCompile", "JdkDynamicCompileHelloService", SOURCE_CODE, new Class[]{}, new Object[]{});
        instance.sayHello("throwable");
    }
}
