package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser1<T> {
    private final T[] choiceArray;

    public static void main(String[] args) {
        Chooser1 chooser = new Chooser1(Arrays.asList(1, 2, 3));
        /**
         * 从 Object 转换成每次调用该方法时想要的类型，如果搞错类型，转换就会在运行时失败 。
         * 建议将Chooser修改成泛型
         */
        int result = (int) chooser.choose();
        System.out.println(result);
    }

    public Chooser1(Collection<T> choices) {
        /**
         * 这里会有警告：Unchecked cast: 'java.lang.Object[]' to 'T[]'
         *因为程序在运行时还不知道 T 是什么一记住，元素类型信息会在运行时从泛型中被擦除 。
         * 这段程序可以运行吗？可以，但是编译器无法证明这一点 。
          */
        this.choiceArray = (T[])choices.toArray();
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)];
    }
}
