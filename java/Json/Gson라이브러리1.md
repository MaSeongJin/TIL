## Gson이란
Gson은 Java에서 Json을 파싱하고, 생성하기 위해 사용되는 구글에서 개발한 오픈소스이다.<br>
Java Object를 Json 문자열로 변환할 수 있고, Json 문자열을 Java Object로 변환할 수 있다.

<br>

## Gson 라이브러리 추가하기
Json 파싱에 사용할 json-simple 라이브러리를 추가하기위해 pom.xml 파일에 dependency 추가<br>
mvnrepository 링크에서 Gson 검색 후 버전 선택 후 복사<br>
Link: https://mvnrepository.com/artifact/com.google.code.gson/gson

<br>

## Gson 객체 생성하기
- new Gson();
- new GsonBuilder.create();

new GsonBuilder()를 이용하여 Gson 객체 생성시, 몇 가지 옵션을 추가하여 객체 생성 가능
```java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class CreateGson {
    public static void main(String[] args) {
        // new
        Gson gson1 = new Gson();
 
        // GsonBuilder
        Gson gson2 = new GsonBuilder().create();
        Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
    }
}
```

<br>

## Json 생성하기
```java
import com.google.gson.Gson;
import com.google.gson.JsonObject;
 
public class GsonExample {
    public static void main(String[] args) {
        Gson gson = new Gson();
 
        // Json key, value 추가
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "anna");
        jsonObject.addProperty("id", 1);
 
        // JsonObject를 Json 문자열로 변환
        String jsonStr = gson.toJson(jsonObject);
 
        // 생성된 Json 문자열 출력
        System.out.println(jsonStr); // {"name":"anna","id":1} 
    }
}
```

<br>

## Object -> Json 변환
```java
public class Student {
    private int id;
    private String name;
 
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
 
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}
```
```java
import com.google.gson.Gson;
 
public class ObjectToJson {
    public static void main(String[] args) {
        // Student 객체 생성
        Student student = new Student(1, "Anna");
 
        // Gson 객체 생성
        Gson gson = new Gson();
 
        // Student 객체 -> Json 문자열
        String studentJson = gson.toJson(student);
 
        // Json 문자열 출력
        System.out.println(studentJson);  // {"id":1,"name":"Anna"}
    }
}
```

<br>

## Json -> Object 변환
```java
import com.google.gson.Gson;
 
public class JsonToObject {
    public static void main(String[] args) {
        // Json 문자열
        String jsonStr = "{\"id\":1,\"name\":\"Anna\"}";
 
        // Gson 객체 생성
        Gson gson = new Gson();
 
        // Json 문자열 -> Student 객체
        Student student = gson.fromJson(jsonStr, Student.class);
 
        // Student 객체 toString() 출력
        System.out.println(student); // Student [id=1, name=Anna]
    }
}
```

<br>

## Map -> Json 문자열 변환
```java
import com.google.gson.Gson;
 
public class MapToJson {
    public static void main(String[] args) {
        // Map
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "Anna");
 
        // Map -> Json 문자열
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
 
        // Json 문자열 출력
        System.out.println(jsonStr); // {"name":"Anna","id":"1"}
    }
}
```

<br>

## Json 문자열 -> Map 변환
```java
import java.util.Map;
import com.google.gson.Gson;
 
public class JsonToMap {
    public static void main(String[] args) {
        // Json 문자열
        String jsonStr = "{\"id\":\"1\",\"name\":\"Anna\"}";
 
        // Gson 객체 생성
        Gson gson = new Gson();
 
        // Json 문자열 -> Map
        Map<String, Object> map = gson.fromJson(jsonStr, Map.class);
 
        // Map 출력
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
```
**결과**
```java
id = 1
name = Anna
```

<br>

## Json 문자열 보기 편하게 출력
```java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class PrettyPrinting {
    public static void main(String[] args) {
        // Json 문자열로 변환할 Student 객체
        Student student = new Student(1, "Anna");
 
        // PrettyPrinting 옵션을 추가하여 Gson 객체 생성
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
 
        // Student 객체 -> Json 문자열
        String studentJson = gson.toJson(student);
 
        // Json 문자열 출력 ( 예쁘게 출력 )
        System.out.println(studentJson);
    }
}
```
**결과**
```java
{
  "id": 1,
  "name": "Anna"
}
```

참고링크 : https://hianna.tistory.com/629
