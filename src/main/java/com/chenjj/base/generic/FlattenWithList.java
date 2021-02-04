package com.chenjj.base.generic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 如果不想使用SafeVarargs注解，也可以用一个 List参数代替
 * 可变参数（这是一个伪装数组） 。
 * 其缺点在于客户端代码有点烦琐，运行起来速度会慢一些 。
 */
public class FlattenWithList {
    static <T> List<T> flatten(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists)
            result.addAll(list);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> flatList = flatten(Arrays.asList(
                Arrays.asList(1, 2), Arrays.asList(3, 4, 5), Arrays.asList(6, 7)));
        System.out.println(flatList);
    }
}
