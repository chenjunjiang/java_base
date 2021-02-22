package com.chenjj.base.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Benchmark                       Mode  Cnt    Score    Error  Units
 * PrimitiveCovertToString.test1  thrpt    5   46.118 ± 37.679  ops/s
 * PrimitiveCovertToString.test2  thrpt    5  136.933 ±  2.421  ops/s
 * PrimitiveCovertToString.test3  thrpt    5  137.619 ±  2.006  ops/s
 * <p>
 * 可以通过javap -c 反编译查看：
 * D:\Java\jdk1.8.0_162\bin\javap.exe -c com.chenjj.base.string.PrimitiveCovertToString
 * Compiled from "PrimitiveCovertToString.java"
 * public class com.chenjj.base.string.PrimitiveCovertToString {
 * public com.chenjj.base.string.PrimitiveCovertToString();
 * Code:
 * 0: aload_0
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: return
 * <p>
 * public static void main(java.lang.String[]) throws org.openjdk.jmh.runner.RunnerException;
 * Code:
 * 0: new           #2                  // class org/openjdk/jmh/runner/options/OptionsBuilder
 * 3: dup
 * 4: invokespecial #3                  // Method org/openjdk/jmh/runner/options/OptionsBuilder."<init>":()V
 * 7: ldc           #4                  // class com/chenjj/base/string/PrimitiveCovertToString
 * 9: invokevirtual #5                  // Method java/lang/Class.getSimpleName:()Ljava/lang/String;
 * 12: invokevirtual #6                  // Method org/openjdk/jmh/runner/options/OptionsBuilder.include:(Ljava/lang/String;)Lorg/openjdk/jmh/runner/options/ChainedOptionsBuilder;
 * 15: invokeinterface #7,  1            // InterfaceMethod org/openjdk/jmh/runner/options/ChainedOptionsBuilder.build:()Lorg/openjdk/jmh/runner/options/Options;
 * 20: astore_1
 * 21: new           #8                  // class org/openjdk/jmh/runner/Runner
 * 24: dup
 * 25: aload_1
 * 26: invokespecial #9                  // Method org/openjdk/jmh/runner/Runner."<init>":(Lorg/openjdk/jmh/runner/options/Options;)V
 * 29: invokevirtual #10                 // Method org/openjdk/jmh/runner/Runner.run:()Ljava/util/Collection;
 * 32: pop
 * 33: return
 * <p>
 * public void test1(org.openjdk.jmh.infra.Blackhole);
 * Code:
 * 0: iconst_0
 * 1: invokestatic  #11                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 * 4: astore_2
 * 5: ldc           #12                 // int 1000000
 * 7: istore_3
 * 8: ldc           #13                 // String
 * 10: astore        4
 * 12: iconst_0
 * 13: istore        5
 * 15: iload         5
 * 17: iload_3
 * 18: if_icmpge     48
 * 21: new           #14                 // class java/lang/StringBuilder
 * 24: dup
 * 25: invokespecial #15                 // Method java/lang/StringBuilder."<init>":()V
 * 28: aload_2
 * 29: invokevirtual #16                 // Method java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
 * 32: ldc           #13                 // String
 * 34: invokevirtual #17                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 37: invokevirtual #18                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 * 40: astore        4
 * 42: iinc          5, 1
 * 45: goto          15
 * 48: aload_1
 * 49: aload         4
 * 51: invokevirtual #19                 // Method org/openjdk/jmh/infra/Blackhole.consume:(Ljava/lang/Object;)V
 * 54: return
 * <p>
 * public void test2(org.openjdk.jmh.infra.Blackhole);
 * Code:
 * 0: iconst_0
 * 1: invokestatic  #11                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 * 4: astore_2
 * 5: ldc           #12                 // int 1000000
 * 7: istore_3
 * 8: ldc           #13                 // String
 * 10: astore        4
 * 12: iconst_0
 * 13: istore        5
 * 15: iload         5
 * 17: iload_3
 * 18: if_icmpge     33
 * 21: aload_2
 * 22: invokestatic  #20                 // Method java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
 * 25: astore        4
 * 27: iinc          5, 1
 * 30: goto          15
 * 33: aload_1
 * 34: aload         4
 * 36: invokevirtual #19                 // Method org/openjdk/jmh/infra/Blackhole.consume:(Ljava/lang/Object;)V
 * 39: return
 * <p>
 * public void test3(org.openjdk.jmh.infra.Blackhole);
 * Code:
 * 0: iconst_0
 * 1: invokestatic  #11                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 * 4: astore_2
 * 5: ldc           #12                 // int 1000000
 * 7: istore_3
 * 8: ldc           #13                 // String
 * 10: astore        4
 * 12: iconst_0
 * 13: istore        5
 * 15: iload         5
 * 17: iload_3
 * 18: if_icmpge     33
 * 21: aload_2
 * 22: invokevirtual #21                 // Method java/lang/Integer.toString:()Ljava/lang/String;
 * 25: astore        4
 * 27: iinc          5, 1
 * 30: goto          15
 * 33: aload_1
 * 34: aload         4
 * 36: invokevirtual #19                 // Method org/openjdk/jmh/infra/Blackhole.consume:(Ljava/lang/Object;)V
 * 39: return
 * }
 * test1方法每次循环都会创建StringBuilder，这样做性能肯定低下。
 * test2和test3差不多，String.valueOf比Integer.toString()稍好。
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
public class PrimitiveCovertToString {
    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder().include(PrimitiveCovertToString.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    /**
     * 使用+""的方式把基本类型转换成字符串
     *
     * @param blackhole
     */
    @Benchmark
    public void test1(Blackhole blackhole) {
        Integer num = 0;
        int loop = 1000000;
        String s = "";
        for (int i = 0; i < loop; i++) {
            s = num + "";
        }
        blackhole.consume(s);
    }

    @Benchmark
    public void test2(Blackhole blackhole) {
        Integer num = 0;
        int loop = 1000000;
        String s = "";
        for (int i = 0; i < loop; i++) {
            s = String.valueOf(num);
        }
        blackhole.consume(s);
    }

    @Benchmark
    public void test3(Blackhole blackhole) {
        Integer num = 0;
        int loop = 1000000;
        String s = "";
        for (int i = 0; i < loop; i++) {
            s = num.toString();
        }
        blackhole.consume(s);
    }
}
