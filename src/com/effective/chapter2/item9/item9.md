# Effective java
## item 9 : try-finally 보다는 try-with-resources를 사용하라 
***

### try-finally

자바에서 반드시 close() 메서드를 실행줘야하는 객체의 경우 try-finllay를 이용하여
close를 실행할수 있다. 

```java
public static String inputString() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
        return br.readLine();
    } finally {
        br.close();
    }
}
```
하지만 try-finally 에는 몇가지 문제점이 있다.<br>
우선 중첩으로 사용하게 되면 가독성이 떨어지게 된다.
```java
public static void inputAndWriteString() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
             String line = br.readLine();
            bw.write(line);
        } finally {
            bw.close();
        }
    } finally {
        br.close();
    }
}
```
또한 try 블록과 finally영역에서 동시에 에러가 발생한다면 try 영역에서 발생한 에러가
무시될수도 있다.

***
### try-with-resources

try-with-resources 는 위에 try-finally에서 발생한 문제점을 해결할 수 있다.
```java
public static String inputString() throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStream(System.in))) {
        return br.readLine();
    }
}
```

훨씬 가독성이 좋아진 모습을 볼수있다. 또한 try-with-resource는 try영역에서 발생하는 에러와,close() 발생할수 있는 문제를 모두 보여준다.
<br><br>
이러한 try with resources를 사용하기 위해선 AutoCloseable 인터페이스를 상속받아 close()메서드르 재정의 해야한다.

객체를 반드시 해제시켜줘야한다며 try-with-resources를 고려해보도록하자.