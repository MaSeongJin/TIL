## 의존이란?

DI는 **Dependency Injection**의 약자로 우리말로는 ‘의존 주입’이라고 한다.

```java
import java.time.LocalDateTime；
public class MemberRegisterService {   
 
    private MemberDao memberDao = new MemberDao();

    public void regist(RegisterRequest req) {
        // 이메일로 회원 데이터(Member) 조회
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
        // 같은 이메일을 가진 회원이 이미 존재하면 익셉션 발생
        throw new DuplicateMemberException("dup email " + req.getEmail();
        }
    
    // 같은 이메일을 가진 회원이 존재하지 않으면 DB에 삽입
        Member newMember = new Member(
            req.getEmail(), req.getPassword(), req.getName(),
            LocalDateTime.now());        
        memberDao.insert(newMember);
    )
)
```

서로 다른 회원은 동일한 이메일 주소를 사용할 수 없다는 요구사항이 있다면, 이 제약사항을 처리하기 위해 MemberRegisterService 클래스는 MemberDao 객체의 selectByEmail() 메서드를 이용해서 동일한 이메일을 가진 회원 데이터가 존재하는지 확인한다. 만약 같은 이메일을 가진 회원 데이터가 존재한다면 위 코드처럼 익셉션을 발생시킨다. 같은 이메일을 가진 회원 데이터가 존재하지 않으면 회원 정보를 담은 Member 객체를 생성하고 MemberDao 객체의 insert() 메서드를 이용해서 DB에 회원 데이터를 삽입한다.

중요한 점은 **MemberRegisterService 클래스가 DB 처리를 위해 MemberDao 클래스의 메서드를 사용한다는 점**이다.

이처럼 **한 클래스가 다른 클래스의 메서드를 실행할 때 이를 '의존’한다고 표현**한다.

위 코드에서는 **“MemberRegisterService 클래스가 MemberDao 클래스에 의존한다”** 고 할 수 있다.

*의존은 변경에 의해 영향을 받는 관계를 의미한다. 예를 들어 MemberDao의 insert() 메서드
의 이름을 insertMember()로 변경하면 이 메서드를 사용하는 MemberRegisterService 클래스의 소스 코드도 함께 변경된다. 이렇게 변경에 따른 영향이 전파되는 관계를 ‘의존’한다고 표현한다.*

### 문제점

위와 같이 의존 대상인 객체를 직접 생성하여 필드에 할당할 경우 유지보수의 관점에서 문제점을 유발할 수 있다. 그래서 스프링에서는 DI를 이용한다.

## DI를 통한 의존 처리

DI(Dependency Injection, 의존 주입)는 의존하는 객체를 직접 생성하는 대신 의존 객체를 전달받는 방식을 사용한다. 예를 들어 앞서 의존 객체를 직접 생성한 MemberRegisterService 클래스에 DI 방식을 적용하면 아래처럼 구현할 수 있다.

```java
import java.time.LocalDateTime;

public class MemberRegisterService {
    private MemberDao memberDao;

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail()；
        if (member != null) {
            throw new DuplicateMemberExceptionC'dup email" + req.getEmailO);
        }
        Member newMember = new Member(
        req.getEmail(), req.getPassword(), req.getName(),
        LocalDateTime. now());
        memberDao. insert(newMember);
        return newMember.getld();
    }
}
```

직접 의존 객체를 생성했던 코드와 달리 바뀐 코드는 의존 객체를 직접 생성하지 않는다. 대신 생성자를 통해서 의존 객체를 전달받는다. **즉 생성자를 통해 MemberRegisterService가 의존(Dependency)하고 있는 MemberDao 객체를 주입(Injection) 받은 것**이다. 의존 객체를 직접 구하지 않고 생성자를 통해서 전달받기 때문에 이 코드는 DI(의존 주입) 패턴을 따르고 있다.

이를 적용한 결과 MemberRegisterService 클래스를 사용하는 코드는 아래와 같이MemberRegisterService 객체를 생성할 때 생성자에 MemberDao 객체를 전달해야 한다.

```java
MemberDao dao = new MemberDao();
// 의존 객체를 생성자를 통해 주입한다
MemberRegisterService svc = new MemberRegisterService(dao);
```

의존 객체를 직접 생성하는 방식과 달리 의존 객체를 주입하는 방식은 객체를 생성하는 부분의 코드가 조금 더 길다. 하지만 이와 같이 의존성 주입을 하는 이유는 바로 **변경의 유연함**이다.

## DI와 의존 객체 변경의 유연함

의존 객체를 직접 생성하는 방식은 필드나 생성자에서 new 연산자를 이용해서 객체를 생성한다. 만약 회원의 암호 변경 기능을 제공하는 ChangePasswordService 클래스도 의존 객체를 직접 생성한다면, 수정이 필요할 시 모두 소스 코드를 변경해야 하지만 DI를 사용하면 수정할 코드가 줄어든다.

DI를 사용하면 MemberDao 객체를 사용하는 클래스가 세 개여도 변경할 곳은 의존 주입 대상이 되는 객체를 생성하는 코드 한 곳뿐이다. 앞서 의존 객체를 직접 생성했던 방식에 비해 변경할 코드가 한 곳으로 집중되는 것을 알 수 있다.
