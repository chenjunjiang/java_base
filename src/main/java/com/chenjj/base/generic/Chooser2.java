package com.chenjj.base.generic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 要消除未受检的转换警告，必须选择用列表代替数组 。
 *
 * @param <T>
 */
public class Chooser2<T> {
    private final List<T> choiceList;

    public static void main(String[] args) {
        Chooser2<Integer> chooser = new Chooser2(Arrays.asList(1, 2, 3));
        int result = chooser.choose();
        System.out.println(result);
    }

    public Chooser2(Collection<T> choices) {
        this.choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random random = ThreadLocalRandom.current();
        return choiceList.get(random.nextInt(choiceList.size()));
    }
}
