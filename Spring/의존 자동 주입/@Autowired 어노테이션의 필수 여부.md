## @Autowired 어노테이션의 필수 여부

```java
public class MemberPrinter {
    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf(
                "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                member.getId(), member.getEmail(),
                member.getName(), member.getRegisterDateTime());
        } else {
            System.out.printf(
                "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                member.getId(), member.getEmail(),
                member.getName(),
                dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }
// 빈객체가 없는 경우 에러를 발생시킬 수 있다.
    @Autowired
    public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
```

print() 메서드는 dateTimeFormatter가 null인 경우에도 알맞게 동작한다. 반드시 setDateFormatter()를 통해서 의존 객체를 주입할 필요는 없다. setDateFormatter()에 주입할 빈이 존재하지 않아도 MemberPrinter가 동작하는데는 문제가 없다.

**하지만** @Autowired 애노테이션은 기본적으로 @Autowired 애노테이션을 붙인 타입에 해당하는 빈이 존재하지 않으면 익셉션을 발생한다. 따라서 setDateFormatter() 메서드에서 필요로 하는 DateTimeFormatter 타입의 빈이 존재하지 않으면 익셉션이 발생한다.

**MemberPrinter는 setDateFormatter()메서드에 자동 주입할 빈이 존재하지 않으면** 익셉션이 발생하기 보다는 그냥 dateTimeFormatter 필드가 null이면 된다.

#### 자동 주입할 대상이 필수가 아닌경우의 속성값

```java
public class MemberPrinter {

    @Autowired(required = false)
    public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

}

public class MemberPrinter {

    @Autowired(required=false)
    private DateTimeformatter dateTimeFormatter;

}
```

- 주입할 대상이 필수가 아닌 경우에는 required속성을 false로 한다.
- 이렇게 하면 매칭되는 빈이 없어도 익셉션이 발생하지 않으며 자동 주입을 수행하지 않는다.
- 위의 예시는 DateTimeFormatter 빈이 존재하지 않으면 주입을 하지않고, setDateFormmatter 메서드를 실행하지 않는다.

#### 스프링 5버전 부터의 자동 주입할 대상이 필수가 아닌경우의 속성 처리

```java
public class MemberPrinter {

    @Autowired
    public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
        if (formatterOpt.isPresent()) {
            this.dateTimeFormatter = formatterOpt.get();
        } else {
            this.dateTimeFormatter = null;
        }
    }   
}

public class MemberPrinter {

    @Autowired
    private Optional<DateTimeformatter> formatterOpt;

    public void print(Member member) {
        DateTimeFormatter dateTimeFormatter = formatterOpt.orElse(null);
        if(dateTimeFormatter == null) {
            ...
        } else {
            ...//여기에서 사용하는것이 정상적일 것
        }
    }
}
```

- 자바 8의 Optional을 사용한다.
- 자동 주입 대상 타입이 Optional인 경우, 일치하는 빈이 존재하지 않으면 값이 없는 Optional을 인자로 전달하고 **(익셉션이 발생하지 않는다.)** formatterOpt.isPresent()"를 이용해서 true이면 값이 존재하므로 dateTimeFormatter를 필드에 할당한다.
- 즉, DateTimeFormatter 타입 빈을 주입 받아 dateTimeFormatter필드에 할당한다.
- 값이 존재하지 않으면 주입 받은 빈 객체가 없으므로 dateTimeFormatter 필드에 null을 할당한다.

#### @Nullable 어노테이션을 이용한 방법

```java
public class MemberPrinter {

    @Autowired
    public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

}

public class MemberPrinter {

    @Autowired
    @Nullable
    private DateTimeformatter dateTimeFormatter;

    public void print(Member member) {
        ...
    }

}
```

@Nullable 어노테이션을 의존 주입 대상 파라미터에 붙이면, 스프링 컨테이너는 세터 메서드를 호출할 때 자동 주입할 빈이 존재하면 해당 빈을 인자로 전달하고, 존재하지 않으면 인자로 null을 전달한다.

### 생성자 초기화와 필수 여부 지정 방식 동작 이해

> @Nullable 과 @Autowired(required = false) 의 차이점
> 
> - 일치하는 빈이 없으면 값 할당 자체를 하지않는 @Autowired(required=false)
> - @Nullable 어노테이션을 사용하면 일치하는 빈이 없을 때 null값을 할당한다.
> - Optional 타입은 매칭 되는 빈이 없으면 값이 없는 Optional을 할당한다.
> - **기본 생성자에서 자동 주입 대상이 되는 필드를 초기화할 때는 이 점에 유의해야한다.**

```java
public class MemberPrinter {
    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {//생성자에서 필드값을 초기화 해준다.
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n", 
                    member.getId(), member.getEmail(),
                    member.getName(), member.getRegisterDateTime());
        } else {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n", 
                    member.getId(), member.getEmail(),
                    member.getName(), 
                    dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

    @Autowired(required = false)
    public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    // 출력 : 2018년 01월 02일 null값 전달 X

    @Autowired
    public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
        if (formatterOpt.isPresent()) {
            this.dateTimeFormatter = formatterOpt.get();
        } else {
            this.dateTimeFormatter = null;
        }
    }

    @Autowired
    public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    // 출력 : 2018-01-02 null값 전달 O
}
```

@Autowired(required = false)인 경우는 빈이 존재하지않을 때 **필드나 메서드에 null값을 전달하지 않는다.** 그래서 기본 생성자에서 초기화한 DateTimeFormatter의 형식으로 날짜를 출력한다.

**하지만** @Nullable인 경우는 **필드나 메서드에 null값을 전달함으로** 생성자에서 dateTimeFormatter를 초기화 해도 setDateFormatter() 메서드가 null을 전달받아 dateTimeFormatter 필드가 다시 null로 바뀐다.
