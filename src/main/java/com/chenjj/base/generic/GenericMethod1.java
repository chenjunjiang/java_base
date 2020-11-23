package com.chenjj.base.generic;

public class GenericMethod1 {
    private static int add(int a, int b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    private static <T> T genericAdd(T a, T b) {
        System.out.println(a + "+" + b + "=" + a + b);
        return a;
    }

    public static void main(String[] args) {
        add(1, 2);
        genericAdd("a", "b");
    }
}
