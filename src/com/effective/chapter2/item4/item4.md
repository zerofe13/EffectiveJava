# Effective java
## Item 4 : 인스턴스화를 막으려거든 private 생성자를 사용하라

***

### 유틸리티 클래스
java.lang.Math 혹은 java.util.Arrays 와 같이 정적 메서드로만 구성된 클래스를 뜻하는 단어이다.  
유틸리티 클래스는 메서드를 제공하는 것이 목적이기 때문에 인스턴스화를 해줄 이유가 없다. 때문에  
이러한 유틸리티 클래스는 private 을 이용하여 인스턴스화 를 막아준다.

***
인스턴스화를 막고자 할땐 유틸리티 클래스 처럼 private 생성자를 추가하여 인스턴화를 막아주도록 하자  
추가적으로 private 생성자를 사용하면 상속을 불가능하게 하는 효과도 있다. 모든 생성자는 상위 클래스의   
생성자를 호출기 때문이다.