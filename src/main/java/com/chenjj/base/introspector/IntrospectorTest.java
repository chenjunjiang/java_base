package com.chenjj.base.introspector;

import java.beans.*;

public class IntrospectorTest {
    public static void main(String[] args) throws IntrospectionException {
        // 获取整个JavaBean的信息
        // BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        // 在Object类时候停止检索，可以选择在任意一个父类停止
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        System.out.println("所有属性描述：");
        // 获取所有的属性描述，属性和User定义的成员变量无关，只与setXxx和getXxx有关
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : pds) {
            System.out.println(propertyDescriptor.getName());
        }
        System.out.println("所有方法描述：");
        for (MethodDescriptor methodDescriptor : beanInfo.getMethodDescriptors()) {
            System.out.println(methodDescriptor.getName());
            // Method method = methodDescriptor.getMethod();
        }
    }
}
