## 프로젝트 준비

메이븐 프로젝트의 pom.xml 파일에 아래와 같이 aspectjweaver 의존을 추가한다. 이 모듈은 스프링이 AOP를 구현할 때 사용하는 모듈이다.

```xml
<dependencies>
    <dependency>
        <groupld>org.springframework</groupld>
        <artifactld>spring-context</artifactld>
        <version>5.0.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactld>aspectjweaver</artifactld>
        <version>1.8.13</version>
    </dependency>
</dependencies>
```

스프링 프레임워크의 AOP 기능은 spring-aop 모듈이 제공하는데 spring-context 모듈을 의존 대상에 추가하면 spring-aop 모듈도 함께 의존 대상에 포함된다. 따라서 spring-aop 모듈에 대한 의존을 따로 추가하지 않아도 된다. aspectjweaver 모듈은 AOP를 설정하는데 필요한 어노테이션을 제공하므로 이 의존을 추가해야 한다

### 예제 파일

```java
public interface Calculator {
	public long factorial(long num);
}
```

```java
public class ImpleCalculator implements Calculator{

	@Override
	public long factorial(long num) {
		long result = 1;
		for(long i=1; i<=num;i++) {
			result *= i;
		}
		return result;
	}
	
}
```

```java
public class RecCalculator implements Calculator{

	@Override
	public long factorial(long num) {
		if(num==0) {
			return 1;
		}
		else {
			return num * factorial(num-1);
		}
	}
	
}
```
