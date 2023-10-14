## 스프링이란?
스프링(Spring)은 자바 엔터프라이즈 개발을 편하게 해주는 오픈소스 경량급 애플리케이션 프레임워크이다.
- SpringBoot
  - 스프링 부트는 웹 프로그램을 쉽고 빠르게 만들 수 있도록 도와주는 웹 프레임워크
- Spring Cloud
  - 분산/버전 관리, 서비스 등록 및 검색 가능, 라우팅, 서비스 간 호출, 부하분산, 회로차단기, 분산 메시지 등의 기능을 사용할 수 있는 도구
- Spring Batch
  - 엔터프라이즈 시스템의 일상적인 운영에 필수적인 강력한 배치 애플리케이션을 개발할 수 있도록 설계된 가볍고 포괄적인 배치 프레임워크
 
## 프레임워크(Frame Work)
- 프레임워크는 어떠한 목적을 달성하기 위해, 복잡하게 얽혀 있는 문제를 쉽게 해결하기 위한 약속이자 도구이며, 소프트웨어 개발에 하나의 뼈대 역할을 한다.
- 프레임워크는 자주 쓰일 만한 기능들을 한데 모아 놓은 클래스들의 모음이다.
- 의자를 만든다고 가정 할때 의자를 만드는 망치 나 못 같은 개념이다.
  
## Spring 특징
- 컨테이너 역할
  - Spring 컨테이너는 Java 객체의 LifeCycle을 관리하며, Spring 컨테이너로부터 필요한 객체를 가져와 사용할 수 있다.
- DI(Dependecy Injection) 지원
  - Spring은 설정 파일이나 어노테이션을 통해서 객체 간의 의존관계를 설정할 수 있도록 하고 있다.
  - 프로그램에서 구성 요소의 의존 관계가 소스코드 내부가 아닌 외부의 설정 파일을 통해 정의 되는 방식이다.
  - 코드 간의 재사용을 높이고, 소스코드를 다양한 곳에 사용하며 모듈 간의 결합도를 낮출 수 있다.
  - 대표적으로 라이브러리나 API, 프레임워크를 연동 할 때 연결하는 소스코드를 직접 작성하는게 아닌 외부 파일을 연결해 불러오는 방식이다.
- AOP(Aspect Oriented Programming) 지원
  - Spring은 트랜젝션이나 로깅, 보안과 같이 공통적으로 필요로 하는 모듈들을 실제 핵심 모듈에서 분리해서 적용할 수 있다.
  - 각각의 클래스가 있다고 가정하자. 각 클래스들은 서로 코드와 기능들이 중복되는 부분이 많다. 코드가 중복될 경우 실용성과 가독성 및 개발 속도에 좋지 않다. 중복된 코드를 최대한 배제하는 방법은 중복되는 기능들을 전부 빼놓은 뒤 그 기능이 필요할때만 호출하여 쓰면 훨씬 효율성이 좋다.
  - 즉, AOP는 여러 객체에 공통으로 적용할 수 있는 기능을 구분함으로써 재사용성을 높여주는 프로그래밍 기법이다.
- POJO(Plain Old Java Object) 지원
  - POJO는 Java EE를 사용하면서 해당 플랫폼에 종속되어 있는 무거운 객체들을 만드는 것에 반발하여 나타난 용어이다.
  - 별도의 프레임 워크 없이 Java EE를 사용할 때에 비해 인터페이스를 직접 구현하거나 특정 클래스를 상속받을 필요가 없어 기존 라이브러리를 지원하기 용이하고, 객체가 가볍다.
- 트랜젝션 처리를 위한 일관된 방법을 지원
  - JDBC, JTA 등 어떤 트랜젝션을 사용하던 설정을 통해 정보를 관리하므로 트랜젝션 구현에 상관없이 동일한 코드 사용 가능합니다.
- 영속성과 관련된 다양한 API 지원
  - Spring은 Mybatis, Hibernate 등 데이터베이스 처리를 위한 ORM(Object Relational Mapping)프레임워크들과의 연동 지원합니다.

