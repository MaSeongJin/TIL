## 스프링 DI 설정

### 스프링을 이용한 객체 조립과 사용

스프링을 사용하려면 먼저 스프링이 어떤 객체를 생성하고, 의존을 어떻게 주입할지를 정의한 설정 정보를 작성해야 한다. 이 설정 정보는 자바 코드를 이용해서 작성할 수 있다.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }
}
```

@Configuration 어노테이션은 스프링 설정 클래스를 의미한다. 이 어노테이션을 붙여야 스프링 설정 클래스로 사용할 수 있다.

@Bean 어노테이션은 해당 메서드가 생성한 객체를 스프링 빈이라고 설정한다. 위 코드의 경우 세 개의 메서드에 @Bean 어노테이션을 붙였는데 각각의 메서드마다 한 개의 빈 객체를 생성한다. 이때 메서드 이름을 빈 객체의 이름으로 사용한다. 예를 들어 memberDao() 메서드를 이용해서 생성한 빈 객체는 “memberDao”라는 이름으로 스프링에 등록된다.

MemberRegisterService 생성자를 호줄할 때 memberDao() 메소드를 호출한다. 즉 memberDao()가 생성한 객체를 MemberRegisterService 생성자를 통해 주입한다.

changePwdSvc() 메소드는 ChangePasswordService 타입의 빈을 설정한다. 이 메서드는 세터(setMemberDao() 메서드)를 이용해서 의존 객체를 주입한다

객체를 생성하고 의존 객체를 주입하는 것은 스프링 컨테이너이므로 설정 클래스를 이용해서 컨테이너를 생성해야 한다. AnnotationConfigApplicationContext 클래스를 이용해서 스프링 컨테이너를 생성할 수 있다.

```java
ApplicationContext ctx = 
    new AnnotationConfigApplicationContext(AppCtx.class);
```

컨테이너를 생성하면 getBean() 메서드를 이용해서 사용할 객체를 구할 수 있다.

```java
// 컨테이너에서 이름이 memberRegSvc인 빈 객체를 구한다.
MemberRegisterService regSvc =    
    ctx.getBean("memberRegSvc", MemberRegisterService.class);
```

위 코드는 스프링 컨테이너 (ctx)로부터 이름이 "memberRegSvc()" 빈 객체를 구한다. 앞서 자바 설정을 보면 다음 코드처럼 이름이 "memberRegSvc"인 @Bean 메소드를 설정했다. 이 메서드는 MemberRegisterService 객체에 생성자를 통해 memberDao를 주입한다. 따라서 위 코드에서 구한 MemberRegisterService 객체는 내부에서 memberDao 빈 객체를 사용한다.

### DI 방식 1 : 생성자 방식

MemberRegisterService 클래스를 보면 아래 코드처럼 생성자를 통해 의존 객체를 주입받아 필드(this. memberDao)에 할당했다.

```java
public class MemberRegisterService {
    private MemberDao memberDao;

    // 생성자를 통해 의존 객체를 주입 받음
    public MemberRegisterService(MemberDao memberDao) {
        // 주입 받은 객체를 필드에 할당
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        // 주입 받은 의존 객체의 메서드를 사용
        Member member = memberDao.selectByEmail(req.getEmail())；
        memberDao.insert(newMember);
    return newMember.getld();
    }
}
```

### DI 방식 2 : 세터 메소드 방식

생성자 외에 세터 메서드를 이용해서 객체를 주입받기도 한다. 일반적인 세터(setter) 메
서드는 자바빈 규칙에 따라 다음과 같이 작성한다.

- 메서드 이름이 set으로 시작한다.
  
- set 뒤에 첫 글자는 대문자로 시작한다.
  
- 파라미터가 1개이다.
  
- 리턴 타입이 void이다.
  

> 자바빈에서는 getter와 setter를 이용해서 프로퍼티를 정의한다. 예를 들어 String getName() 메소드와 void setName(String name) 메서드는 값을 읽고 쓸 수 있는 name 프로퍼티가 된다. 메서드 이름은 get이나 set으로 시작하고, get과 set 뒤에는 사용할 프로퍼티 이름의 첫 글자를 대문자로 바꾼 글자를 사용한다. age 프로퍼티가 있다면 이 프로퍼티를 위한 읽기 메서드는 getAge가 되고 쓰기 메서드는 setAge가 된다.
> set과 get 메서드를 각각 세터(setter)와 게터(getter)라고 부르며 , setAge와 같은 쓰기 메서드는 프로퍼티 값을 변경하므로 프로퍼티 설정 메서드라고도 부른다.

### 생성자 방식 VS 세터 메소드 방식

- 생성자 방식 : 빈 객체를 생성하는 시점에 모든 의존 객체가 주입된다.
  
- 설정 메서드 방식 : 세터 메서드 이름을 통해 어떤 의존 객체가 주입되는지 알 수 있다.
  

각 방식의 장점은 곧 다른 방식의 단점이다. 예를 들어 생성자의 파라미터 개수가 많을 경우 각 인자가 어떤 의존 객체를 설정하는지 알아내려면 생성자의 코드를 확인해야 한다. 하지만 설정 메서드 방식은 메서드 이름만으로도 어떤 의존 객체를 설정하는지 쉽게 유추할 수 있다.

반면에 생성자 방식은 빈 객체를 생성하는 시점에 필요한 모든 의존 객체를 주입받기 때문에
객체를 사용할 때 완전한 상태로 사용할 수 있다. 하지만 세터 메서드 방식은 세터 메서드를
사용해서 필요한 의존 객체를 전달하지 않아도 빈 객체가 생성되기 때문에 객체를 사용하는
시점에 NullPointerException이 발생할 수 있다.
