## 스프링 AOP 구현

#### 스프링 AOP를 이용한 공통 기능 구현 방법

1. Aspect로 사용할 클래스에 @Aspect 어노테이션을 붙인다.
2. @Pointcut 어노테이션으로 공통 기능을 적용할 Pointcut을 정의한다.
3. 공통 기능을 구현한 메서드에 @Around 어노테이션을 적용한다.

### @Aspect, @Pointcut, @Around를 이용한 AOP 구현

공통 기능을 제공하는 Aspect 구현 클래스를 만들고 자바 설정을 이용해서 Aspeect을 어디에 적용할지 설정하면 된다. Aspect는 @Aspect 어노테이션을 이용해서 구현한다. 프록시는 스프링 프레임워크가 알아서 만들어준다.

```java
@Aspect    // 공통 기능 정의
public class ExeTimeAspect {
    @Pointcut("execution(public * chap07..*(..))")    // 공통기능 적용대상 설정, public으로 시작하는 메소드
    public void publicTarget() {

    }

    /**
     * getSignature() : 호출한 메서드의 시그니처
     * getTarget() : 대상 객체
     * getArgs() : 인자 목록
     * 
     * @param joinPoint : 프록시의 대상 객체
     * @return
     * @throws Throwable
     */
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();    // 공통 기능의 예시, 시간측정
        try {
            Object result = joinPoint.proceed();    // 실제 대상 객체의 메서드 호출
            return result;
        }finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                                joinPoint.getTarget().getClass().getSimpleName(),
                                sig.getName(),Arrays.toString(joinPoint.getArgs()),
                                (finish-start));
        }


    }
}
```

- @Aspect 어노테이션 : @Aspect 어노테이션을 적용한 클래스는 Advice와 Pointcut을 함께 제공한다.
- @Pointcut 어노테이션 : @Pointcut은 공통 기능을 적용할 대상을 설정한다.
- @Around 어노테이션 : Around Advice를 설정한다. @Around 어노테이션의 인자값이 "publicTarget()"인데 이는 publicTarget() 메서드에 정의한 Pointcut에 공동 기능을 적용한다는 것을 의미한다.
- ProceedingJoinPoint 타입 파라미터 : 프록시의 대상 객체
- joinPoint.proceed() : 실제 대상 객체의 메서드를 호출

#### 스프링 설정 클래스

```java
@Configuration
@EnableAspectJAutoProxy
public class AppCtx {
    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
```

@Aspect 어노테이션을 붙인 클래스를 공통 기능으로 적용하려면 스프링 설정 클래스(AppCtx)에 설정해야 한다. @EnableAspectJAutoProxy 어노테이션을 추가하면 스프링은 @Aspect 애노테이션이 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut 설정과 @Around 설정을 사용한다.

```java
public class MainAspect {
    public static void main(String args[]) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        long fiveFact = cal.factorial(5);
        System.out.println("cal.factorial(5) = " + fiveFact);
        System.out.println(cal.getClass().getName());
        ctx.close();
    }
}
```

#### 실행 결과

> RecCalculator.factorial([5]) 실행 시간 : 13035300 ns<br>
> cal.factorial(5) = 120<br>
> com.sun. proxy.$Proxy17

첫 번째 줄은 ExeTimeAspect 클래스의 measure() 메서드가 출력한 것이다.

#### measure() 메서드의 실행 과정

<p align="center"><img src="https://i.postimg.cc/bJrpZgxR/image.png"></p>

### ProceedingJoinPoint의 메서드

Around Advice에서 사용할 공통 기능 메서드는 대부분 파라미터로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출하면 된다.

호출되는 대상 객체에 대한 정보, 실행되는 메서드에 대한 정보, 메서드를 호출할 때 전달된 인자에 대한 정보가 필요할 때가 있다.

#### ProceedingJoinPoint 인터페이스의 제공 메서드

- Signature getSignature() : 호출되는 메서드에 대한 정보를 구한다
- Object getTarget() : 대상 객체를 구한다
- Ojbect[] getArgs() : 파라미터 목록을 구한다.

#### org.aspectj.lang.Signature 인터페이스 제공 메서드

- String getName() : 호출되는 메서드의 이름을 구한다.
- String toLongString() : 호출되는 메서드를 완전하게 표현한 문장을 구한다(메서드의 리턴 타입, 파라미터 타입이 모두 표시된다)
- String toShortString() : 호출되는 메서드를 축약해서 표현한 문장을 구한다(기본 구현은 메서드의 이름만을 구한다.)
