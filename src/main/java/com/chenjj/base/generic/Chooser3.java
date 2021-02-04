package com.chenjj.base.generic;

import java.util.*;

public class Chooser3<T> {
    private final List<T> choiceList;
    private final Random rnd = new Random();

    public Chooser3(Collection<? extends T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Chooser3<Number> chooser = new Chooser3<>(intList);
        for (int i = 0; i < 10; i++) {
            Number choice = chooser.choose();
            System.out.println(choice);
        }
    }
}
