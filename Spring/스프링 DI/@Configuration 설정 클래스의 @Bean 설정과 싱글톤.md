## @Configuration 설정 클래스의 @Bean 설정과 싱글톤

```java
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

memberRegSvc() 메서드와 changePwdSvc() 메서드에서 MemberDao 객체 생성자를 호출하는 memberDao() 메서드를 실행하고 있다. 이 때문에 MemberDao 인스턴스가 2개 생성될거라고 생각할 수 있다.

그러나 스프링 컨테이너는 @Bean 이 붙은 메서드에 대해 1개의 인스턴스만 생성하는 것으로 싱글톤을 유지한다. 즉, AppCtx 클래스에서 MemberDao 생성자가 2회 호출되었지만, 실제 생성되어 있는 인스턴스는 1개 뿐이다.

이런 동작이 가능한 이유는 스프링에서 개발자가 **@Configuration 클래스에 설정한 정보를 그대로 사용하지 않고, 스프링에 적합한 형태로 재구성하여 사용**하기 때문이다.
