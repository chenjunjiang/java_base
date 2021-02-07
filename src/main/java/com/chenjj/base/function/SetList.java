package com.chenjj.base.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            // 这样到底是要删除指定下标的元素，还是直接删除指定元素,idea也会有警告。
            list.remove(i);
        }
        System.out.println(set + " " + list);
    }
}
