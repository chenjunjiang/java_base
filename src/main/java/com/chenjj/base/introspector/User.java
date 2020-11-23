package com.chenjj.base.introspector;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getNameX() {
        return name;
    }

    public void setNameX(String name) {
        this.name = name;
    }

    public int getAgeX() {
        return age;
    }

    public void setAgeX(int age) {
        this.age = age;
    }
}
