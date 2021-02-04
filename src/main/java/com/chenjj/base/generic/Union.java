package com.chenjj.base.generic;

import java.util.HashSet;
import java.util.Set;

public class Union {

    /**
     * 不要用通配符类型作为返回类型 。
     *
     * @param s1
     * @param s2
     * @param <E>
     * @return
     */
    public static <E> Set<E> union(Set<? extends E> s1,
                                   Set<? extends E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    // Simple program to exercise flexible generic staticfactory
    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.add(3);
        integers.add(5);

        Set<Double> doubles = new HashSet<>();
        doubles.add(2.0);
        doubles.add(4.0);
        doubles.add(6.0);

        Set<Number> numbers = union(integers, doubles);

        // Explicit type parameter - required prior to Java 8,Java 8的类型推导
        // Set<Number> numbers = Union.union(integers, doubles);

        System.out.println(numbers);
    }
}
