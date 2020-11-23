package com.chenjj.base.generic;

public class GenericRestrict1<T> {
    static class NormalClass {

    }

    private T data;

    /**
     * 不能实例化泛型类
     */
    /*public void setData() {
        this.data  = new T();
    }*/

    // 静态变量或方法不能引用泛型类型变量
    /*private static T result;

    private static T getResult(){
        return result;
    }*/

    /**
     * 静态泛型方法是可以的
     *
     * @param k
     * @param <K>
     * @return
     */
    private static <K> K getKey(K k) {
        return k;
    }

    public static void main(String[] args) {
        NormalClass normalClassA = new NormalClass();
        NormalClass normalClassB = new NormalClass();

        // 基本类型无法作为泛型类型
        // GenericRestrict1<int> genericRestrictInt = new GenericRestrict1<>();
        GenericRestrict1<Integer> genericRestrictInteger = new GenericRestrict1<>();
        GenericRestrict1<String> genericRestrictString = new GenericRestrict1<>();
        // 无法使用instanceof关键字判断泛型类的类型
        /*if (genericRestrictInteger instanceof GenericRestrict1<Integer>) {
            return;
        }*/

        // 无法使用“==”判断两个泛型类的实例
        /*if (genericRestrictInteger == genericRestrictString) {
            return;
        }*/

        // 泛型类的原生类型与所传递的泛型无关，无论传递什么类型，原生类是一样的
        System.out.println(normalClassA == normalClassB);//false
        System.out.println(genericRestrictInteger == genericRestrictInteger);// true
        System.out.println(genericRestrictInteger.getClass() == genericRestrictString.getClass()); //true
        System.out.println(genericRestrictInteger.getClass());// class com.chenjj.base.generic.GenericRestrict1
        System.out.println(genericRestrictString.getClass());// class com.chenjj.base.generic.GenericRestrict1

        // 泛型数组可以声明但无法实例化
        GenericRestrict1<String>[] genericRestrict1s;
        // genericRestrict1s = new GenericRestrict1<String>[10];
        genericRestrict1s = new GenericRestrict1[10];
        genericRestrict1s[0] = genericRestrictString;
    }
}
