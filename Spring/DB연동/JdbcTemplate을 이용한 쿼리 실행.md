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
