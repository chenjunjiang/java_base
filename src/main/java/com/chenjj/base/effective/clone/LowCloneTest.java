package com.chenjj.base.effective.clone;

/**
 * 浅拷贝的典型实现方式是：让被复制对象的类实现Cloneable接口，并重写clone()方法即可。
 * 从结果可以看出：
 * student1==student2打印false，说明clone()方法的确克隆出了一个新对象；
 * 修改值类型字段并不影响克隆出来的新对象，符合预期；
 * 而修改了student1内部的引用对象，克隆对象student2也受到了波及，说明内部还是关联在一起的
 * <p>
 * 虽然clone()方法可以完成对象的拷贝工作，但是注意：clone()方法默认是浅拷贝行为，就像上面的例子一样。
 * 若想实现深拷贝需覆写 clone()方法实现引用对象的深度遍历式拷贝，进行地毯式搜索。
 */
public class LowCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Major m = new Major("计算机科学与技术", 666666);
        Student student1 = new Student("CodeSheep", 18, m);

        // 由 student1 拷贝得到 student2
        Student student2 = (Student) student1.clone();

        System.out.println(student1 == student2);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println("\n");

        // 修改student1的值类型字段
        student1.setAge(35);

        // 修改student1的引用类型字段
        m.setMajorName("电子信息工程");
        m.setMajorId(888888);

        System.out.println(student1);
        System.out.println(student2);
    }
}
