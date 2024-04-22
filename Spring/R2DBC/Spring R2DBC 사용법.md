### Spring R2DBC 사용법

- R2DBC(Reactive Relational Database Connectivity)는 기존 블로킹 기반의 RDB를 리액티브하게 사용할 수 있도록 API를 제공
- Spring WebFlux와 R2DBC를 함께 사용하면 애플리케이션이 온전히 리액티브하게 동작할 수 있다.
- R2DBC는 non-blocking I/O 레이어 위에 데이터베이스 유선 프로토콜 계층까지 완전히 새롭게 구현한 드라이버를 제공

---

## R2DBC 설정

1. **의존성 추가**
   - Spring Boot Starter Data R2DBC 의존성 추가
   - MySQL 드라이버 의존성 추가 (예: `dev.miku:r2dbc-mysql:0.8.2.RELEASE`)

2. **application.yml 설정**
   ```yaml
   spring:
     r2dbc:
       url: r2dbc:mysql://localhost:3307/test_contents?useUnicode=true&characterEncoding=utf8
       username: root
       password: 
       logging:
         level:
           org.springframework.r2dbc: DEBUG
   ```

## R2DBC 엔티티 선언

1. **엔티티 클래스 선언**
   ```java
   @Data
   @Table("users")
   public class User {
       @Id
       private Long id;
       private String name;
       private String email;
   }
   ```

2. **Repository 인터페이스 선언**
   ```java
   public interface UserRepository extends ReactiveCrudRepository<User, Long> {
   }
   ```

## R2DBC 사용 예시

1. **CRUD 작업**
   ```java
   @Service
   public class UserService {
       private final UserRepository userRepository;

       public Mono<User> createUser(User user) {
           return userRepository.save(user);
       }

       public Flux<User> getAllUsers() {
           return userRepository.findAll();
       }

       public Mono<User> getUserById(Long id) {
           return userRepository.findById(id);
       }

       public Mono<User> updateUser(User user) {
           return userRepository.save(user);
       }

       public Mono<Void> deleteUser(Long id) {
           return userRepository.deleteById(id);
       }
   }
   ```

2. **트랜잭션 처리**
   ```java
   @Service
   public class TransactionService {
       private final UserRepository userRepository;

       @Transactional
       public Mono<Void> processUserTransactions(User user1, User user2) {
           return userRepository.save(user1)
                   .then(userRepository.save(user2))
                   .then();
       }
   }
   ```

## 추가 정보

R2DBC는 MySQL 외에도 PostgreSQL, SQL Server, Oracle 등 다양한 데이터베이스를 지원한다.
- R2DBC 공식 문서: https://r2dbc.io/
- Spring Data R2DBC 문서: https://spring.io/projects/spring-data-r2dbc
