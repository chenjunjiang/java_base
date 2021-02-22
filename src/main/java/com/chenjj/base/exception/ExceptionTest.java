package com.chenjj.base.exception;

import java.sql.SQLException;

public class ExceptionTest {
    public static void main(String[] args) {
        try {
            /**
             * 由于test方法抛出的真实异常是ArithmeticException，
             * 所以这里用IllegalArgumentException来捕获是不会成功的，
             * ArithmeticException异常会直接抛给main方法。
             */
            test();

            /**
             * 如果调用方法显示声明会抛出checked异常，那么这里必须捕获这个异常，否则编译错误。
             * unchecked异常可以不用捕获。
             */
            // test1();
        } catch (ArithmeticException e) {
            System.out.println("异常了......");
        }
    }

    /**
     * throws IllegalArgumentException只是表示test()方法可能会抛出该类型异常，
     * 至于方法里面随便抛出什么异常都可以
     */
    public static void test() throws IllegalArgumentException {
        try {
            System.out.println(1 / 0);
        } catch (ArithmeticException e) {
            throw e;
        }
    }

    /**
     * SQLException 是checked异常
     *
     * @throws SQLException
     */
    public static void test1() throws SQLException {
        try {
            System.out.println(1 / 0);
        } catch (ArithmeticException e) {
            throw e;
        }
    }
}
