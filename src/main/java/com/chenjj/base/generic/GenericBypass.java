package com.chenjj.base.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericBypass {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(23);
        /**
         * 正常情况下，因为泛型的限制，编译器不让最后一行代码编译通过，因为类似不匹配。
         * 但是，基于对类型擦除的了解，利用反射，我们可以绕过这个限制。
         */
        // list.add("text");

        try {
            Method method = list.getClass().getDeclaredMethod("add", Object.class);
            method.invoke(list, "text");
            method.invoke(list, 22);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        for (Object o : list) {
            System.out.println(o);
        }
    }
}
