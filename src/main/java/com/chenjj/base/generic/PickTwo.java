package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 让另一个方法(pickTwo)访问一个泛型可变参数数组是不安全的。
 * 有两种情况例外 ： 将数组传给另一个用@SafeVarargs正确注解过的可变参数方法是安全
 * 的，将数组传给只计算数组内容部分函数的非可变参数方法也是安全的 。
 * 泛型可变参数方法在下列条件下是安全的 ：
 * 1. 它没有在可变参数数组中保存任何值 。
 * 2. 它没有对不被信任的代码开放该数组（或者其克隆程序） 。
 * 以上两个条件只要有任何一条被破坏，就要立即修正它 。
 * 例子查看FlattenWithVarargs和FlattenWithList。
 */
public class PickTwo {
    static <T> T[] toArray(T... args) {
        return args;
    }

    /**
     * 在编译这个方法时，编译器会产生代码，将两个T实例传
     * 到 toArray 。这些代码配置了一个类型为Object[]的数组，这是确保能够保存这些实例
     * 的最具体的类型，无论在调用时给 pickTwo 传递什么类型的对象都没问题。toArray方
     * 法只是将这个数组返回给 pickTwo，因此pickTwo始终都会返回一个类型为 Object[]的数组 。
     *
     * @param a
     * @param b
     * @param c
     * @param <T>
     * @return
     */
    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, b);
            case 1:
                return toArray(a, c);
            case 2:
                return toArray(b, c);
        }
        throw new AssertionError(); // Can't get here
    }

    public static void main(String[] args) {
        // java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
        String[] attributes = pickTwo("Good", "Fast", "Cheap");
        System.out.println(Arrays.toString(attributes));
    }
}
