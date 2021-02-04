package com.chenjj.base.serialization;

import java.io.*;

/**
 * 对单例类进行序列化后再反序列化可能会创建新的实例，即使构造方法是私有的！
 * 为了解决上述问题，需要定义一种被称为readResolve的特殊序列化方法。
 * 如果定义了readResolve方法，在对象反序列化时就会调用它。
 */
public class Singleton implements Serializable {
    private static final long serialVersionUID = 6242459382274456459L;

    private static final Singleton SINGLETON = new Singleton(100);

    private int value;

    private Singleton(int value) {
        // private!
        this.value = value;
    }

    public static void main(String[] args) throws Exception {
        Singleton singleton1 = Singleton.getInstance();

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("singleton.dat"));
        output.writeObject(singleton1);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("singleton.dat"));
        Singleton singleton2 = (Singleton) input.readObject();

        System.out.println("创建新实例：" + (singleton1 != singleton2));
    }

    public static Singleton getInstance() {
        return SINGLETON;
    }

    protected Object readResolve() throws ObjectStreamException {
        return SINGLETON;
    }
}
