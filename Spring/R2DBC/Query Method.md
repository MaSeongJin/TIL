# Spring Data R2DBC 쿼리 메소드 및 커스텀 쿼리 예시

| 메소드                                  | 설명                                                         | 예시                                                                                      |
|---------------------------------------|------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| `findById(ID id)`                     | 주어진 ID에 해당하는 엔티티 조회                             | `findById(1)`                                                                            |
| `findAll()`                           | 모든 엔티티 조회                                              | `findAll()`                                                                              |
| `save(S entity)`                      | 엔티티 저장                                                   | `save(user)`                                                                             |
| `deleteById(ID id)`                   | 주어진 ID에 해당하는 엔티티 삭제                             | `deleteById(1)`                                                                          |
| `delete(S entity)`                    | 주어진 엔티티 삭제                                            | `delete(user)`                                                                           |
| `deleteAll()`                         | 모든 엔티티 삭제                                              | `deleteAll()`                                                                            |
| `existsById(ID id)`                   | 주어진 ID에 해당하는 엔티티가 존재하는지 확인                 | `existsById(1)`                                                                          |
| `count()`                             | 엔티티의 개수 조회                                            | `count()`                                                                                |
| `findBy{프로퍼티명}(T value)`           | 주어진 프로퍼티 값에 해당하는 엔티티 조회                     | `findByUsername("john")`                                                                 |
| `findFirstBy{프로퍼티명}(T value)`      | 주어진 프로퍼티 값에 해당하는 첫 번째 엔티티 조회             | `findFirstByUsername("john")`                                                            |
| `findAllBy{프로퍼티명}(T value)`        | 주어진 프로퍼티 값에 해당하는 모든 엔티티 조회               | `findAllByUsername("john")`                                                              |
| `countBy{프로퍼티명}(T value)`         | 주어진 프로퍼티 값에 해당하는 엔티티의 개수 조회              | `countByUsername("john")`                                                                |
| `existsBy{프로퍼티명}(T value)`        | 주어진 프로퍼티 값에 해당하는 엔티티가 존재하는지 확인         | `existsByUsername("john")`                                                               |
| `findFirst{Entity}By{프로퍼티명}OrderBy{정렬프로퍼티}(T value)` | 주어진 프로퍼티 값에 해당하는 엔티티 중 정렬된 첫 번째 엔티티 조회 | `findFirstUserByUsernameOrderByAgeDesc("john")`                                           |
| `findFirst{Entity}By{프로퍼티명}OrderBy{정렬프로퍼티}Desc(T value)` | 주어진 프로퍼티 값에 해당하는 엔티티 중 역순으로 정렬된 첫 번째 엔티티 조회 | `findFirstUserByUsernameOrderByAgeDesc("john")`                                           |
| `findFirst{Entity}By{프로퍼티명}OrderBy{정렬프로퍼티}Asc(T value)` | 주어진 프로퍼티 값에 해당하는 엔티티 중 오름차순으로 정렬된 첫 번째 엔티티 조회 | `findFirstUserByUsernameOrderByAgeAsc("john")`                                            |
| `findFirst{Entity}By{프로퍼티명}Limit{number}(T value)` | 주어진 프로퍼티 값에 해당하는 엔티티 중 상위 number 개의 엔티티 조회 | `findFirstUserByUsernameLimit5("john")`                                                   |
| `findAll(Sort sort)`                  | 주어진 정렬 기준에 따라 모든 엔티티 조회                     | `findAll(Sort.by(Sort.Direction.DESC, "age"))`                                            |
| `findAllById(Iterable<ID> ids)`       | 주어진 ID 리스트에 해당하는 모든 엔티티 조회                   | `findAllById(Arrays.asList(1L, 2L, 3L))`                                                  |
| `findAll(Pageable pageable)`          | 주어진 페이징 정보에 따라 모든 엔티티 조회  

### 커스텀 쿼리 메소드

- **예시 코드**

```java
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> findByAgeGreaterThan(int age); // 나이가 주어진 값보다 큰 사용자 조회
    Flux<User> findByUsernameAndEmail(String username, String email); // 사용자 이름과 이메일이 주어진 값과 일치하는 사용자 조회
    Flux<User> findByEmailContaining(String keyword); // 이메일에 특정 키워드를 포함하는 사용자 조회
    Flux<User> findByAgeOrderByUsernameDesc(int age); // 나이가 주어진 값인 사용자를 이름 내림차순으로 정렬하여 조회
    Flux<User> findByUsernameIgnoreCase(String username); // 대소문자 구분 없이 사용자 이름 조회
}
```
