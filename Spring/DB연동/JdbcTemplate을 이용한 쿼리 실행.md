## JdbcTemplate을 이용한 쿼리 실행

스프링을 사용하면 DataSource나 Connection, Statement, ResultSet을 직접 사용하지 않고 JdbcTemplate을 이용해서 편리하게 쿼리를 실행할 수 있다.

### JdbcTemplate 생성

```java
public class MemberDao {
    private JdbcTemplate jdbcTemplate;
    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
```

```java
@Configuration
public class AppCtx {
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true); //유휴 커넥션 검사
        ds.setMinEvictableIdleTimeMillis(100 * 60 * 3); //최소 유휴 시간 3분
        ds.setTimeBetweenEvictionRunsMillis(1000 * 10); //10초 주기
        return ds;
    }
    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
}
```

### JdbcTemplate을 이용한 조회 쿼리 실행

JdbcTemplate 클래스는 SELECT 쿼리 실행을 위한 query() 메서드를 제공한다.

**자주 사용되는 쿼리 메소드**

- `List<T> query(String sql, RowMapper<T> rowMapper)`
  
- `List<T> query(String sql, Object[] args, RowMapper<T> rowMapper)`
  
- `List<T> query(String sql, RowMapper<T> rowMapper, Object... args)`
  

query() 메서드는 sql 파라미터로 전달받은 쿼리를 실행하고 **RowMapper**를 이용해서 ResultSet의 결과를 자바 객체로 변환한다.

**RowMapper 인터페이스**

```java
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
```

RowMapper의 mapRow() 메서드는 SQL 실행결과로 구한 ResultSet에서 한 행의 데이터를 읽어와 자바 객체로 변환하는 매퍼 기능을 구현한다. RowMapper 인터페이스를 구현한 클래스를 작성할 수도 있지만 **임의 클래스**나 람다식으로 RowMapper의 객체를 생성해서 query() 메서드에 전달할 때도 많다.

```java
public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate
            .query("select * from MEMBER where EMAIL = ?", new RowMapper<Member>(){
                @Override
                public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Member member = new Member(
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getTimestamp("REGDATE").toLocalDateTime());
                    return member;
                }
            }, email);
        return results.isEmpty() ? null : results.get(0);
    }

    ... 
}
```

query() 메서드의 세 번째 파라미터는 가변 인자로 인덱스 파라미터가 두 개이 상이면 아래와 같이 인덱스 파라미터 설정에 사용할 각 값을 콤마로 구분한다.

```java
List<Member> results = jdbcTemplate.query(
    "select * from MEMBER where EMAIL = ?", 
    new RowMapper<Member>(){...}, email, name); // 물음표 개수만큼 해당되는 값 전
```

위의 코드는 임의 클래스를 이용해서 RowMapper의 객체를 전달한다. 이 RowMapper는 ResultSet에서 데이터를 읽어와 Member 객체로 변환해주는 기능을 제공하므로 RowMapper의 타입 파라미터로 Member를 사용했다.

동일한 RowRapper 구현을 여러 곳에서 사용한다면 아래 코드처럼 RowMapper 인터페이스를 구현한 클래스를 만들어 코드 중복을 막을 수 있다.

```java
public class MemberRowMapper implements RowMapper<Memeber> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throw SQLException {
        Member member = new Member(
            rs.getString("EMAIL"),
            rs.getString("PASSWORD"),
            rs.getString("NAME"),
            rs.getTimestamp("REGDATE").toLocalDateTime());
        member.setId(rs.getLong("ID"));
        return member;
    }
}
```

```java
List<Member> results = jdbcTemplate.query(
    "select * from MEMBER where EMAIL = ? and NAME = ?",
    new MemberRowMapper(), email, name);
```

MemberDao에서 JdbcTemplate의 query()를 사용하는 또 다른 메서드는 selectAll()로 아래와 같이 구현할 수 있다.

```java
public class MemberDao {
    ...
    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query(
            "select * from MEMBER", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime());
                return member;
            }
        });
        return results;
    }
}
```

위 코드는 selectByEmail() 메서드와 동일한 RowMapper 임의 클래스를 사용했다.

### queryForObject() 메서드

아래의 코드는 MEMBER 테이블의 전체 행 개수를 구하는 코드이다. 이 코드는 query() 메서드를 사용했다.

```java
public int count() {
    List<lnteger> results = jdbcTemplate.query(
    "select count(*) from MEMBER",
    new RowMapper<lnteger>() {
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getlnt(1);
        }
    });
    return results.get(O);
}
```

