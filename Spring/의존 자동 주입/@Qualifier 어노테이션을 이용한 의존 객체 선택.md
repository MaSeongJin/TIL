## @Qualifier 어노테이션을 이용한 의존 객체 선택

자동 주입 가능한 빈이 2개 이상이면 @Qualifier 애노테이션을 사용해서 자동 주입 대상 빈을 한정한다.

#### @Qualifier 어노테이션 사용 방법

```java
@Bean
@Qualifier("printer")
public MemberPrinter memberPrinter1() {
    return new MemberPrinter();
}

@Bean
@Qualifier("summaryPrinter")
public MemberSummaryPrinter memberPrinter2() {
    return new MemberSummaryPrinter();
}
```

- @Bean애노테이션을 붙인 빈 설정 메서드에서 사용한 예시이다.
- 가장 위의 printer값을 갖는 @Qualifier 애노테이션을 붙여 해당 빈의 한정 값을 "printer"로 지정한다.
- 이렇게 지정한 한정 값은 @Autowired 애노테이션에서 자동 주입할 빈을 한정할 때 사용한다.

```java
@Autowired
@Qualifier("printer")
public void setMemberPrinter(MemberPrinter printer) {
    this.printer = printer;
}
```

- setMemberPrinter() 메서드에 @Autowired 애노테이션을 붙였으므로 MemberPrinter 타입의 빈을 자동 주입한다.
- 이제 해당 빈이 자동 주입할때 @Bean 객체중 "printer"라고 한정된 MemberPrinter 타입의 (memberPrinter1)을 자동 주입 대상으로 사용한다.

### 빈 이름과 기본 한정자

빈 설정에 @Qualifier 어노테이션이 없으면 빈의 이름을 한정자로 지정한다

```java
@Bean
public MemberPrinter printer() {
    return new MemberPrinter();
}

@Bean
@Qualifier("mprinter")
public MemberPrinter printer2() {
    return new MemberPrinter();
}

@Bean
public MemberInfoPrinter2 infoPrinter() {
    MemberInfoPrinter2 infoPrinter = new MemberInfoPrinter2();
    return infoPrinter;
}
```

- printer() 메서드로 정의한 빈의 한정자는 빈 이름인 "printer"가 된다.
- printer2() 메서드는 "mprinter"가 한정자가 된다.
- @Autowired 애노테이션도 @Qualifier 애노테이션이 없으면 필드나 파라미터 이름을 한정자로를 사용한다. 예를 들면 printer 필드에 일치하는 빈이 2개 이상 존재하면 한정자로 필드 이름인 "printer"를 사용한다.
