
# Effective java
## item 86 : Serializable 구현할지는 신중히 결정하라
***

#### Serializable 구현하면 수정이 어렵다.
>직렬화는 객체를 바이트스트림화 해 다른환경에서 사용하도록 하는 기술이다 때문에 이는 공개 api 처럼 릴리즈후 지속적으로 관리해줘야한다.

__1.캡슐화가 깨진다__

* 직렬화, 역직렬화 과정에서 __보이지 않는 생성자__ 를 사용하기 때문에 private 등의 접근제어자를 통한 정보 은닉 캡슐화가 깨지게 된다.

__2. serialVersionUID__

* 직렬화를 하게되면 serialVersionUID 객체에대한 정보(version)을 해쉬코드를 기반으로 serialVersionUID 에 저장하게된다.<br>
  객체의 구조가 바뀌게 되면 해쉬코드 역시 바뀌게 되고 serialVersionUID 가 바뀌어 역직렬화시 문제가 발생하게 된다.
<br><br>
* 역직렬화를 사용하게 된다면 위의 문제를 방지하기 위해 serialVersionUID 를 직접 관리하는 방법이 있다. 하지만 이방법 또한 완벽한 해결책이 아니다.
 예를 들어 변수가 추가되거나 혹은 탑이이 변경되게 된다면 변수에 null이 들어가거나 InvalidClassException 가 발생할것이다. 
<br><br>

***
위 문제점 뿐만 아니라 직렬화는 굉장히 넓은 범위에서 다양한 문제를 야기 시킬 수 있다.
때문에 DB,캐시 등에 장기간 저장되는 정보,혹은 자주 변경되는 정보는 직렬화를 지양하며 또한 개발자가 직접 컨트롤이 불가능한 객체의 경우 또한
직렬화를 피하는 것이좋다. 


