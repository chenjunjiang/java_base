package com.chenjj.base.dynamicCompile;

import javassist.*;

import java.lang.reflect.Constructor;

/**
 * 运行过程中动态生成类并进行编译
 */
public class JavassistClient1 {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        // 获取接口
        CtClass interfaces = pool.get("com.chenjj.base.dynamicCompile.HelloService");
        // 创建类
        CtClass ctClass = pool.makeClass("com.chenjj.base.dynamicCompile.JavassistDynamicCompileHelloService");
        ctClass.setInterfaces(new CtClass[]{interfaces});
        // 创建属性
        CtField ctField1 = CtField.make("private int age;", ctClass);
        CtField ctField2 = CtField.make("private String name;", ctClass);
        ctClass.addField(ctField1);
        ctClass.addField(ctField2);

        //添加构造器
        CtConstructor ctConstructor1 = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, ctClass);
        ctConstructor1.setBody("{this.age=age; this.name=name;}");
        // 无参构造
        CtConstructor ctConstructor2 = new CtConstructor(new CtClass[]{}, ctClass);
        ctConstructor2.setBody("{}");
        ctClass.addConstructor(ctConstructor1);
        ctClass.addConstructor(ctConstructor2);

        //创建方法
        CtMethod ctMethod1 = CtMethod.make("public int getAge(){return age;}", ctClass);
        CtMethod ctMethod2 = CtMethod.make("public void setAge(int age){this.age=age;}", ctClass);
        CtMethod ctMethod3 = CtMethod.make("public void sayHello(String name) {System.out.println(name+\" say hello [by default]\");}", ctClass);
        ctClass.addMethod(ctMethod1);
        ctClass.addMethod(ctMethod2);
        ctClass.addMethod(ctMethod3);

        // ctClass.writeFile("D:\\test"); //将上面构造好的类写入到指定位置
        Class<?> clazz = ctClass.toClass();
        System.out.println(clazz.getName());
        Constructor constructor1 = clazz.getDeclaredConstructor(new Class[]{int.class, String.class});
        // HelloService helloService = (HelloService) constructor1.newInstance(1, "chenjj");
        HelloService helloService = (HelloService) clazz.newInstance();
        helloService.sayHello("chenjj");
    }
}
