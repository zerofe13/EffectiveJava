# Effective java

## 불필요한 객체 생성을 피하라
***
* 자바 프로그램의 성능과 메모리 사용을 최적화하기 위해, 불필요한 객체 생성을 피하는 것이 중요하다.

이책에서는 불필요한 객체 생성을 피하기 위해 다음과 같은 방법을 제시해주고 있다.

***
1. __불변 객체 재사용__<br>
불변 객체(값이 변경되지 않는 객체)는 자유롭게 재사용할 수 있다.<br>
예를 들어, String 객체는 불변이므로, 문자열 리터럴을 직접 사용하는 것이 객체 생성을 줄일 수 있는 방법이다.
```java
// new 연산자를 이용한 방식 매번 새로운 인스턴스가 생성
        String str1 = new String("hello");

// 리터럴을 이용한 방식 String constant pool을 사용
        String str2 = "hello";
        
```
2. __정적 팩토리 메서드 사용__<br>
생성자를 대신하여 정적 팩토리 메서드를 이용하면 불필요한 객체 생성을 막을수 있다. 
```java
 public static Boolean valueOf(String s) {
        return parseBoolean(s) ? TRUE : FALSE;
    }
```
3. __오토박싱 주의__<br>
오토박싱은 편리하지만, 불필요한 객체 생성이 발생할 수 있다. <br>
박싱된 기본 타입보다는 기본 타입을 사용하고, 의도치 않은 오토박싱이 숨어들지 않도록 주의하자.
```java
private static long sum() {
	Long sum = 0L;
	for(long i=0; i<=Integer.MAX_VALUE; i++) {
		sum += i;
	}
	return sum;
}
```
***
객체생성의 비용이 비싸니 무조건 생성을 피해야 한다고 오해해서는 안된다.
객체를 생성하고 gc가 이를 회수하는 것은 그렇게 부담이가는 작업이 아니다.
때문에 프로그램의 명확성과 간결성을 위해서라면 오히려 객체를 새로 생성하는것이
더욱 권장되며 방어적 복사가 필요한 상황에 객체를 재사용하게 되면 더 큰 피해가 생기게 되므로
상황에 맞게 사용하도록하자