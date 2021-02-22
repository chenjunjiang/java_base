package com.chenjj.base.primitive;

/**
 * 运行会抛出：java.lang.NullPointerException
 * 问题在于， i 是个 Integer ，而不是 int,
 * 就像所有的对象引用域一样，它的初始值为 null。当程序计算表达式（i == 42）时，它会
 * 将 Integer 与 int 进行比较 。 几乎在任何一种情况下， 当在一项操作中混合使用基本类
 * 型和装箱基本类型时，装箱基本类型就会自动拆箱 ，这种情况无一例外 。 如果 null 对象引
 * 用被自动拆箱，就会抛出一个 NullPointerException 异常 。 就如这个程序所示，它几
 * 乎可以在任何位置发生 。 修正这个问题很简单，声明i是个int而不是 Integer 即可 。
 *
 * 那么什么时候应该使用装箱基本类型呢？它们有几个合理的用处。第一个是作为集合
 * 中的元素、键和值 。 你不能将基本类型放在集合中，因此必须使用装箱基本类型 。
 * 这是一种更通用的特例 。在参数化类型和方法中，必须使用装箱基本类型作为类型参
 * 数，因为 Java 不允许使用基本类型 。例如，你不能将变量声明为 ThreadLocal<int>类
 * 型，因此必须使用 ThreadLocal<Integer＞代替 。
 * 最后，在进行反射的方法调用，必须使用装箱基本类型 。
 *
 * 总而言之，当可以选择的时候，基本类型要优先于装箱基本类型 。 基本类型更加简单，
 * 也更加快速 。 如果必须使用装箱基本类型，要特别小心！ 自动装箱减少了使用装箱基本类型
 * 的烦琐性，但是并没有减少它的风险 。 当程序用＝＝操作符比较两个装箱基本类型时，它做
 * 了个同一性比较，这几乎肯定不是你所希望的 。 当程序进行涉及装箱和拆箱基本类型的混
 * 合类型计算时，它会进行拆箱。
 * 最后，当程序装箱了基本类型值时，会导致较高的资源消耗和不必要的对象创建。
 */
public class Unbelievable {
    static Integer i;

    public static void main(String[] args) {
        if (i == 42) {
            System.out.println("Unbelievable");
        } else {
            System.out.println("test");
        }
    }
}
