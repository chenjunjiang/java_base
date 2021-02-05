package com.chenjj.base.enum1;

/**
 * 无论下边的枚举顺序怎么更换，numberOfMusicians方法返回的值都是和具体的枚举绑定的。不会改变。
 */
public enum Ensemble1 {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;

    Ensemble1(int size) {
        this.numberOfMusicians = size;
    }

    public int numberOfMusicians() {
        return numberOfMusicians;
    }
}
