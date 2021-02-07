package com.chenjj.base.function;

import java.util.*;

public class CollectionClassifier {
    public static String classify(Set<?> set) {
        return "Set";
    }

    public static String classify(List<?> list) {
        return "List";
    }

    public static String classify(Collection<?> collection) {
        return "Unknown Collection";
    }

    /**
     * 三次都打印出Unknown Collection
     * 因为 classify方法被重载（ overloaded ）了，而要调用哪个重载方法是在编译时做出决定的。
     * 对于重载方法的选择是静态的，而对于被覆盖的方法的选择则是动态的 。参见Overriding。
     * 安全而保守的策略是，永远不要有两个具有相同参数数量的重载方法 。
     * 这项限制并不麻烦，因为你始终可以给方法起不同的名称，而不使用重载机制 。
     * 例如，以 ObjectOutputStream 类为例 。 对于每个基本类型，以及几种引用类型，它
     * 的 write 方法都有一种变形 。 这些变形方法并不是重载 write 方法，而是具有诸如 write
     * Boolean(boolean ） 、 writeint(int ）和 writeLong(long ）这样的签名 。 与重载方案
     * 相比较，这种命名模式带来的好处是，可以提供相应名称的读方法，比如 readBoolean （）、
     * readint （）和 readLong （） 。 实际上， ObjectinputStream 类正是提供了这样的读方法。
     * @param args
     */
    public static void main(String[] args) {
        Collection<?>[] collections = {new HashSet<String>(), new ArrayList<String>(),
                new HashMap<String, String>().values()};
        for (Collection<?> collection : collections) {
            System.out.println(classify(collection));
        }
    }

}
