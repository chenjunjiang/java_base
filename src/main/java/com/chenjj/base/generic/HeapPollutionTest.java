package com.chenjj.base.generic;

import java.util.*;

/**
 * 堆污染
 * Heap pollution(堆污染), 指的是当把一个不带泛型的对象赋值给一个带泛型的变量时, 就有可能发生堆污染.
 * 堆污染在编译时并不会报错, 只会在编译时提示有可能导致堆污染的警告. 在运行时,如果发生了堆污染,
 * 那么就会抛出类型转换异常.
 */
public class HeapPollutionTest {
    public static void main(String[] args) {
        // test1();

        test2();
    }

    private static void test2() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);

        List<String> strList = new ArrayList<>();
        strList.add("a");

//  如果直接将intList 赋值给lst 编译会报错, 因为类型不一致
        // List<String> lst = intList;

//  借助泛型可以不指定类型可以进行狸猫换太子操作
        List list = intList;
        List<String> lst = list;

//  在获取参数时,会抛出类型转换异常
        System.out.println(lst.get(0));
    }

    private static void test1() {
        // list 定义时未指定泛型,实际上集合中存储为Integer类型
        List list = new ArrayList<Integer>();
        list.add(1);

        // 将无泛型的list对象,赋值给指定类型的strList变量, 此处已经发生堆污染
        List<String> strList = list;

        // 抛出异常 java.lang.ClassCastException
        String str = strList.get(0);
    }


}
