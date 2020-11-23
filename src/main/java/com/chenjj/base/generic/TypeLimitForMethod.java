package com.chenjj.base.generic;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeLimitForMethod {
    /**
     * 如果要实现这样的功能就需要对泛型方法的类型做出限定
     */
    /*private static <T> T getMin(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }*/

    /**
     * 限定类型使用extends关键字指定
     * 可以是类、接口；类放在前面，接口放在后面，用&符号分割
     * 例如：<T extends ArrayList & Comparable<T> & Serializable>
     */
    private static <T extends Comparable<T>> T getMin(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

    public static void main(String[] args) {
        System.out.println(getMin(2, 4));
        System.out.println(getMin("a", "r"));
    }
}
