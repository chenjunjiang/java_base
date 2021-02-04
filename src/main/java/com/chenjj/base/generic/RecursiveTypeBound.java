package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.List;

public class RecursiveTypeBound {
    /**
     * 参数list产生E实例，按照PECS规则，把它定义成? extends E；E的Comparable是用来消费E实例的
     * (用E来进行排序)，所以定义成Comparable<? super E>。comparable 始终是消费者，
     * 因此使用时始终应该是 Comparable<? super T>优先于Cornparable<T ＞ 。
     * 对于 comparator 接口也一样，因此使用时始终应该是 Comparator<? super T＞优先于 Comparator<T>。
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> E max(List<? extends E> list) {
        if (list.isEmpty())
            throw new IllegalArgumentException("Empty list");

        E result = null;
        for (E e : list)
            if (result == null || e.compareTo(result) > 0)
                result = e;

        return result;
    }

    /**
     * String 实现了Comparable接口
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> argList = Arrays.asList("1", "7", "9", "3");
        System.out.println(max(argList));
    }
}
