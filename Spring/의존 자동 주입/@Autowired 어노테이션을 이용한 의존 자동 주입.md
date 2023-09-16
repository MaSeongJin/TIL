## @Autowired 어노테이션을 이용한 의존 자동 주입

- 자동 주입 기능을 사용하면 스프링이 알아서 의존 객체를 찾아서 주입한다.
  
- 스프링 컨테이너 설정에 의존 객체를 명시하지 않아도 스프링이 필요한 의존 빈 객체를 찾아서 주입해준다.
  

#### @Autowired 사용 전(@Bean생성해주는 클래스 파일)

```java
@Bean
public MemberDao memberDao() {//생성자 주입 방법
    return new MemberDao();
}
@Bean
public ChangePasswordService changePwdSvc() {//setter 메소드 주입 방법
    ChangePasswordSerivce pwdSvc = new ChangePasswordSerivce();
    pwdSvc.setMemberDao(memberDao());
    return pwdSvc;
}
```

- ChangePasswordService 객체의 setMemberDao로 MemberDao 빈 객체를 주입하고있다.

#### @Autowired 사용 후(@Bean생성해주는 클래스 파일)

```java
@Bean
public MemberDao memberDao() {//생성자 주입 방법
    return new MemberDao();
}
@Bean
public ChangePasswordService changePwdSvc() {//setter 메소드 주입 방법
    ChangePasswordSerivce pwdSvc = new ChangePasswordSerivce();
    return pwdSvc;
}
```

- @Autowired를 이용해서 자동으로 필요한 객체를 주입해주기 때문에 빈 객체를 주입하지 않는다.

#### 의존을 주입할 대상에 @Autowired 애노테이션을 붙여준다.

```java
import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();

        member.changePassword(oldPwd, newPwd);

        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
```

- ChangePasswordService 클래스에서 MemberDao 객체에 @Autowired 애노테이션을 사용했다.
- setMemberDao() 메서드를 **사용하지 않고도** 스프링 컨테이너가 @Autowired가 붙은 필드에 해당 타입의 빈객체를 찾아서 주입한다.
- @Autowired 애노테이션을 필드나 세터 메서드에 붙이면 스프링은 타입이 일치하는 빈 객체를 찾아 주입한다.

### 일치하는 빈이 없는 경우

- 스프링 컨테이너가 자동 주입을 하려면 해당 타입을 가진 빈이 어떤 빈인지 정확하게 한정할 수 있어야한다.
  
- 주입할 타입의 빈이 2개이거나 없는 경우 스프링은 자동 주입에 실패하고 익셉션을 발생 시킨다.
