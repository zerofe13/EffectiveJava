# Effective java
## Item 2 : 생성자에 매개변수가 많다면 빌더를 고려하라

***
### 생성자에 매개변수가 많을때의 문제점
- 매개변수에 따라 생성자를 확장하기에 어려움이 발생한다.  

```java
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
```
### 자바빈즈 패턴의 문제점  

- final을 사용할수 없어 불변으로 만들수 없다.
- 객체 하나를 만들기 까지 메서드를 여러 개 호출해야 하며 이과정에서 일관성이 무너진 상태에 놓이게 된다.

```java

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
```
***
### ___빌더 패턴___
- 빌더 패턴을 사용하면 점층적 생서자 패턴의 장점인 일관성, final  
 자바빈즈 패턴의 장점인 확장성 모두 취할 수 있다.
```java
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

```

***
### Builder 를 정적 내부 클래스로 만들어야 하는이유
중첩클래스 에는 static inner class 와 inner class 가 존재한다  
static inner class의 경우 inner class와 달리 외부 클래스와 독립적으로  
존재하기 때문에 inner class 의 문제점인 메모리 누수로 부터 자유롭다.  
또한 Builder 룰 static inner class 로 만들경우 응집도 올라가고  
namespace 충돌을 방지할수있다.