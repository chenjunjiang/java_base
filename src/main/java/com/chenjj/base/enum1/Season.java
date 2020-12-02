package com.chenjj.base.enum1;

/**
 * Season类是一个不可变类，在Season类中包含了4个static final常量Field，这4个常量Field就代表了该类所能
 * 创建的对象。当其他程序需要使用Season对象时，既可通过如Season.SPRING的方式来取得Season对象，
 * 也可通过getSeason()静态工厂方法来获得Season对象。
 */
public final class Season {
    private final String name;
    private final String desc;

    public static final Season SPRING = new Season("春天", "趁春踏青");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season FALL = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "围炉赏雪");

    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static Season getSeason(int seasonNum) {
        switch (seasonNum) {
            case 1:
                return SPRING;
            case 2:
                return SUMMER;
            case 3:
                return FALL;
            case 4:
                return WINTER;
            default:
                return null;
        }
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
