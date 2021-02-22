package com.chenjj.base.string;

/**
 * 通过变量和字符串拼接，java是需要先到内存找变量对应的值，才能进行完成字符串拼接的工作，
 * 这种方式java编译器没法优化，只能走 StringBuilder进行拼接字符串，然后调用toString方法：
 * @Override public String toString() {
 * // Create a copy, don't share the array
 * return new String(value, 0, count);
 * }
 * 当然返回的结果和常量池中的 111这个字符串的内存地址是不一样的，因此结果为false。
 *
 * 可以通过javap -c 反编译查看：
 * :\Java\jdk1.8.0_162\bin\javap.exe -c com.chenjj.base.string.Splice1
 * Compiled from "Splice1.java"
 * public class com.chenjj.base.string.Splice1 {
 *   public com.chenjj.base.string.Splice1();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: ldc           #2                  // String 111
 *        2: astore_1
 *        3: ldc           #2                  // String 111
 *        5: astore_2
 *        6: new           #3                  // class java/lang/StringBuilder
 *        9: dup
 *       10: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
 *       13: aload_2
 *       14: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 *       17: ldc           #6                  // String
 *       19: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 *       22: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 *       25: astore_3
 *       26: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       29: aload_1
 *       30: aload_3
 *       31: if_acmpne     38
 *       34: iconst_1
 *       35: goto          39
 *       38: iconst_0
 *       39: invokevirtual #9                  // Method java/io/PrintStream.println:(Z)V
 *       42: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       45: aload_1
 *       46: aload_2
 *       47: if_acmpne     54
 *       50: iconst_1
 *       51: goto          55
 *       54: iconst_0
 *       55: invokevirtual #9                  // Method java/io/PrintStream.println:(Z)V
 *       58: return
 * }
 */
public class Splice1 {
    public static void main(String[] args) {
        String str1 = "111";
        String a = "111";
        String str2 = a + "";
        System.out.println(str1 == str2);// false
        System.out.println(str1 == a);// true
    }
}