## Spring 기능 요소
<p align="center"><img src="https://i.postimg.cc/9QBMSLty/img1-daumcdn.png"></p>

### Core Container
- 스프링 프레임 워크의 핵심은 Bean의 생명 주기와 설정 그리고 처리방법을 관리하는 코어 컨테이너이다.
- 모든 스프링 모듈의 기반이다.
  - Core 및 Beans
    - IOC/DI기능을 지원하는 담당이다.
    - BeanFactory를 기반으로 Bean 클래스들을 제어할 수 있는 기능을 지원한다.
    - Core는 스프링에서 사용하는 핵심모듈이며 주요 어노테이션, 컨버터, 상수, 유틸리티 클래스 등을 제공한다.
    - Beans는 스프링 DI기능의 핵심인 bean Factory와 DI기능을 제공하는 모듈이다.
  - Context
    - Bean들을 포함하여 여러 기능을 가진 공간이다.
    - Beans모듈에 더하여 국제화, 애플리케이션 생명주기, 이벤트, 유효성 검증, 투명한 Context 생성 등을 제공한다.
    - Java EE 기술(JNDI, Mail, JMS, EJB 등)과의 연동을 위한 Adapter들을 제공한다.
### AOP (Aspect Oriented Programming)
- 관점 지향 프로그래밍을 풍부하게 지원하고 객체 간의 결합력을 낮추게 도와준다.
- 트랜젝션이나 로깅, 보안과 같이 공통적으로 필요로 하는 모듈들을 실제 핵심 모듈에서 분리해서 적용할 수 있다.
### Instrumentation
- JVM에 에이전트를 추가하는 기능을 제공, 정확히는 톰캣용 위빙 에이전트(weaving agent)를 제공한다.
- 톰캣은 클래스 로더 되는 클래스 파일을 변환한다.
### Data Access / Integration
- JDBC, ORM, 트랜잭션 등 서비스 추상화를 이용해 쉽게 데이터에 접근하는 방법을 제공
  - JDBC
    - 일관된 방법으로 개발하는 것이 가능하도록 추상화된 레이어를 제공
  - ORM (Object Relation Mapping)
    - 프레임워크인 Hibernate, iBatis, JDO, JPA와의 통합을 지원
  - OXM (Object/XML Mapping)
    - Object와 XML 간의 변환을 위한 추상 계층을 제공
    - JAXB, Castor, XMLBeans, JiBX, XStream
  - JMS (Java Message Service)
    - 메시징 처리를 위한 모듈을 제공
  - Transaction
    - 스프링의 데이터에 직접적인 트랜잭션 관리
    - 언적인 트랜잭션 관리에 있어 일관된 추상화를 제공하고 DataAcssessException 예외 계층 구조와 트랜잭션 동기화 저장소 JCA기능을 제공하거나 포함
### Web
- 다양한 MVC프레임워크와 같이 사용 가능하지만 자체적으로도 Spring 모듈이 만들어져 있고 HTTP호출자나 REST API 모듈을 제공
  - Web
    - 기본적인 웹 기반을 위한 공통적 기능을 제공 및 정의한 모듈, Multipart Request 등의 다양한 기능을 지원
    - 다중 FileUpload처리, 리스너와 웹 기반으로 하는 application context를 위한 IOC컨테이너의 초기화를 제공
  - Servlet
    - 웹 어플리케이션 구현을 위한 Spring MVC를 제공 전통적인 MVC와 @MVC기능을 모두 포함
    - Struts, JSF, WebSocket과 같은 프레임 워크의 통합을 지원
  - WebSocket
    - JSR-356 스펙에 따라 구현된 WebSocket프로그래밍을 지원하는 모듈
  - Portlet
    - 포털 기반의 MVC 구현을 위한 모듈을 제공

## Spring 구조 및 동작 
<p align="center"><img src="https://i.postimg.cc/tTRp6bLc/img1-daumcdn.png"></p>
Client 요청(Request) -> DispatcherServlet -> HandlerMapping -> Controller -> Service -> DAO -> DB -> DAO -> Service -> Controller -> DispatcherServlet -> ViewResolver -> View -> 응답(Response)


















