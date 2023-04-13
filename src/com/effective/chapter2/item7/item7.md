# Effective java

## item 7 : 다 쓴 객체 참조를 회수하라
***

* 자바에는 GC가 메모리 회수 해주지만 예외가 존재하기때문에 다 쓴 객체 참조를 회수하면 메모리 누수를 막을수 있다.
```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
 // 해당 요소가 pop 되지먄 여전히 stack에서 해당 요소를 참조하고있다.
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

}
```
위 Stack 에는 메모리 누수가 발생한다.<br>
pop() 을 할시 사이즈가 줄어들지만 Stack은 여전히 사용이 끝난 elements를 참조하고있다.
때문에 __GC는 elements 를 비롯하여 stack을 회수하지 못하게된다__.

```java
public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }
```
메모리를 해제하는 가장 간단한 방법은<br>
이처럼 직접 다쓴 elements를 null 로 만들어줘 참조를 해제 해줌으로써 GC가 메모리를
수거해 가 도록 하는 방법이 있지만 이는 NPE 이 발생할 수 있음으로 신중하게 사용하도록 하자.

<br> 
메모리를 해제하는 가장 좋은 방법은 무엇일까?<br>
참조를 담은 변수의 유효범위 (scope) 를 이용하는 방식이다. 해당 변수의 유효범위가 
벗어나게 된다면 GC의 내부 로직으로 인해 해당 메모리를 수거해가게 된다.

***
### 캐시를 이용할때 메모리 누수를 주의하자

캐시를 사용하다 보면 더이상 사용하지 않는 값을 그대로 방치하는 경우가 종종 발생한다.
이는 메모리 누수의 원이이 될 수 있다.
```java
Object key1 = new Object();
Object value1 = new Object();

Map<Object, List> cache = new HashMap<>();
cache.put(key1, value1);
```
위 소스에서 key1 변수가 사용이 끝나게 되더라도 cache 가 계속 해당 객체를 참조하게되고
이는 메모리 누수의 원인이 된다.
이를 해결하기위한 방법으론 약한참조(weakReference)가 있다
```java
Object key1 = new Object();
Object value1 = new Object();

Map<Object, List> cache = new WeakHashMap<>();
cache.put(key1, value1);
```
약한 참조의 경우 key1변수의 사용이 끝나게 되면 자동으로 메모리가 해제된다.