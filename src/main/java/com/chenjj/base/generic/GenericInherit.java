package com.chenjj.base.generic;

public class GenericInherit<T> {
    private T data1;
    private T data2;

    public T getData1() {
        return data1;
    }

    public void setData1(T data1) {
        this.data1 = data1;
    }

    public T getData2() {
        return data2;
    }

    public void setData2(T data2) {
        this.data2 = data2;
    }

    public static <V> void setData2(GenericInherit<Father> data2) {

    }

    public static void main(String[] args) {
        Father father = new Father();
        Son son = new Son();

        GenericInherit<Father> fatherGenericInherit = new GenericInherit<>();
        GenericInherit<Son> sonGenericInherit = new GenericInherit<>();
        SubGenericInherit<Father> fatherSubGenericInherit = new SubGenericInherit<>();
        SubGenericInherit<Son> sonSubGenericInherit = new SubGenericInherit<>();

        /**
         * 对于传递的泛型类型是继承关系的泛型类之间是没有继承关系的
         * GenericInherit<Father> 与GenericInherit<Son> 没有继承关系
         * Incompatible types.
         */
        // fatherGenericInherit=new GenericInherit<Son>();

        // 泛型类可以继承其它泛型类，例如: public class ArrayList<E> extends AbstractList<E>
        fatherGenericInherit = new SubGenericInherit<Father>();

        setData2(fatherGenericInherit);
        // setData2(sonGenericInherit);
        setData2(fatherSubGenericInherit);
        // setData2(sonSubGenericInherit);
    }
}
