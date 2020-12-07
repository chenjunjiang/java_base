package com.chenjj.base.dynamicCompile;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * 运行过程中对已存在的类进行修改并编译
 */
public class JavassistClient2 {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.chenjj.base.dynamicCompile.DefaultHelloService");
        CtMethod ctMethod = cc.getDeclaredMethod("sayHello", new CtClass[]{pool.get("java.lang.String")});
        ctMethod.insertBefore("System.out.println(\"insert before by Javassist\");");
        ctMethod.insertAfter("System.out.println(\"insert after by Javassist\");");
        Class<?> klass = cc.toClass();
        System.out.println(klass.getName());
        HelloService helloService = (HelloService) klass.getDeclaredConstructor().newInstance();
        helloService.sayHello("throwable");
    }
}
