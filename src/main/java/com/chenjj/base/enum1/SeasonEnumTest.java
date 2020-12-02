package com.chenjj.base.enum1;

public class SeasonEnumTest {
    public static void main(String[] args) {
        // values方法返回了SeasonEnum枚举类的所有实例
        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            System.out.println(seasonEnum);
        }
        new SeasonEnumTest().judge(SeasonEnum.SPRING);
    }

    public void judge(SeasonEnum seasonEnum) {
        switch (seasonEnum) {
            case SPRING:
                System.out.println("春暖花开，正好踏青");
                break;
            case SUMMER:
                System.out.println("夏日炎炎，适合游泳");
                break;
            case FALL:
                System.out.println("秋高气爽，进补及时");
                break;
            case WINTER:
                System.out.println("冬日雪飘，围炉赏雪");
                break;
        }
    }
}
