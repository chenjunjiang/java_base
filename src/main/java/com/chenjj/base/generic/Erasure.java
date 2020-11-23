package com.chenjj.base.generic;

import java.lang.reflect.Field;

public class Erasure<T> {
    T object;

    public Erasure(T object) {
        this.object = object;
    }

    public static void main(String[] args) {
        Erasure<String> erasure = new Erasure<String>("hello");
        Class eclz = erasure.getClass();
        // erasure class is:com.chenjj.base.generic.Erasure
        System.out.println("erasure class is:" + eclz.getName());
        /**
         * Class 的类型仍然是 Erasure 并不是 Erasure<T>这种形式，那我们再看看泛型类中 T 的类型在 jvm 中是什么具体类型。
         */
        Field[] fs = eclz.getDeclaredFields();
        for (Field f : fs) {
            // Field name object type:java.lang.Object
            System.out.println("Field name " + f.getName() + " type:" + f.getType().getName());
        }
    }
}
