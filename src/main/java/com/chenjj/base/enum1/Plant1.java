package com.chenjj.base.enum1;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class Plant1 {
    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

    final String name;
    final LifeCycle lifeCycle;

    Plant1(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant1[] garden = {
                new Plant1("Basil", LifeCycle.ANNUAL),
                new Plant1("Carroway", LifeCycle.BIENNIAL),
                new Plant1("Dill", LifeCycle.ANNUAL),
                new Plant1("Lavendar", LifeCycle.PERENNIAL),
                new Plant1("Parsley", LifeCycle.BIENNIAL),
                new Plant1("Rosemary", LifeCycle.PERENNIAL)
        };

        Map<Plant1.LifeCycle, Set<Plant1>> plantsByLifeCycle =
                new EnumMap<>(Plant1.LifeCycle.class);
        for (Plant1.LifeCycle lc : Plant1.LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }
        for (Plant1 p : garden) {
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        }
        System.out.println(plantsByLifeCycle);

        // 按lifeCycle分组，默认用HashMap存储，key是lifeCycle，value是List，collect的返回值是HashMap
        // Naive stream-based approach - unlikely to produce an EnumMap!
        System.out.println(Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle)));

        // 按lifeCycle分组，用EnumMap存储，key是lifeCycle，value是Set，collect的返回值是EnumMap
        // Using a stream and an EnumMap to associate data with an enum
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet())));
    }
}
