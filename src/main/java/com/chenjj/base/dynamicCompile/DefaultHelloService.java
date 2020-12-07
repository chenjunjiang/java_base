package com.chenjj.base.dynamicCompile;

public class DefaultHelloService implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println(String.format("%s say hello [by default]", name));
        System.out.println(name+" say hello [by default]");
    }
}
