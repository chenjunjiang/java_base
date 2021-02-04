package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.List;

/**
 * 一般来说， 如果类型参数只在方法声明中出现一次，就可以用通配符取代它 。 如果是无
 * 限制的类型参数，就用无限制的通配符取代它；如果是有限制的类型参数，就用有限制的通
 * 配符取代它 。
 */
public class Swap {
    // 1
    /*public static <E> void swap(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }*/

    // 2
    public static void swap(List<?> list, int i, int j) {
        /**
         * 编译错误：Error:(12, 41) java: 不兼容的类型: java.lang.Object无法转换为capture#1, 共 ?
         * list 的类型为List<?>，你不能把 null 之外的任何值放到List<?>中 。
         * 解决办法：
         * 编写一个私有的辅助方法来捕捉通配符类型。为了捕捉类型，辅助方法必须是一个泛型方法。
         */
        // list.set(i, list.set(j, list.get(i)));
        // list.set(0,null);
        swapHelper(list, i, j);
    }

    /**
     * swapHelper 方法知道 list 是一个 List<E> 。 因此，它知道从这个列表中取出的
     * 任何值均为 E 类型，并且知道将 E 类型的任何值放进列表都是安全的 。 swap 这个有些费
     * 解的实现编译起来却是正确无误的 。 它允许我们导出 swap 这个比较好的基于通配符的声
     * 明，同时在内部利用更加复杂的泛型方法 。 swap 方法的客户端不一定要面对更加复杂的
     * swapHelper 声明，但是它们的确从中受益 。
     *
     * @param list
     * @param i
     * @param j
     * @param <E>
     */
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void main(String[] args) {
        List<String> argList = Arrays.asList(args);
        swap(argList, 0, argList.size() - 1);
        System.out.println(argList);
    }
}
