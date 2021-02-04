package com.chenjj.base.effective.clone;

import org.apache.commons.lang3.SerializationUtils;

public class SerializeCloneTest {
    public static void main(String[] args) {
        Major major1 = new Major("计算机科学与技术", 666666);
        Student student1 = new Student("CodeSheep", 18, major1);
        // 使用Apache Commons Lang序列化进行深拷贝
        Student student2 = SerializationUtils.clone(student1);
        System.out.println(student1 == student2);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println("\n");

        // 修改student1的值类型字段
        student1.setAge(35);

        // 修改student1的引用类型字段
        major1.setMajorName("电子信息工程");
        major1.setMajorId(888888);

        System.out.println(student1);
        System.out.println(student2);
    }
}
