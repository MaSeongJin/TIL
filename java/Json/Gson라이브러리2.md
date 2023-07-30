## 값이 null인 field의 Json property 생성
```java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class NullObject {
    public static void main(String[] args) {
        // 이름이 null인 Student 객체
        Student student = new Student(1, null);
 
        // 1. default
        // 값이 null인 field는, Json에 포함시키지 않습니다.
        Gson gsonWithoutNull = new Gson();
        String studentJson = gsonWithoutNull.toJson(student);
 
        System.out.println(studentJson); // {"id":1}
 
        
        // 2. serializeNulls 옵션
        // 값이 null인 field도, Json에 포함시킵니다.
        Gson gsonWithNull = new GsonBuilder().serializeNulls().create();
        String studentJsonWithNull = gsonWithNull.toJson(student);
 
        System.out.println(studentJsonWithNull); // {"id":1,"name":null} 
    }
}
```
**Gson gsonWithoutNull = new Gson();**<br>
Default로 Gson 객체를 생성하고, 이 객체로 Json 문자열을 생성하면,<br>
Json 문자열로 변환될 객체(여기서는 student)의 field 값이 null일 경우,<br>
null인 field는 Json의 property로 생성하지 않습니다.

**Gson gsonWithNull = new GsonBuilder().serializeNulls().create();**<br>
Gson 객체를 생성할 때, serializeNulls 옵션을 추가하면,<br>
Json 문자열로 변환될 객체의 field 값이 null일 경우,<br>
null인 field도 Json의 property로 생성합니다.

<br>

## Field 제외하고, Json 문자열 만들기
### transient
transient는 객체를 직렬화(Serialize)할 때, 특정 필드를 제외하고 싶을 떄 사용한다.<br>
제외하려고 하는 필드에 transient를 붙이면, 해당 필드를 제외하고 Json문자열이 생성된다.
```java
public class Student {
    private transient int id;
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
**id 필드 제외**
```java
import com.google.gson.Gson;
 
public class ExcludeTransient {
    public static void main(String[] args) {
        // Student 객체 생성
        Student student = new Student(1, "Anna");
 
        // Gson 객체 생성
        Gson gson = new Gson();
 
        // Student 객체 -> Json 문자열
        String studentJson = gson.toJson(student);
 
        // Json 문자열 출력
        System.out.println(studentJson); // {"name":"Anna"}
    }
}
```

<br>

### @Expose Annotation
객체가 Json 문자열로 변환되거나(Serialized), Json 문자열이 객체로 변환될 때(Deserialized) <br>
Gson의 '@Expose' 어노테이션을 이용하여 특정 필드가 포함될지, 포함되지 않을지 결정 할 수 있습니다.
```java
@Expose(serialize = true);
@Expose(serialize = false);
@Expose(deserialize = true);
@Expose(deserialize = false);
@Expose(serialize = true , deserialize = false);
@Expose(serialize = false, deserialize = true);
```
- 특정 필드의 serialize 값이 true이면, 객체가 Json 문자열로 변환될 때, 해당 필드가 포함된다.
- 특정 필드의 serialize 값이 false이면, 객체가 Json 문자열로 변환될 때, 해당 필드가 제외된다.
- 특정 필드의 deserialize 값이 true이면, Json 문자열이 객체로 변환될 때, 필드에 값이 세팅된다.
- 특정 필드의 deserialize 값이 false이면, Json 문자열이 객체로 변환될 때, 필드에 값이 세팅되지 않는다.
- @Expose 어노테이션은, excludeFieldsWithoutExposeAnnotation 옵션이 적용된 Gson 객체로, Json을 생성하거나(toJson()), 객체를 생성할 때만(fromJson()) 유효하다.
- excludeFieldsWithoutExposeAnnotation 옵션이 적용된 Gson 객체로, Json을 생성하거나(toJson()), 객체를 생성할 때(fromJson()) @Expose 어노테이션이 없으면, 해당 필드는 무시됩니다.

**@Expose 가 적용된 Student 클래스**
```java
import com.google.gson.annotations.Expose;
 
public class Student {
    @Expose(serialize = false, deserialize = true)
    private int id;
 
    @Expose(serialize = true, deserialize = false)
    private String name;
 
    private String major;
 
    public Student(int id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }
 
    @Override
    public String toString() {
        return "Student [id=" + id + ", major=" + major + ", name=" + name + "]";
    }
}
```
@Expose(serialize = false, deserialize = true)
private int id;
'id' field는
객체 -> Json 변환시에는 제외되고,
Json -> 객체 변환시에는 포함됩니다.

@Expose(serialize = true, deserialize = false)
private String name;
'name' field는
객체 -> Json 변환시에는 포함되고,
Json -> 객체 변환시에는 제외됩니다.

private String major;
@Expose 어노테이션이 명시되지 않은
'major' 필드는,
객체 -> Json 변환시에도 제외되고,
Json -> 객체 변환시에도 제외됩니다.
















