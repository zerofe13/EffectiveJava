package com.effective.chapter2.item2;

public class NutritionFactsV2 {
    //자바빈즈 패턴에서는 변수에 final을 사용할수 없다.
    private int servingSize = -1;
    private int servings = -1;
    private int calories =0;
    private int fat =0;
    private int sodium =0;
    private int carbohydrate=0;

    public NutritionFactsV2() {
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