`count(*)` 쿼리는 결과가 한 행 뿐이니 쿼리 결과를 List로 받기보다는 Integer와 같은 정수 타입으로 받으면 편리할 것이다. 이를 위한 메서드가 바로 queryForObject()이다. queryForObject()를 이용하면 `count(*)`쿼리 실행 코드를 아래처럼 구현할 수 있다.

```java
public int count() {
    Integer count = jdbcTemplate.queryForObject(
        "select count(*) from MEMBER", Integer.class);
    return count;
}
```

queryForObject() 메서드는 쿼리 실행 결과 행이 한 개인 경우에 사용할 수 있는 메서드다. queryForObject() 메서드의 두 번째 파라미터는 칼럼을 읽어올 때 사용할 타입을 지정한다.

실행 결과 칼럼이 두 개 이상이면 RowMapper를 파라미터로 전달해서 결과를 생성할 수 있다. 예를 들어 특정 ID를 갖는 회원 데이터를 queryForObject()로 읽어오고 싶다면 아래 코드를 사용할 수 있다.

```java
Member member = jdbcTemplate.queryForObject(
    "select * from MEMBER where ID = ?",
    new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        Member member = new Member(
            rs.getString("EMAIL"),
            rs.getString("PASSWORD"),
            rs.getString("NAME"),
            rs.getTimestamp("REGDATE").toLocalDateTime());
        member.setld(rs.getLong("ID"))；
        return member；
    }
}, 100);
```

queryForObject() 메서드를 사용한 위 코드와 기존의 query() 메서드를 사용한 코드의 차이점은 리턴 타입이 List가 아니라 RowMapper로 변환해주는 타입이라는 점이다.

**주요 queryForObject() 메서드**

- T queryForObject(String sql, Class requiredType)
  
- T queryForObject(String sql, Class〈T〉requiredType, Object... args)
  
- T queryForObject(String sql, RowMapper rowMapper)
  
- T queryForObject(KString sql, RowMapper rowMapper, Object... args)
  

만약 쿼리 실행 결과 행이 없거나 두 개 이상이면 IncorrectResultSizeDataAccessException이 발생한다. 행의 개수가 0이면 하위 클래스인 EmptyResultDataAccessException이 발생한다. 따라서 결과 행이 정확히 한 개가 아니면 queryForObjectO 메서드 대신 query() 메서드를 사용해야 한다.

### JdbcTemplate을 이용한 변경 쿼리 실행

INSERT, UPDATE, DELETE 쿼리는 update() 메서드를 사용한다.

- int update(String sql)
  
- int update(String sql, Object... args)
  

update() 메서드는 쿼리 실행 결과로 변경된 행의 개수를 리턴한다. update() 메서드의
사용 예는 아래와 같다.

```java
public class MemberDao {
    private JdbcTemplate jdbcTemplate;
    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    ...
    public void update(Member member){
        jdbcTemplate.update(
            "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
            member.getName(), member.getPassword(), member.getEmail()
        );
    }
}
```

### PreparedStatementCreator를 이용한 쿼리 실행

지금까지 작성한 코드는 쿼리에서 사용할 값을 인자로 전달했다. 하지만, PreparedStatemet의 set 메서드를 사용해서 직접 인덱스 파라미터의 값을 설정해야 할 때도 있다. 이 경우 PreparedStatementCreator를 인자로 받는 메서드를 이용해서 직접 PreparedStatement를 생성하고 설정해야 한다.

**PreparedStatementCreator 인터페이스**

```java
public interface PreparedStatementCreator {
    PreparedStatement createPreparedStatement(Connection con) 
        throws SQLException;
}
```

PreparedStatementCreator 인터페이스의 createPreparedStatement() 메서드는
Connection 타입의 파라미터를 갖는다. PreparedStatementCreator를 구현한 클래스
는 createPreparedStatementQ 메서드의 파라미터로 전달받는 Connection을 이용해서
PreparedStatement 객체를 생성하고 인덱스 파라미터를 알맞게 설정한 뒤에 리턴하면
된다. 아래의 코드는 PreparedStatementCreator 인터페이스 예제 코드이다.

