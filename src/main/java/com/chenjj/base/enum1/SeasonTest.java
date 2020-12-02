package com.chenjj.base.enum1;

public class SeasonTest {
    public SeasonTest(Season season) {
        System.out.println(season.getName() + ", 这真是一个" + season.getDesc() + "的季节");
    }

    public static void main(String[] args) {
        new SeasonTest(Season.FALL);
    }
}
