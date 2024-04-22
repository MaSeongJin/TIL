## EntityCallbacks

- R2DBC는 리액티브 프로그래밍을 지원하는 리액티브 스트림을 사용하여 관계형 데이터베이스와 상호 작용할 수 있는 비동기적인 JDBC 대안이다.
- R2DBC의 `EntityCallbacks`는 R2DBC의 기능 중 하나로, 엔티티의 라이프사이클 이벤트에 대한 콜백을 제공한다.
- 이 콜백을 사용하면 엔티티의 특정 이벤트가 발생할 때 실행할 로직을 정의할 수 있다.

> `EntityCallbacks`는 보통 Spring Data R2DBC와 함께 사용된다.
> Spring Data R2DBC는 스프링 기반 애플리케이션에서 R2DBC를 사용하기 쉽도록 도와주는 프레임워크이다.
> 여기서 엔티티는 데이터베이스 테이블과 매핑되는 자바 객체를 의미한다.
> 각각의 엔티티는 라이프사이클을 가지고 있고, 이 라이프사이클은 특정 이벤트로부터 시작되어 끝이난다.
> 예를 들어, 엔티티의 저장, 업데이트, 삭제와 같은 작업은 모두 라이프사이클 이벤트에 해당한다.

`EntityCallbacks`는 다음과 같은 주요 메소드를 가지고 있다:

- `EntityCallback<T>`: 주어진 엔티티 타입 `T`에 대한 콜백을 정의
- `onBeforeConvert`: 엔티티가 데이터베이스로 변환되기 전에 실행, 예를 들어, 엔티티의 일부 필드를 수정할 수 있다.
- `onBeforeSave`: 엔티티가 저장되기 전에 실행되며, 주로 엔티티에 대한 검증 또는 수정이 이루어진다.
- `onAfterSave`: 엔티티가 저장된 후에 실행됨. 저장된 엔티티에 대한 후속 작업을 수행할 수 있다.
- `onBeforeUpdate`: 엔티티가 업데이트되기 전에 실행됨. 업데이트 전에 엔티티의 상태를 검사하거나 수정할 수 있다.
- `onAfterUpdate`: 엔티티가 업데이트된 후에 실행됨. 업데이트 이후의 작업을 수행할 수 있다.
- `onBeforeDelete`: 엔티티가 삭제되기 전에 실행됨. 엔티티를 삭제하기 전에 추가 작업을 수행할 수 있다.
- `onAfterDelete`: 엔티티가 삭제된 후에 실행됨. 삭제 이후의 작업을 수행할 수 있다.

예를 들어, 다음은 `EntityCallbacks`를 사용하여 간단한 로깅을 구현하는 방법이다:

```java
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoggingEntityCallback implements BeforeSaveCallback<Entity> {

    @Override
    public Mono<Entity> onBeforeSave(Entity entity, SqlIdentifier table) {
        System.out.println("Entity is being saved: " + entity);
        return Mono.just(entity);
    }
}
```
##### 이런 식으로 엔티티의 저장 이전에 메시지를 출력하는 콜백을 정의할 수 있다. 이 콜백은 Spring Data R2DBC에서 자동으로 감지되어 엔티티가 저장되기 전에 호출된다.
