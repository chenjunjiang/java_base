package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SafePickTwo {
    /**
     * 这样得到的代码就是类型安全的，因为它只使用泛型，没有用到数组。用的是List
     * @param a
     * @param b
     * @param c
     * @param <T>
     * @return
     */
    static <T> List<T> pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return Arrays.asList(a, b);
            case 1:
                return Arrays.asList(a, c);
            case 2:
                return Arrays.asList(b, c);
        }
        throw new AssertionError();
    }

    public static void main(String[] args) {
        List<String> attributes = pickTwo("Good", "Fast", "Cheap");
        System.out.println(attributes);
    }
}
