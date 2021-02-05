package com.chenjj.base.enum1;

public enum Gender implements GenderDesc {
    /**
     * 如果需要每个枚举值在调用该方法时呈现出不同的行为方式，则可以让每个枚举值分别来实现该方法，
     * 每个枚举值提供不同的实现方式，从而让不同的枚举值调用该方法时具有不同的行为方式。
     * 在这种情况下，当创建MALE、FEMALE枚举值时，并不是直接创建Gender枚举类的实例，而是相当于创建
     * Gender的匿名子类的实例。
     * 枚举类不是用final修饰了吗？怎么还能派生子类呢？
     * 并不是所有的枚举类都使用了final修饰！
     * 非抽象的枚举类才默认使用final修饰。对于一个抽象的枚举类而言——只要它包含了抽象方法，它就是抽象枚举类，
     * 系统会默认使用abstract修饰，而不是使用final修饰。
     */
    MALE("男") {
        @Override
        public void info() {
            System.out.println("这个枚举值代表男性");
        }
    }, FEMALE("女") {
        @Override
        public void info() {
            System.out.println("这个枚举值代表女性");
        }
    };
    private final String name;

    /**
     * 只能是私有的， private可以去掉
     *
     * @param name
     */
    private Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 如果由枚举类来实现接口里的方法，则每个枚举值在调用该方法时都有相同的行为方式（因为方法体完全一样）。
     */
    /*@Override
    public void info() {
        System.out.println("这是一个用于定义性别Field的枚举类");
    }*/

    // 可以定义抽象方法
    // public abstract void test();
}
