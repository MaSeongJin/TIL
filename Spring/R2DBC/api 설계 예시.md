# Spring R2DBC를 활용한 API 예시

## 1. 의존성 추가

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-r2dbc</artifactId>
</dependency>
<dependency>
    <groupId>io.r2dbc</groupId>
    <artifactId>r2dbc-h2</artifactId> <!-- 또는 다른 데이터베이스 드라이버를 선택 -->
    <scope>runtime</scope>
</dependency>
```

## 2. 데이터베이스 설정

```properties
spring.r2dbc.url=r2dbc:h2:mem:///testdb
spring.r2dbc.username=sa
spring.r2dbc.password=password
spring.r2dbc.pool.initial-size=2
spring.r2dbc.pool.max-size=4
```

## 3. Entity 클래스 생성

```java
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("items")
public class Item {
    @Id
    private Long id;
    private String name;
    private String category;

    // 생성자, 게터, 세터 생략
}
```

## 4. Repository 인터페이스 생성

```java
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {
}
```

## 5. 서비스 클래스 생성

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Flux<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Mono<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Mono<Item> saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Mono<Void> deleteItem(Long id) {
        return itemRepository.deleteById(id);
    }

    public Flux<Item> findItemsByCategory(String category) {
        return itemRepository.findAll()
                .filter(item -> item.getCategory().equals(category));
    }

    public Mono<Item> updateItem(Long id, Item updatedItem) {
        return itemRepository.findById(id)
                .flatMap(existingItem -> {
                    existingItem.setName(updatedItem.getName());
                    existingItem.setCategory(updatedItem.getCategory());
                    return itemRepository.save(existingItem);
                });
    }

    public Flux<Item> searchItems(String keyword) {
        return itemRepository.findAll()
                .filter(item -> item.getName().contains(keyword));
    }
}
```

## 6. 응답 객체 생성

```java
public class ItemResponse {
    private Long id;
    private String name;
    private String category;

    // 생성자, 게터, 세터 생략
}
```

## 7. 컨트롤러 생성

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public Flux<ItemResponse> getAllItems() {
        return itemService.findAllItems().map(this::mapToResponse);
    }

    @GetMapping("/{id}")
    public Mono<ItemResponse> getItemById(@PathVariable Long id) {
        return itemService.findItemById(id).map(this::mapToResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ItemResponse> createItem(@RequestBody Item item) {
        return itemService.saveItem(item).map(this::mapToResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }

    @GetMapping("/category/{category}")
    public Flux<ItemResponse> getItemsByCategory(@PathVariable String category) {
        return itemService.findItemsByCategory(category).map(this::mapToResponse);
    }

    @PutMapping("/{id}")
    public Mono<ItemResponse> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        return itemService.updateItem(id, updatedItem).map(this::mapToResponse);
    }

    @GetMapping("/search")
    public Flux<ItemResponse> searchItems(@RequestParam String keyword) {
        return itemService.searchItems(keyword).map(this::mapToResponse);
    }

    private ItemResponse mapToResponse(Item item) {
        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setCategory(item.getCategory());
        return response;
    }
}
```
