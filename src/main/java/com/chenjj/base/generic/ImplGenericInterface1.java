package com.chenjj.base.generic;

public class ImplGenericInterface1<T> implements GenericInterface<T> {
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    public static void main(String[] args) {
        ImplGenericInterface1<String> genericInterface = new ImplGenericInterface1<>();
        genericInterface.setData("Generic Interface1");
        System.out.println(genericInterface.getData());
    }
}
