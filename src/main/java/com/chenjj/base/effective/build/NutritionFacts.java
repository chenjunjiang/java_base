package com.chenjj.base.effective.build;

/**
 * NutritionFacts是不可变的
 * Builder 模式的确也有它自身的不足 。 为了创建对象 ，必须先创建它 的构建器 。 虽然创
 * 建这个构建器的开销在实践中可能不那么明显 但是在某些十分注重性能的情况下，可能就
 * 成问题了 。 Builder 模式还 比重叠构造器模式更加冗长 ，因此它只在有很多参数的时候才使
 * 用，比如 4 个或者更多个参数 。 但是记住，将来你可能需要添加参数。 如果一开始就使用构
 * 造器或者静态工厂，等到类需要多个参数时才添加构造器，就会无法控制，那些过时的构造
 * 器或者静态工厂显得十分不协调 。 因此，通常最好一开始就使用构建器 。
 */
public final class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
    }

    public static class Builder {
        // 必须参数
        private final int servingSize;
        private final int servings;

        // 可选参数
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }

    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
