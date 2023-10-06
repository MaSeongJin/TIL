## DataSource 설정

JDBC API는 DriverManager 외에 DataSource를 이용해서 DB 연결을 구하는 방법을 정의하고 있다. DataSource를 사용하면 아래와 같이 Connection을 구할 수 있다.

```java
Connection conn = null;
try{
    // dataSource는 생성자나 설정 메서드를 이용해서 주입받음
    conn = dataSource.getConnection();
    ...
}
```

스프링이 제공하는 DB 연동 기능은 DataSource를 사용해서 DB Connection을 구한다. DB 연동에 사용할 **DataSource를 스프링 빈으로 등록**하고 DB 연동 기능을 구현한 빈 객체는 DataSource를 주입받아 사용한다.

Tomcat JDBC 모듈은 javax.sql.DataSource를 구현한 DataSource 클래스를 제공한다. 이 클래스를 스프링 빈으로 등록해서 DataSource로 사용할 수 있다.

```java
@Configuration
public class DBConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        // DataSource 객체를 생성한다.
        DataSource ds = new DataSource();
        // JDBC 드라이버 클래스를 지정한다. MySql 드라이버 클래스를 사용한다.
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        // JDBC URL을 지정한다. 데이터베이스와 테이블의 캐릭터셋을 UTF-8로 지정
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        // DB에 연결할 때 사용할 사용자 계정과 암호를 지정
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        return ds;
    }
}
```

### Tomcat JDBC의 주요 프로퍼티

| 설정 메서드 | 설명  | 기본값 |
| --- | --- | --- |
| setInitialSize(int) | 커넥션 풀을 초기화할 때 생성할 초기 커넥션 개수를 지정한다. | 10  |
| setMaxActive(int) | 커넥션 풀에서 가져올 수 있는 최대 커넥션 개수를 지정한다. | 100 |
| setMaxIdle(int) | 커넥션 풀에 유지할 수 있는 최대 커넥션 개수를 지정한다. | maxActive |
| setMinIdle(int) | 커넥션 풀에 유지할 최소 커넥션 개수를 지정한다. | initailSize |
| setMaxWait(int) | 커넥션 풀에서 커넥션의 최대 유효 시간을 밀리초 단위로 지정한다. | 0(유효시간 없음을 의미) |
| setValidationQuery(String) | 커넥션이 유효한지 검사할 때 사용할 쿼리를 지정한다. | null |
| setValidationQueryTimeout(int) | 검사 쿼리의 최대 실행 시간을 초 단위로 지정한다. 이 시간을 초과하면 검사에 실패한 것으로 간주한다. 0 이하로 지정하면 비활성화한다. | -1  |
| setTestOnBorrow(boolean) | 풀에서 커넥션을 가져올 때 검사 여부를 지정한다. | false |
| setTestOnReturn(boolean) | 풀에 커넥션을 반환할 때 검사 여부를 지정한다. | false |
| setTestWhileIdle(boolean) | 커넥션이 풀에 유휴 상태로 있는 동안에 검사할지 여부를 지정한다. | false |
| setMinEvictableIdleTimeMillis(int) | 커넥션 풀에 유휴 상태로 유지할 최소 시간을 밀리초 단위로 지정한다. testWhileIdle이 true이면 유휴 시간이 이 값을 초과한 커넥션을 풀에서 제거한다. | 60000밀리초(60초) |
| setTimeBetweenEvictionRunsMillis(int) | 커넥션 풀의 유휴 커넥션을 검사할 주기를 밀리초 단위로 지정한다. 이 값을 1초 이하로 설정하면 안된다. | 5000밀리치(5초) |

커넥션 풀은 커넥션을 생성하고 유지한다. 커넥션 풀에 커넥션을 요청하면 해당 커넥션은 활성(active) 상태가 되고, 커넥션을 다시 커넥션 풀에 반환하면 유휴(idle) 상태가 된다.

```java
public class DbQuery {

    private DataSource dataSource;

    public DbQuery(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int count() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection(); // 풀에서 구함
            try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // 풀에 반환
                } catch (SQLException e) {

                }
            }
        }
    }
}
```

- 커넥션 사용이 끝나고 커넥션을 종료하면 실제 커넥션을 끊지 않고 풀에 반환한다. 풀에 반환된 커넥션은 다시 유휴 상태가 된다.
  
- maxActive는 활성 상태가 가능한 최대 커넥션 개수를 지정한다. 활성 상태 커넥션이 40개인데 풀에 다시 커넥션을 요청하면 다른 커넥션이 반환될 때까지 대기한다. 이 대기 시간이 maxWait이다. 대기 시간 내에 풀에 반환된 커넥션이 있으면 해당 커넥션을 구하고, 대기 시간내에 반환된 커넥션이 없으면 익셉션이 발생한다.
  
- 커넥션 풀을 초기화할 때 최소 수준의 커넥션을 미리 생성하는 것이 좋다. 이때 생성할 커넥션 개수를 initialSize로 정한다.
  
- 커넥션 풀에 생성된 커넥션은 지속적으로 재사용된다. DBMS 설정에 따라 일정 시간 내에 쿼리를 실행하지 않으면 연결을 끊기도 한다. 커넥션 풀에 특정 커넥션이 5분 넘게 유휴 상태로 존재한다고 하자. 이 경우 DBMS는 해당 커넥션의 연결을 끊지만 커넥션은 여전히 풀 속에 남아있다. 이 상태에서 해당 커넥션을 풀에서 가져와 사용하면 연결이 끊어진 커넥션이므로 익셉션이 발생한다.
  
- 커넥션 풀의 커넥션이 유효한지 주기적으로 검사해야하는데, 이와 관련된 속성이 minEvictableidleTimeMills, timeBetweenEvictionRunsMillstestWhileIdle이다.
