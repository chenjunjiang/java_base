package com.chenjj.base.enum1;

import java.util.HashSet;
import java.util.Set;

/**
 * 现在假设有一个香草的数组，表示一座花园中的植物，你想要按照类型（一年生、多年
 * 生或者两年生植物）进行组织之后将这些植物列出来 。 如果要这么做，需要构建三个集合，
 * 每种类型一个，并且遍历整座花园，将每种香草放到相应的集合中 。将这些集
 * 合放到一个按照类型的序数进行索引的数组中来实现这一点。
 * 这种实现方式的问题在于，当你访问一个按照枚举的序数进行索引的数组时，
 * 使用正确的 int 值就是你的职责了； int 不能提供枚举的类型安全 。
 * 你如果使用了错误的值，程序就会悄悄地完成错误的工作，或者幸运的话，
 * 会抛出 ArrayindexOutOfBou口dExceptio口异常 。
 * 用EnumMap来实现更好，参见：Plant1
 */
public class Plant {
    enum LifeCycle {
        ANNUAL, PERENNIAL, BIENNIAL
    }

    final String name;
    final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {
                new Plant("Basil", LifeCycle.ANNUAL),
                new Plant("Carroway", LifeCycle.BIENNIAL),
                new Plant("Dill", LifeCycle.ANNUAL),
                new Plant("Lavendar", LifeCycle.PERENNIAL),
                new Plant("Parsley", LifeCycle.BIENNIAL),
                new Plant("Rosemary", LifeCycle.PERENNIAL)
        };

        Set<Plant>[] plants = new Set[LifeCycle.values().length];
        for (int i = 0; i < plants.length; i++) {
            plants[i] = new HashSet<>();
        }
        for (Plant plant : garden) {
            plants[plant.lifeCycle.ordinal()].add(plant);
        }
        for (int i = 0; i < plants.length; i++) {
            System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plants[i]);
        }
    }
}
