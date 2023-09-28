## @Component
컴포넌트 스캔은 스프링이 직접 클래스를 검색해서 빈으로 등록해주는 기능이다. 설정 클래스에서 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록할 수 있으므로 컴포넌트 스캔 기능을 사용하면 설정 코드 양이 줄어든다.
```java
@Component
public class MemberDao{
	...
}
```
`@Component("infoPrinter")`로 속성값을 지정하면 빈으로 등록될 때 이름을 지정할 수 있다.
```java
@Component("infoPrinter")
public class MemberInfoPrinter{
	...
}
```
©Component 어노테이션에 값을 주었는지에 따라 빈으로 등록할 때 사용할 이름이 결정된다.

어노테이션에 값을 주지 않는다면 클래스 이름의 첫 글자를 소문자로 바꾼 이름을 빈 이름으로 사용한다. 만약 어노테이션에 값을 주었다면 그 값을 빈 이름으로 사용한다.
