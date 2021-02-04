package com.chenjj.base.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Favorites 实例是类型安全（ typesafe ）的 ： 当你向它请求 String 的时候 ， 它从来
 * 不会返回一个 Integer 给你。 同时它也是异构的（ heterogeneous ） ： 不像普通的映射，它
 * 的所有键都是不同类型的 。 因此，我们将 Favorites 称作类型安全的异构容器（ typesafe
 * heterogeneous container ） 。
 * <p>
 * favorites Map 的值类型只是 Object 。 换句话说， Map 并
 * 不能保证键和值之间的类型关系，即不能保证每个值都为它的健所表示的类型（
 * 就是指键与值的类型并不相同)。
 * 事实上， Java 的类型系统还没有强大到足以表
 * 达这一点 。 但我们知道这是事实，并在获取 favorite 的时候利用了这一点 。
 */
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    // Achieving runtime type safety with a dynamic cast
    /*public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }*/

    public <T> T getFavorite(Class<T> type) {
        // 使用cast方法将对象引用动态地转换（ dynamically cast ）成了 Class 对象所表示的类型 。
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        System.out.printf("%s %x %s%n", favoriteString,
                favoriteInteger, favoriteClass.getName());
    }
}
