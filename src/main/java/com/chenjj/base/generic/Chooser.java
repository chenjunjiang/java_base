package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser {
    private final Object[] choiceArray;

    public static void main(String[] args) {
        Chooser chooser = new Chooser(Arrays.asList(1, 2, 3));
        /**
         * 从 Object 转换成每次调用该方法时想要的类型，如果搞错类型，转换就会在运行时失败 。
         * 建议将Chooser修改成泛型
         */
        int result = (int) chooser.choose();
        System.out.println(result);
    }

    public Chooser(Collection choices) {
        this.choiceArray = choices.toArray();
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)];
    }
}