```java
jdbcTemplate.update(new PreparedStatementCreator() {
    @Override
    public PreparedStatement createPreparedStatement(Connection con)
        throws SQLException {
        // 파라미터로 전달받은 Connections 이용해서 PreparedStatement 생성
        PreparedStatement pstmt = con.prepareStatement(
        "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?. ?, ?)")；
        // 인덱스 파라미터의 값 설정
        pstmt.setString(1, member.getEmail();
        pstmt.setString(2, member.getPassword();
        pstmt.setString(3, member.getName();
        pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime())；
        // 생성한 PreparedStatement 객체 리턴
        return pstmt;
    }
});
```

JdbcTemplate 클래스가 제공하는 메서드 중에서 PreparedStatementCreator 인터페이스를 파라미터로 갖는 메서드는 아래와 같다.

- List query(PreparedStatementCreator psc, RowMapper<T> rowMapper)
  
- int update(PreparedStatementCreator psc)
  
- int update(PreparedStatementCreator psc, KeyHolder generatedKeyHolder)
  

위 목록에서 세 번째 메서드는 자동 생성되는 키값을 구할 때 사용한다.

### INSERT 쿼리 실행 시 KeyHolder를 이용해서 자동 생성 키값 구하기

MySQL의 AUTO_INCREMENT 칼럼은 행이 추가되면 자동으로 값이 할당되는 칼럼으로서 주요키 칼럼에 사용된다. 앞서 MEMBER 테이블을 생성할 때 사용한 쿼리도 다음 코드처럼 주요키 칼럼을 AUTO_INCREMENT 칼럼으로 지정했다.

```java
create table spring5fs.MEMBER (
    ID int auto_increment primary key,
    EMAIL varchar(255),
    PASSWORD varchar(IOO),
    NAME varchar(IOO),
    REGDATE datetime,
    unique key(EMAIL)
) engine=InnoDB character set = utf8;
```

AUTO_INCREMENT와 같은 자동 증가 칼럼을 가진 테이블에 값을 삽입하면 해당 칼럼의 값이 자동으로 생성된다. 따라서 아래 코드처럼 INSERT 쿼리에 자동 증가 칼럼에 해당하는 값은 지정하지 않는다.

```java
jdbcTemplate.update(
    "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)",
    member.getEmail(), member.getPassword(), member.getName(),
    new Timestamp(member.getRegisterDate().getTime());
```

그런데 쿼리 실행 후에 생성된 키값을 알고 싶다면 어떻게 해야 할까? update() 메서드는 변경된 행의 개수를 리턴할 뿐 생성된 키값을 리턴하지는 않는다.

JdbcTemplate은 자동으로 생성된 키값을 구할 수 있는 방법을 제공하고 있다. 그것은 바로 KeyHolder를 사용하는 것이다. KeyHolder를 사용하면 아래와 같이 MemberDao의 insert() 메서드에서 삽입하는 Member 객체의 ID 값을 구할 수 있다.

```java
public class MemberDao {
    private JdbcTemplate jdbcTemplate;
    ...

    public void insert(final Member member) {
        // GeneratedKeyHolder 객체를 생성한다.
        // 이 클래스는 자동 생성된 키 값을 구해주는 KeyHolder 구현 클래스이다.
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // update() 메서드는 PerparedStatementCreator 객체와 KeyHolder 객체를 파라미터로 갖는다.
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                    "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)"+
                    "values (?, ?, ?, ?)",
                    new String[] {"ID"});
                pstmt.setString(1, member.getEmail();
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt. setTimestamp(4, 
                    Timestamp.valueOf(member.getRegisterDateTime()));
                return pstmt;
            }
        // JdbcTemplate.update() 메서드의 두 번째 파라미터로 KeyHolder 객체를 전달한다.
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setld(keyValue.longValue());
    }
}
```

PreparedStatementCreator 임의 클래스를 이용해서 PreparedStatement 객체를 직접 생성한다. Connection의 preparedStatement() 메서드를 이용해서 PreparedStatement 객체를 생성하는데 두 번째 파라미터로 String 배열인 {“ID”}를 주었다. 이 두 번째 파라미터는 자동 생성되는 키 칼럼 목록을 지정할 때 사용한다. MEMBER 테이블은 ID 칼럼이 자동 증가 키 칼럼이므로 두 번째 파라미터 값으로 {"ID”}를 주었다.

JdbcTemplate의 update() 메서드는 PreparedStatement를 실행한 후 자동 생성된 키 값을 KeyHolder에 보관한다. KeyHolder에 보관된 키값은 getKey() 메서드를 이용해서 구한다. 이 메서드는 java. lang.Number를 리턴하므로 Number의 intValue(), longValue() 등의 메서드를 사용해서 원하는 타입의 값으로 변환할 수 있다.
