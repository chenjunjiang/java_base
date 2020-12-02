package com.chenjj.base.enum1;

/**
 * 定义枚举类时，需要显式列出所有的枚举值，如上面的SPRING,SUMMER,FALL,WINTER;所示，所有的枚举值之间以
 * 英文逗号（,）隔开，枚举值列举结束后以英文分号作为结束。这些枚举值代表了该枚举类的所有可能的实例。
 */
public enum SeasonEnum {
    // 必须在第一行列出枚举实例
    SPRING, SUMMER, FALL, WINTER;
    public String name;
}
