package com.chenjj.base.effective.build;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import static com.chenjj.base.effective.build.NyPizza.Size.SMALL;

public abstract class Pizza {
    public enum Topping {
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }

    final Set<Topping> toppings;

    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(Topping.SAUSAGE)
                .addTopping(Topping.ONION).build();
    }

    abstract static class Builder<T extends Builder<T>> {
        // 创建一个空的EnumSet
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
