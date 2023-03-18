package com.effective.chapter2.item2;

public class NutritionFactsV3 {
    // 불변성,일관성,확장 모든 이점을 취할수 있다
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    //Builder 중첩클래스
    public static class Builder{
        private final int servingSize;
        private final int servings;
        private int calories =0;
        private int fat =0;
        private int sodium =0;
        private int carbohydrate=0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }
        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }
        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }
        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }
        public NutritionFactsV3 build(){
            return new NutritionFactsV3(this);
        }
    }
    private NutritionFactsV3(Builder builder){
        servingSize = builder.servings;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
