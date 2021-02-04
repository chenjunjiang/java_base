package com.chenjj.base.generic;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("zhangsan");
        stack.push("lisi");
        stack.push("wangwu");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
        }
    }

    /**
     * 不能创建泛型数组(Type parameter 'E' cannot be instantiated directly)。
     * 每当编写用数组支持的泛型时，都会出现这个问题 。解决这个问题有两种方法：
     * 第一种，直接绕过创建泛型数组的禁令：创建一个 Object 的数组，并将它转换成泛型数组类型 。
     * 现在错误是消除了，但是编译器会产生一条警告 。 这种用法是合法的，但（整体上而言）不是类型
     * 安全的。编译器不可能证明你的程序是类型安全的，但是你可以 。 你自己必须确保未受检的转
     * 换不会危及程序的类型安全性 。一旦你证明了未受检的转换是安全的，就要在尽可能小的范围中禁止警告
     * 在这种情况下，构造器只包含未受检的数组创建，因此可以在整个构造器中禁止这条警
     * 告。 通过增加一条注解＠ SuppressWarnings 来完成禁止， Stack 能够正确无误地进行编
     * 译，你就可以使用它了，无须显式的转换，也无须担心会出现 ClassCastException 异常。
     * <p>
     * 第二种方法是，将elements域的类型从E[]改为Object[]。
     * <p>
     * 这两种消除泛型数组创建的方法，各有所长 。 第一种方法的可读性更强 ： 数组被声明
     * 为 E[]类型清楚地表明它只包含 E 实例 。 它也更加简洁 ： 在一个典型的泛型类 中，可以在
     * 代码中的多个地方读取到该数组；第一种方法只需要转换一次（创建数组的时候），而第二
     * 种方法则是每次读取一个数组元素时都需要转换一次 。 因此，第一种方法优先，在实践中也
     * 更常用 。 但是，它会导致堆污染（ heap pollution ）： 数组的运行时类型与它的
     * 编译时类型不匹配（除非E正好是Object） 。 这使得有些程序员会觉得很不舒服，因而选
     * 择第二种方案。
     * <p>
     * 鼓励优先使用列表而非数组。实际上不可能总是或者总想在泛型中使用列表。Java并不是生来就支持列表，
     * 因此有些泛型如 ArrayList,必须在数组上实现 。为了提升性能，其他泛型如 HashMap 也在数组上实现 。
     */
    // @SuppressWarnings("unchecked")
    public Stack() {
        // elements = new E[DEFAULT_INITIAL_CAPACITY];
        // 会有警告：Unchecked cast: 'java.lang.Object[]' to 'E[]'
        // elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        // 会有警告：Unchecked cast: 'java.lang.Object' to 'E'
        E result = (E) elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
