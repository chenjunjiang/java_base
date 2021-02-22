package com.chenjj.base.string;

/**
 * 直接在表达式里写值，java不用根据变量去内存里找对应的值，可以在编译的时候直接对这个表达式进行优化，
 * 优化后的表达式从 "111"+"" 直接变成了 "111" ，两个String类型的变量都指向了常量池的111字符串，
 * 因此结果为true;
 * <p>
 * 可以通过javap -c 反编译查看：
 * D:\Java\jdk1.8.0_162\bin\javap.exe -c com.chenjj.base.string.Splice2
 * Compiled from "Splice2.java"
 * public class com.chenjj.base.string.Splice2 {
 * public com.chenjj.base.string.Splice2();
 * Code:
 * 0: aload_0
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: return
 * <p>
 * public static void main(java.lang.String[]);
 * Code:
 * 0: ldc           #2                  // String 111
 * 2: astore_1
 * 3: ldc           #2                  // String 111
 * 5: astore_2
 * 6: ldc           #2                  // String 111
 * 8: astore_3
 * 9: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 12: aload_1
 * 13: aload_3
 * 14: if_acmpne     21
 * 17: iconst_1
 * 18: goto          22
 * 21: iconst_0
 * 22: invokevirtual #4                  // Method java/io/PrintStream.println:(Z)V
 * 25: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 28: aload_1
 * 29: aload_2
 * 30: if_acmpne     37
 * 33: iconst_1
 * 34: goto          38
 * 37: iconst_0
 * 38: invokevirtual #4                  // Method java/io/PrintStream.println:(Z)V
 * 41: return
 * }
 */
public class Splice2 {
    public static void main(String[] args) {
        String str1 = "111";
        String a = "111";
        String b = "222";
        String c = "111222";
        String str2 = "111" + "";
        String str3 = a + b;
        System.out.println(str1 == str2);// true
        System.out.println(str1 == a);// true
        System.out.println(str3 == c);// false
    }
}
