package com.chenjj.base.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenjj
 * @description 测试EchoService
 * @creat_time 2019/12/3
 **/
public class TestEchoService {
    public static void main(String[] args) {
        MyService myService = new MyServiceImpl();
        // 获取myService这个实例的所有接口
        Class<?>[] interfaces2 = myService.getClass().getInterfaces();
        // 将EchoService接口添加到Class<?>数组中
        Class<?>[] interfaces = new Class<?>[interfaces2.length + 1];
        interfaces[0] = EchoService.class;
        for (int i = 0; i < interfaces2.length; i++) {
            interfaces[i + 1] = interfaces2[i];
        }
        // 创建代理对象
        Object object = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces,
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法调用之前......" + method.getName());
                /*try {
                    // //处理EchoService方法，从而实现回声定位功能，返回值就是第一个参数
                    if ("$echo".equals(method.getName())) {
                        return args[0];
                    }
                    return method.invoke(myService, args);
                } finally {
                    System.out.println("方法调用之后......" + method.getName());
                }*/

                if ("$echo".equals(method.getName())) {
                    return args[0];
                }
                Object result = method.invoke(myService, args);
                System.out.println("方法调用之后......" + method.getName());
                return result;
            }
        });
        // 正常调用
        MyService myService1 = (MyService) object;
        myService1.sayHello();
        // 强制转为EchoService对象并调用方法
        EchoService echoService = (EchoService) object;
        Object result = echoService.$echo("xxx");
        System.out.println(result);
    }
}
