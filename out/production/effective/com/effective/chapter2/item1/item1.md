# Effective java
## Item 1 : 생성자 대신 정적 팩터리 메서드를 고려하라

***
### ___정적 메서드 팩터리 메서드란?___  

> 클래스의 인스턴스를 반환하는 단순한 정적 메서드  

```java
    public static Boolean valueOf(boolean b){
            return b ? Boolean.TRUE : Boolean.FALSE;
        }
```
***
### ___정적 팩터리의 장점___
#### 1. 이름을 가질 수 있다.  
    이름을 가질수 없는 생성자와 다르게 이름을 가질 수 있는 정적 팩터리 메서드는 의도를 더욱 명확하게 전달할 수 있다. 
    ex) BigInteger(int, int , Randeom),   BigInteger.probalePrime  

#### 2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.  
    정적 팩터리 메서드를 이용하면 불필요한 객체 생성을 피할 수 있으며 인스턴스를 철저히 통제 할수 있다.

#### 3. 반환 타입의 하위 타입 객체를 반환할 수 있다.
    정적 팩터리 메서드는 반환 타입의 하위 타입 객체를 반환할 수 있다 이는 다양한 구현 클래스를 가질 수 있어 유연함을 제공한다.
 
#### 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다
    정적 팩터리 메서드는 인자에 따라 다양한 클래스의 객체를 반환할 수 있다.  
    유연성을 증가시키며, 클라이언트 코드에 영향을 주지 않고 구현을 변경할 수 있다.  

#### 5. 작성 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
    정적 팩터리 메서드는 서비스 제공자 프레임워크(Service Provider Framework)를 구현할 수 있다.  
    이를 통해 객체 생성을 미루거나, 적절한 구현을 결정할 수 있습니다.
***

### ___정적 팩터리의 단점___
>상속을 사용하는 경우, 정적 팩터리 메서드만 제공하면 하위 클래스를 만들기 어렵다.
생성자가 없는 클래스를 상속하는 것은 어렵기 때문입니다.
***

### 예시
```java
    static <E> List<E> of(E... elements) {
        switch (elements.length) { // implicit null check of elements
        case 0:
        return ImmutableCollections.emptyList();
        case 1:
        return new ImmutableCollections.List12<>(elements[0]);
        case 2:
        return new ImmutableCollections.List12<>(elements[0], elements[1]);
        default:
        return new ImmutableCollections.ListN<>(elements);
            }
        }
    
    List<Integer>.of(1,2,3)
```




    