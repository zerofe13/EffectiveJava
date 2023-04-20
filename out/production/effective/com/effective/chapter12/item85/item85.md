# Effective java
## item 85 : 자바 직렬화의 대안을 찾아라
***
#### 직렬화란?
자바에서 직렬화란 객체를 바이트스트림으로 만들어 다른환경(DB, File, socket)에서 사용할수 있도록
만들어 주는 것을 말한다.

구현방법은 Serializable 인터페이스만 상속받으면 된다. 또한 Serializable은 마커 인터페이스 임으로
따로 구현 할 내용은 없다.

```java

public class SingleV1 implements Serializable {
    private static final SingleV1 INSTANCE = new SingleV1();
    private SingleV1() {
    }
    public static SingleV1 getInstance = INSTANCE;
    
}
```
***

#### 직렬화의 문제

1. __보이지 않는 생성자__  
ObjectOutputStream 의 readObject() 는 보이지 않는 생성자다.
역직렬화시 readObject 메서드가 실행되며 이 과정에서
생성자에서 특정 조건을 사용하거나(싱글톤) 생성자를 이용한 어떤 일을 처리할때
예상하지 못한 오류가 발생할 수 있다.
<br><br>
2. __역직렬화 폭탄__  
역직렬화에 시간이 오래 걸리는 짧은 스트림을 역직렬화 폭탄이라고 한다.

```java
static byte[] bomb() {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();

    for (int i=0; i < 100; i++) {
        Set<Object> t1 = new HashSet<>();
        Set<Object> t2 = new HashSet<>();

        t1.add("foo"); // t1을 t2과 다르게 만든다.
        s1.add(t1); s1.add(t2);

        s2.add(t1); s2.add(t2);
        s1 = t1; s2 = t2;
    }
    return serialize(root);
}
```
역질렬화는 각원소의 해시코드를 계산한다.
위 소스에서 root 원소는 또다른 HashSet을 가지고 
이 HashSet 또한 다른 HashSet을 가진다. 게다가 반복문을 100번 수행하게 됨으로

2^^100 번의 해시코드 메서드를 호출하게된다.

***
#### 대안
가능하다면 직렬화를 사용하지말고 
JSON, 프로토콜 버퍼를 사용하다록 하자.
그리고 어쩔수 없이 직렬화를 해야한다면 믿을만한 데이터인지 확인하고
사용하도록 하자.