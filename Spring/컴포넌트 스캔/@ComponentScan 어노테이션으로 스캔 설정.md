## @ComponentScan
빈을 등록하는 방법에는 @Component, @Service, @Repository, @Controller, @Configuration 등이 있다.

등록하고자 설정한 빈들을 찾아서 빈으로 만들어 주는 어노테이션이 바로 @ComponentScan이다.

@ComponentScan은 해당 어노테이션이 작성된 패키지 이하의 클래스들을 순회하며 빈으로 등록될 객체들을 탐색한다.
```java
@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig  {
    // ...
}
```
@componentScan 어노테이션의 basePackages 속성값은 org.example이다. 이 속성은 스캔 대상 패키지 목록을 지정한다. 여기서는 한개의 패키지를 설정하였기 때문에 org.example 패키지와 그 하위 패키지에 속한 클래스를 스캔 대상으로 지정한다. 스캔 대상에 해당하는 클래스 중에서 @Component 어노테이션이 붙은 클래스의 객체를 생성해서 빈으로 등록한다.
## ComponentScan의 동작 과정
1. Configuration 파싱 : ConfigurationClassParser가 Configuration 클래스를 파싱한다. @Configuration 어노테이션 클래스를 파싱하는 것이다.
2. ComponentScan 설정 내역을 파싱한다. 개발자는 basePackages, basePackesClasses, excludeFilters, includeFilters, lazyInit, nameGenerator, resourcePattern, scopedProxy 등 컴포넌트들을 스캔하기 위한 설정을 할 것이다. ComponentScanAnnotationParser가 컴포넌트 후보를 모두 찾고, 스캔하기 위하여 해당 설정을 파싱하여 가져온다.
3. Class 로딩 : 위의 basePackage 설정을 바탕으로 모든 클래스를 로딩해야 한다.( *.class ) 클래스로더를 이용하여 모든 자원을 Resource 인터페이스 형태로 불러온다.
4. 빈 정의 설정 : 클래스 로더가 로딩한 리소스(클래스)를 BeanDefinition으로 정의해놓는다. 그리고 beanName의 key값으로 BeanDefinitionRegistry에 등록해 놓는다. 생성할 빈에 대한 정의(메타데이터 같은)라고 보면 될것 같다.
5. 빈 생성 & 주입 : 다시 처음으로 돌아가 AbstractApplicationContext에서 보이는 finishBeanFactoryInitialization(beanFactory); 메소드에서 빈을 생성한다. 위에서 설정한 빈 정의를 바탕으로 객체를 생성하고, 주입한다.
<br>
쉽게 설명해서 Configuration 클래스 및 Annotation에 사용하는 설정들을 파싱한다. 그리고 basePackage 밑의 모든 .class 자원을 불러와서 component 후보인지 확인하여 BeanDefinition (빈 생성을 위한 정의)을 만든다. 생성된 빈 정의를 바탕으로 빈을 생성하고 의존성있는 빈들을 주입한다.
