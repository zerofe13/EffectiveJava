# Effective java

## item 8 : finalizer와 cleaner 사용을 피하라
***

자바에서는 객체를 소멸하기 위해 finalizer 와 cleaner 을 제공해준다.<br>
하지만 finalizer 와 cleaner 는 여러가지 문제가 존재하며 __자원 회수를 보장하지 않는다.__<br>
혹시나 개발자가 참조를 회수 하지 않을 상황에 대비하는 안전망으로 생각하자.

***
#### 1. 수행을 보장하지 않는다.
finalizer 와 cleaner 의 경우 사용시 GC의 수거 대상이 되긴하지만 수행을 보장하지 못한다.<br>
finalizer 와 cleaner 의 수거 수행 시점은 GC의 알고리즘에 의존하게 되고 GC의 구현마다 다르게 동작하게된다.
 또한 filnalizer 의 쓰레드의 경우 우선순위가 낮아 이러한 문제에 더욱 위험하다.

#### 2. finalizer 가 동작하는 도중 예외를 잡을수 없다.
finlizer 가 동작하는 도중 발생하는 예외는 무시되며 예외에 처리할 작업이 있더라도 그 순간 종료하게 된다.

#### 3. 성능이슈
finalizer, cleaner 을 사용하게 되면 try-with-resources 를 사용할때보다 훨씬 느리게 동작하게된다.

***

우리가 객체를 사용 후 gc 메모리를 수거하도록 하고싶다면
AutoCloseable 과 try-with-resource를 사용하자.
```java
public class Sample implements AutoCloseable {
    @Override
    public void close() {
        System.out.println("close");
    }
}
```
finalizer 와 cleaner 는 안전망이라 생각하고 객체를 소멸시키고 싶다면 직접하도록 하자.