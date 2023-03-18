package com.effective.chapter2.item2;

public class NutritionFactsV1 {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFactsV1(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    //매개 변수에 따라 생성자를 확장하기에 불편하다.
    public NutritionFactsV1(int servingSize, int servings){
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = 0;
        this.fat = 0;
        this.sodium = 0;
        this.carbohydrate = 0;
    }
}
