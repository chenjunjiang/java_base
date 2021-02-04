package com.chenjj.base.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 确定何时应该使用 SafeVarargs 注解的规则很简单 ： 对于每一个带有泛型可变参
 * 数或者参数化类型的方法，都要用＠SafeVarargs 进行注解， 这样它的用户就不用承受
 * 那些无谓的、令人困惑的编译警报了 。
 * 注意，SafeVarargs注解只能用在无法被覆盖的方法上，因为它不能确保每个可能的
 * 覆盖方法都是安全的 。在Java8中，该注解只在静态方法和final实例方法中才是合法的；
 * 在Java9中，它在私有的实例方法上也合法了 。
 */
public class FlattenWithVarargs {
    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists)
            result.addAll(list);
        return result;
    }

    public static void main(String[] args) {
        // 如果没有上面的，会有警告：Unchecked generics array creation for varargs parameter
        List<Integer> flatList = flatten(
                Arrays.asList(1, 2), Arrays.asList(3, 4, 5), Arrays.asList(6, 7));
        System.out.println(flatList);
    }
}
