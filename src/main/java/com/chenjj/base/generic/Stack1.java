package com.chenjj.base.generic;

import java.util.*;

/**
 * 为了获得最大限度的灵活性，要在表示生产者或者消费者的输入参数上
 * 使用通配符类型 。 如果某个输入参数既是生产者，又是消费者，那么通配符类型对你就没有
 * 什么好处了 ： 因为你需要的是严格的类型匹配，这是不用任何通配符而得到的 。
 * 下面的助记符便于让你记住要使用哪种通配符类型：
 * PECS 表示 producer-extends, consumer-super 。
 * 换句话说，如果参数化类型表示一个生产者 T ，就使用＜？ extends T＞；如果它表示
 * 一个消 费者 T ，就使用 ＜？ super T ＞ 。 在我们的 Stack 示例中， pushAll 的 src 参数产
 * 生 E 实例供Stack使用，因此src相应的类型为 Iterable<? extends E> ; popAll
 * 的 dst参数通过 Stack消费E实例，因此dst相应的类型为 Collection<? super E＞ 。
 * PECS 这个助记符突 出了使用通配符类型 的基本原则 。
 * @param <E>
 */
public class Stack1<E> {
    private E[] elements;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static void main(String[] args) {
        Stack1<Number> numberStack = new Stack1<>();
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        // Error:(15, 29) java: 不兼容的类型: java.lang.Iterable<java.lang.Integer>无法转换为java.lang.Iterable<java.lang.Number>
        // numberStack.pushAll(integers);
        numberStack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();
        // Error:(18, 28) java: 不兼容的类型: java.util.Collection<java.lang.Object>无法转换为java.util.Collection<java.lang.Number>
        // numberStack.popAll(objects);
        numberStack.popAll(objects);
    }

    @SuppressWarnings("unchecked")
    public Stack1() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    /*public void pushAll(Iterable<E> src) {
        for (E e : src)
            push(e);
    }*/

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src)
            push(e);
    }

    /*public void popAll(Collection<E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }*/
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}
