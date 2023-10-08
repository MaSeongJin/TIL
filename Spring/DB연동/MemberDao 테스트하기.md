## MemberDao 테스트하기

```java
public class MainForMemberDao {

    private static MemberDao memberDao;

    public static void main(String[] args) {
        // AppCtx 설정을 사용해서 스프링 컨테이너를 생성
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppCtx.class);
        // 컨테이너로부터 “memberDao” 빈을 구해서 정적 필드인 memberDao 필드에 할당
        memberDao = ctx.getBean(MemberDao.class);

        selectAll();
        updateMember();
        insertMember();

        ctx.close();
    }

    private static void selectAll() {
        System.out.println("-----selectAll");
        int total = memberDao.count();
        System.out.println("전체 데이터: " + total);
        List<Member> members = memberDao.selectAll();
        for (Member m : members) {
            System.out.println(m.getId() + ":" + m.getEmail() + ":" + m.getName());
        }
    }

    private static void updateMember() {
        System.out.println("-------updateMember");
        Member member = memberDao.selectByEmail("madvirus@madvirus.net");
        String oldPw = member.getPassword();
        String newPw = Double.toHexString(Math.random());
        member.changePassword(oldPw, newPw);

        memberDao.update(member);
        System.out.println("암호 변경: " + oldPw + ">" + newPw);
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

    private static void insertMember() {
        System.out.println("----insertMember");

        String prefix = formatter.format(LocalDateTime.now());
        Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
        memberDao.insert(member);
        System.out.println(member.getId() + " 데이터 추가");
    }
}
```

### DB 연동 과정에서 발생 가능한 익셉션

MainForMemberDao 클래스를 실행할 때 DB 연결 정보가 올바르지 않으면 익셉션이 발생할 수 있다.

- Access denied for user ‘spring5'@'localhost’ (using password： YES) 메세지는 MySQL 서버에 연결할 권한이 없는 경우에 발생한다.
  
- DB를 실행하지 않았거나 방화벽에 막혀 있어서 DB에 연결할 수 없다면 연결 자체를 할
  수 없다는 에러 메시지가 출력된다（MySQL JDBC 드라이버 버전에 따라 일부 메시지가
  다를 수 있다）.
  
- 문자열을 연결할 때 줄이 바뀌는 부분에서 실수로 공백문자를 누락하면 익셉션이 발생한다.
