package com.chenjj.base.reflect;

/**
 * @author chenjj
 * @description 自定义接口实现类
 * @creat_time 2019/12/3
 **/
public class MyServiceImpl implements MyService {
    @Override
    public void sayHello() {
        System.out.println("MyServiceImpl:hello");
    }
}
