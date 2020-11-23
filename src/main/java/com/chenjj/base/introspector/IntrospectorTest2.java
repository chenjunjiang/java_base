package com.chenjj.base.introspector;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class IntrospectorTest2 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        User user = new User("jack", 21);

        String propertyName = "nameX";
        PropertyDescriptor namePd = new PropertyDescriptor(propertyName, User.class);

        System.out.println("名字：" + namePd.getReadMethod().invoke(user));
        namePd.getWriteMethod().invoke(user, "tom");
        System.out.println("名字：" + namePd.getReadMethod().invoke(user));

        System.out.println("========================================");

        String agePropertyName = "ageX";
        PropertyDescriptor agePd = new PropertyDescriptor(agePropertyName, User.class);

        System.out.println("年龄：" + agePd.getReadMethod().invoke(user));
        agePd.getWriteMethod().invoke(user, 22);
        System.out.println("年龄：" + agePd.getReadMethod().invoke(user));
    }
}
