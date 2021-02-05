package com.chenjj.base.enum1;

/**
 * 下面的枚举只要换了顺序就会导致numberOfMusicians方法的返回值和之前不同，
 * 可能会造成业务逻辑错误。
 * 永远不要根据枚举的序数导出与它关联的值(比如：numberOfMusicians方法)，
 * 而是要将它保存在一个实例域中，参见：Ensemble1。
 * Enum 规范中谈及 ordinal 方法时写道：“大多数程序员都不需要这个方法 。 它是设计
 * 用于像 EnumSet 和 EnumMap 这种基于枚举的通用数据结构的 。 ”除非你在编写的是这种数
 * 据结构，否则最好完全避免使用 ordinal 方法 。
 */
public enum Ensemble {
    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, DOUBLE_QUARTET,
    NONET, DECTET, TRIPLE_QUARTET;

    /**
     * Enum类提供了一个ordinal()方法，用来返回枚举对象的序数。比如SOLO就是0，DUET就是1
     * @return
     */
    public int numberOfMusicians() {
        return ordinal() + 1;
    }

    public static void main(String[] args) {
        System.out.println(DUET.numberOfMusicians());
    }
}
