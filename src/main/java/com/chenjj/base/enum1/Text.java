package com.chenjj.base.enum1;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * EnumSet里面的增、删、改、查是通过位运算加数组实现的
 */
public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // Any Set could be passed in, but EnumSet is clearly best
    public void applyStyles(Set<Style> styles) {
        System.out.printf("Applying styles %s to text%n",
                Objects.requireNonNull(styles));
    }

    // Sample use
    public static void main(String[] args) {
        Text text = new Text();
        EnumSet<Style> enumSet = EnumSet.of(Style.BOLD, Style.ITALIC);
        Iterator<Style> iterator = enumSet.iterator();
        while (iterator.hasNext()) {
            Style style = iterator.next();
            System.out.println(style);
        }
        text.applyStyles(enumSet);
    }
}
