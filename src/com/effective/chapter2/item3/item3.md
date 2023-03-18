# Effective java
## Item 3 : private 생성자나 열거 타입으로 싱글턴임을 보증하라

***
### ___싱글턴___
싱글턴은 오직 하나의 인스턴스만 생성할 수 있는 클래스를 말한다.  
싱글턴을 만드는 방식으론 private 생성자 혹은 열거타입을 사용하는 방법이 있다.
<br></br>
private 타입을 이용할 경우 readResolve() 메소드를 재정의 하지 않으면    
직렬화, 역직렬화 과정에서 싱글턴을 보장할 수 없다는 문제점이 있다.

***
### 예시코드
readReolve() x
```java
    import java.io.Serializable;

    public class SingleV1 implements Serializable {
        private static final SingleV1 INSTANCE = new SingleV1();

        private SingleV1() {
        }
        public static SingleV1 getInstance = INSTANCE;
}
```
readReolve() o
```java
    import java.io.Serializable;

    public class SingleV2 implements Serializable {

        private static final SingleV2 INSTANCE = new SingleV2();
        
        private SingleV2() {
        }
        public static SingleV2 getInstance = INSTANCE;

        private Object readResolve(){
            return INSTANCE;
        }
}
```

enum
```java
    public enum EnumSingle {
    INSTANCE;
    }

```
test
```java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SingleMain {
    //직렬화
    public static byte[] serialize(Object instance) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (bos; ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(instance);
        } catch (Exception e) {
            // ... 구현 생략
        }
        return bos.toByteArray();
    }
    //역직렬화
    public static Object deserialize(byte[] serializedData) {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
        try (bis; ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        } catch (Exception e) {
            // ... 구현 생략
        }
        return null;
    }
    public static void main(String[] args) {
        //v1 readResolve x
        SingleV1 single = SingleV1.getInstance;
        byte[] serializeSingle = serialize(single);
        SingleV1 deserializeSingle = (SingleV1) deserialize(serializeSingle);
        System.out.println(single == deserializeSingle); 
        //false

        //v2 readResolve o
        SingleV2 single2 = SingleV2.getInstance;
        byte[] serializeSingle2 = serialize(single2);
        SingleV2 deserializeSingle2 = (SingleV2) deserialize(serializeSingle2);
        System.out.println(single2 == deserializeSingle2); 
        //true

        //enum
        EnumSingle enumSingle = EnumSingle.INSTANCE;
        byte[] serializeEnum = serialize(enumSingle);
        EnumSingle deserializeEnum = (EnumSingle) deserialize(serializeEnum);
        System.out.println(enumSingle ==deserializeEnum); 
        //true
    }
}
```
위 코드를 통해 역직렬화 과정에서 readResolve 혹은 enum을 사용하지 않을 경우  
싱글톤클래스도 새로운 인스턴스를 만들어 싱글톤을 보장하지 못하는 모습을 확인 할 수 있었다.

