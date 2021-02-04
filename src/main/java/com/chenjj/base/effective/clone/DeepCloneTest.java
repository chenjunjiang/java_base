package com.chenjj.base.effective.clone;

/**
 * 如果想实现深拷贝，首先需要对更深一层次的引用类Major做改造，
 * 让其也实现Cloneable接口并重写clone()方法。
 * 其次我们还需要在顶层的调用类中重写clone方法，来调用引用类型字段的clone()方法实现深度拷贝。
 * <p>
 * 如果嵌套的类型太多，要实现深度拷贝就会很复杂，这时我们可以利用反序列化技术。
 */
public class DeepCloneTest {
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
