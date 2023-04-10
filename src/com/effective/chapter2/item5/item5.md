# Effective java
##  Item 5 : 자원은 직접 명시하지 말고 의존 객체 주입을 사용하라

***

1. 의존 객체 주입(DI): 클래스가 사용하는 의존성을 외부에서 주입받도록
    설계합으로써, 클래스 간의 결합도를 낮추고 코드 유지보수성을 높입니다. 
<br>
ex)
```java
public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    // ... 기타 메서드
}
```
2. 인터페이스를 사용한 의존성 관리: 클래스가 인터페이스에 의존하게 하여
    구현을 교체할 수 있도록 한다.<br>이를 통해 다양한 구현을 쉽게 교체한거나 테스트를 위한
    구현을 제공 할 수 있다.
3. 팩토리 메서드 패턴과 Supplier:팩토리 메서드 패턴(Factory Method Pattern)은 팩토리를 추상화 한 후, <br>
 주입하는 구체 팩토리에 따라서 다른 프로덕트를 반환하는 패턴이다.<br> 
 이를 사용하면 의존관계 주입 시에 클라이언트 코드 변경 없이 유연한 변경이 가능하다. 

```java
    // DefaultDictionary의 모든 하위타입을 반환할 수 있는 dictionaryFactory들을 받는다.
public SpellChecker(Supplier<? extends DefaultDictionary> dictionaryFactory)
        {
        this.dictionary = dictionaryFactory.get();
        }

public static void main(String[] args) {
        // 람다를 통해서 DefaultDictionary 하위 타입의 인스턴스를 뭐든 넣어주면 된다.
        SpellChecker spellChecker = new SpellChecker(() -> new DefaultDictionary());
        }
```

***
싱글톤이나 정적 유틸리티 클래스를 사용하면 테스트하기 어렵고 멀테 쓰레드 환경에서 불리하다.

의존 객체 주입과 다형성을 이용해서 클라이언트 코드의 변경 없이 객체들을 조립할 수 있게 만들자.