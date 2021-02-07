package com.chenjj.base.function;

import java.util.Arrays;
import java.util.List;

public class Overriding {
    public static void main(String[] args) {
        List<Wine> wineList = Arrays.asList(new Wine(), new SparklingWine(), new Champagne());
        for (Wine wine : wineList) {
            System.out.println(wine.name());
        }
    }
}
