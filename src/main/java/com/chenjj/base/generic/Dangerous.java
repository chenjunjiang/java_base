package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.List;

/**
 * 为什么显式创建泛型数组是非法的，用泛型可变参数声明方法却是合法的呢？
 * 答案在于，带有泛型可变参数或者参数化类型的方法在实践中用处很大，
 * 因此 Java 语言的设计者选择容忍这 一矛盾的存在 。
 */
public class Dangerous {
    /**
     * 当调用一个可变参数方法时，会创建一个数组用来存放可变参数。
     *
     * @param stringLists
     */
    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = Arrays.asList(42);
        Object[] objects = stringLists;
        objects[0] = intList;
        // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        String s = stringLists[0].get(0);
        System.out.println(s);
    }

    public static void main(String[] args) {
        dangerous(Arrays.asList("There be dragons!"));
    }
}
