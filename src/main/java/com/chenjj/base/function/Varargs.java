package com.chenjj.base.function;

/**
 * 在重视性能的情况下，使用可变参数机制要特别小心 。 每次调用可变参数方法都会导
 * 致一次数组分配和初始化。 如果凭经验确定无法承受这一成本，但又需要可变参数的灵活
 * 性，还有一种模式可以让你如愿以偿 。 假设确定对某个方法 95% 的调用会有 3 个或者更少
 * 的参数，就声明该方法的 5 个重载，每个重载方法带有 0 至 3 个普通参数，当参数的数目超
 * 过 3 个时，就使用一个可变参数方法。
 * EnumSet 类对它的静态工厂(of方法)使用了这种方法，最大限度地减少创建枚举集合的成本 。
 */
public class Varargs {
    // Simple use of varargs，可变参数
    static int sum(int... args) {
        int sum = 0;
        for (int arg : args)
            sum += arg;
        return sum;
    }

    /**
     * 这样使用可变参数有问题：
     * 如果客户端调用这个方法时，并没有传递参数进去，它就会在运行时而不是编译时发生失败 。
     * 另一个问题是，这段代码很不美观。你必须在 args 中包含显式的有效性检查，除非将 min 初始化为
     * Integer.MAX VALUE，否则将无法使用 for-each 循环，这样的代码也不美观。
     *
     * @param args
     * @return
     */
    /*static int min(int... args) {
        if (args.length == 0)
            throw new IllegalArgumentException("Too few arguments");
        int min = args[0];
        for (int i = 1; i < args.length; i++)
            if (args[i] < min)
                min = args[i];
        return min;
    }*/

    /**
     * 这种做就比上面的方法好很多了
     *
     * @param firstArg
     * @param args
     * @return
     */
    static int min(int firstArg, int... args) {
        int min = firstArg;
        for (int arg : args)
            if (arg < min)
                min = arg;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(min(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

}
