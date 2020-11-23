package com.chenjj.base.generic;

public class GenericRestrict2 {
    private class MyException extends Exception {

    }

    /**
     * 泛型类不能继承Exception或者Throwable
     *
     * @param <T>
     */
    /*private class MyGenericException<T> extends Exception{

    }*/

    // 不能捕获泛型类型限定的异常
    /*public <T extends Exception> void getException(T t){
        try {

        }catch (T e){

        }
    }*/
    public <T extends Throwable> void getException(T t) throws T {
        try {

        } catch (Exception e) {
            // 可以将泛型类型限定的异常抛出
            throw t;
        }
    }
}
