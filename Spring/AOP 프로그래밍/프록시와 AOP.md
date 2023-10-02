## 프록시와 AOP

팩토리얼 메소드의 수행시간을 알고 싶다고 가정하자. 쉬운 방법은 메소드의 시작과 끝에서 시간을 구하고 이 두시간의 차이를 출력하는 것이다. 예를 들어 아래의 ImpleCalculator 클래스를 아래와 같이 수정하면 된다.

```java
public class ImpleCalculator implements Calculator{

    @Override
    public long factorial(long num) {
        long start = System.currentTimeMillis();
        long result  = 1;
        for(long i=1; i<=num;i++) {
            result *= i;
        }
        long end = System.currentTimeMillis();
        System.out.printf("ImpleCalculator.factorial(%d) 실행 시간 = %d\n", num, (end-start));
        return result;
    }

}
```

재귀적으로 팩토리얼을 구현한 메서드같은 경우 실행 시간의 출력을 구현하는데 복잡해진다. 왜냐하면 재귀적인 호출을 통해서 메서드 내에서 여러번의 실행 시간 출력이 실행되기 때문이다. 이러한 문제를 해결하기 위해서는 **프록시 객체**를 사용해야 한다.

```java
public class ExeTimeCalculator implements Calculator{
    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();

        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",delegate.getClass().getSimpleName(), num, (end-start));
        return result;
    }
}
```

```java
public class MainProxy {
    public static void main(String args[]) {
        ExeTimeCalculator tlCal1 = new ExeTimeCalculator(new ImpleCalculator());
        System.out.println(tlCal1.factorial(20));

        ExeTimeCalculator tlCal2 = new ExeTimeCalculator(new RecCalculator());
        System.out.println(tlCal2.factorial(20));
    }
}
```

- 위 결과에서 기존 코드를 변경하지 않고 실행 시간을 출력 가능하게 함.
- 실행 시간을 구하는 코드의 중복을 제거

**이렇게 핵심 기능의 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체를 프
록시(proxy)라고 부른다.**

#### 프록시 객체의 특징

1. 실제 핵심 기능을 실행하는 객체는 대상 객체
    - 위의 예제에서는 ExeTimeCalculator 객체가 프록시이고 ImpeCaculator 객체, RecCalculator 객체가 대상 객체이다.
2. 특징은 핵심 기능은 구현하지 않는 다는 점이다.
    - ExeTimeCalculator 클래스를 보면 팩토리얼 연산 자체를 구현하지 않고 다른 객체에게 위임하여 실행하는 것을 볼 수 있다.
3. 프록시는 핵심 기능을 구현하지 않는 대신 여러 객체에 공통으로 적용할 수 있는 기능을 구현한다.
    - 위의 예제에서 팩토리얼 메서드들의 실행 방법은 다르지만 실행 시간을 출력하는 동일한 핵심 기능이 필요하다. 따라서 프록시는 이러한 동일 기능을 구현한다.

**프록시와 AOP의 관계**

ImpeCaculator, RecCalculator 클래스의 팩토리얼 메소드와 같이 핵심 기능은 집중하고 프록시인 ExeTimeCalculator 클래스는 실행시간 측정이라는 공통 기능 구현에 집중하는 것처럼 **공통 기능 구현과 핵심 기능 구현을 분리하는 것이 AOP의 핵심이다.** 따라서 프록시와 AOP의 관계는 AOP가 제공하는 핵심적인 기능이 프록시라는 것이다.

### AOP

AOP는 Aspect Oriented Programming의 약자로, 여러 객체에 공통으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그래밍 기법이다. AOP는 핵심 기능과 공통 기능의 구현을 분리함으로써 핵심 기능을 구현한 코드의 수정 없이 공통 기능을 적용할 수 있게 만들어준다.

#### AOP의 기본 개념

핵심 기능에 공통 기능을 삽입하는 것이다. 즉 핵심 기능의 코드를 수정하지 않으면서 공통 기능의 구현을 추가하는 것이 AOP이다.

#### 핵심 기능에 공통 기능을 삽입하는 방법

1. 컴파일 시점에 공통 기능을 삽입하는 방법
    - AOP 개발 도구가 소스 코드를 컴파일 하기 전에 공통 구현 코드를 소스에 삽입하는 방식으로 동작
2. 클래스 로딩 시점에 바이트 코드에 공통 기능을 삽입하는 방법
    - 클래스를 로딩할 때 바이트 코드에 공통 기능을 클래스에 삽입하는 방법
    - 1번과 2번은 스프링 AOP에서 지원하지 않으며 AspdectJ와 같이 AOP 전용 도구를 사용해서 적용 가능
3. 런타임에 프록시 객체를 생성해서 공통 기능을 삽입하는 방법
    - 스프링이 제공하는 AOP 방식은 프록시를 이용한 세번째 방식이다.

#### 프록시 기반의 AOP

<p align="center"><img src="https://i.postimg.cc/Vk5s6V57/img1-daumcdn.png"></p>

스프링 AOP는 프록시 객체를 자동으로 만들어준다. 따라서 ExeTimeCalculator 클래스
처럼 상위 타입의 인터페이스를 상속받은 프록시 클래스를 직접 구현할 필요가 없다. 단
지 공통 기능을 구현한 클래스만 알맞게 구현하면 된다.

#### AOP의 주요 용어

| 용어  | 의미  |
| --- | --- |
| Adivice | 언제 공통 관심 기능을 핵심 로직에 적용할 지를 정의하고 있다. 예를 들어 '메서드를 호출하기 전'(언제)에 '트랜잭션 시작'(공통 기능) 기능을 적용한다는 것을 정의한다. |
| JoinPoint | Advice를 적용 가능한 지점을 의미한다. 메서드 호출, 필드 값 변경 등이 JoinPoint에 해당한다. 스프링은 프록시를 이용해서 AOP를 구현하기 때문에 메서드 호출에 대한 Joinpoint만 지원한다. |
| Pointcut | Joinpint의 부분 집합으로서 실제 Advice가 적용되는 Joinpoint를 나타낸다. 스프링에서는 정규 표현식이나 AspectJ의 문법을 이용하여 Pointcut을 정의할 수 있다. |
| Weaving | Advice를 핵심 로직 코드에 적용하는 것을 weaving이라고 한다. |
| Aspect | 여러 객체에 공통으로 적용되는 기능을 Aspect라고 한다. 트랜잭션이나 보안 등이 Aspect의 좋은 예이다. |

### Adivice의 종류

스프링은 프록시를 이용해서 메서드 호출 시점에 Aspect를 적용하기 때문에 구현 가능한 Advice의 종류는 아래와 같다.

| 종류  | 설명  |
| --- | --- |
| Before Advice | 대상 객체의 메서드 호출 전에 공동 기능을 실행한다. |
| After Returning Advice | 대상 객체의 메서드가 익셉션 없이 실행된 이후에 공통 기능을 실행한다. |
| After Throwing Advice | 대상 객체의 메서드를 실행하는 도중 익셉션이 발생한 경우에 공통 기능을 실행한다. |
| After Advice | 익셉션 발생 여부에 상관없이 대상 객체의 메서드 실행 후 공통 기능을 실행한다. (try-catch-finally의 finally 블록과 비슷하다.) |
| Around Advice | 대상 객체의 메서드 실행 전, 후 또는 익셉션 발생 시점에 공통 기능을 실행하는데 사용된다. |

이중에 널리 사용되는 것은 Around Advice이다. 이유는 대상 객체의 메서드를 실행하기 전/후, 익셉션 발생 시점 등 다양한 시점에 원하는 기능을 삽입할 수 있기 때문이다.
