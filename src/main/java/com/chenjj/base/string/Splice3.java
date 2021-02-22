package com.chenjj.base.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Benchmark       Mode  Cnt     Score    Error  Units
 * Splice3.test1  thrpt    5     0.696 ±  0.060  ops/s
 * Splice3.test2  thrpt    5  1102.138 ± 61.541  ops/s
 * Splice3.test3  thrpt    5  1312.892 ± 54.015  ops/s
 *
 * 可以通过javap -c 反编译查看：
 * D:\Java\jdk1.8.0_162\bin\javap.exe -c com.chenjj.base.string.Splice3
 * Compiled from "Splice3.java"
 * public class com.chenjj.base.string.Splice3 {
 *   public com.chenjj.base.string.Splice3();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]) throws org.openjdk.jmh.runner.RunnerException;
 *     Code:
 *        0: new           #2                  // class org/openjdk/jmh/runner/options/OptionsBuilder
 *        3: dup
 *        4: invokespecial #3                  // Method org/openjdk/jmh/runner/options/OptionsBuilder."<init>":()V
 *        7: ldc           #4                  // class com/chenjj/base/string/Splice3
 *        9: invokevirtual #5                  // Method java/lang/Class.getSimpleName:()Ljava/lang/String;
 *       12: invokevirtual #6                  // Method org/openjdk/jmh/runner/options/OptionsBuilder.include:(Ljava/lang/String;)Lorg/openjdk/jmh/runner/options/ChainedOptionsBuilder;
 *       15: invokeinterface #7,  1            // InterfaceMethod org/openjdk/jmh/runner/options/ChainedOptionsBuilder.build:()Lorg/openjdk/jmh/runner/options/Options;
 *       20: astore_1
 *       21: new           #8                  // class org/openjdk/jmh/runner/Runner
 *       24: dup
 *       25: aload_1
 *       26: invokespecial #9                  // Method org/openjdk/jmh/runner/Runner."<init>":(Lorg/openjdk/jmh/runner/options/Options;)V
 *       29: invokevirtual #10                 // Method org/openjdk/jmh/runner/Runner.run:()Ljava/util/Collection;
 *       32: pop
 *       33: return
 *
 *   public void test1(org.openjdk.jmh.infra.Blackhole);
 *     Code:
 *        0: ldc           #11                 // String
 *        2: astore_2
 *        3: iconst_0
 *        4: istore_3
 *        5: iload_3
 *        6: ldc           #12                 // int 100000
 *        8: if_icmpge     37
 *       11: new           #13                 // class java/lang/StringBuilder
 *       14: dup
 *       15: invokespecial #14                 // Method java/lang/StringBuilder."<init>":()V
 *       18: aload_2
 *       19: invokevirtual #15                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 *       22: ldc           #16                 // String s
 *       24: invokevirtual #15                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 *       27: invokevirtual #17                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 *       30: astore_2
 *       31: iinc          3, 1
 *       34: goto          5
 *       37: aload_1
 *       38: aload_2
 *       39: invokevirtual #18                 // Method org/openjdk/jmh/infra/Blackhole.consume:(Ljava/lang/Object;)V
 *       42: return
 *
 *   public void test2(org.openjdk.jmh.infra.Blackhole);
 *     Code:
 *        0: new           #19                 // class java/lang/StringBuffer
 *        3: dup
 *        4: invokespecial #20                 // Method java/lang/StringBuffer."<init>":()V
 *        7: astore_2
 *        8: iconst_0
 *        9: istore_3
 *       10: iload_3
 *       11: ldc           #12                 // int 100000
 *       13: if_icmpge     29
 *       16: aload_2
 *       17: ldc           #16                 // String s
 *       19: invokevirtual #21                 // Method java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
 *       22: pop
 *       23: iinc          3, 1
 *       26: goto          10
 *       29: aload_1
 *       30: aload_2
 *       31: invokevirtual #18                 // Method org/openjdk/jmh/infra/Blackhole.consume:(Ljava/lang/Object;)V
 *       34: return
 *
 *   public void test3(org.openjdk.jmh.infra.Blackhole);
 *     Code:
 *        0: new           #13                 // class java/lang/StringBuilder
 *        3: dup
 *        4: invokespecial #14                 // Method java/lang/StringBuilder."<init>":()V
 *        7: astore_2
 *        8: iconst_0
 *        9: istore_3
 *       10: iload_3
 *       11: ldc           #12                 // int 100000
 *       13: if_icmpge     29
 *       16: aload_2
 *       17: ldc           #16                 // String s
 *       19: invokevirtual #15                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 *       22: pop
 *       23: iinc          3, 1
 *       26: goto          10
 *       29: aload_1
 *       30: aload_2
 *       31: invokevirtual #18                 // Method org/openjdk/jmh/infra/Blackhole.consume:(Ljava/lang/Object;)V
 *       34: return
 * }
 * 我们发现，test1方法每次循环都会创建StringBuilder，这样做性能肯定低下。
 * 而test2和test3方法只会创建一个StringBuffer和StringBuilder实例，性能肯定好很多。
 * StringBuffer的效率比StringBuilder低些，这是由于StringBuffer使用了synchronized
 * 来实现线程安全，效率较低也是不可避免的。
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
public class Splice3 {
    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder().include(Splice3.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void test1(Blackhole blackhole) {
        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += "s";
        }
        blackhole.consume(s);
    }

    @Benchmark
    public void test2(Blackhole blackhole) {
        StringBuffer s1 = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            s1.append("s");
        }

        blackhole.consume(s1);
    }

    @Benchmark
    public void test3(Blackhole blackhole) {
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            s2.append("s");
        }
        blackhole.consume(s2);
    }
}
