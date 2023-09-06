## JSP

- Servlet 표준을 기반으로 작성된 웹 어플리케이션 개발 언어
  
- HTML 내에 Java를 작성하여 동적으로 웹페이지를 생성하여 브라우저에게 돌려주는 페이지
  
- 실행 시 Servlet으로 변환된 후 실행
  

### JSP 동작 방식

![ㅇ](https://i.postimg.cc/x8Hysbnb/995-C554-D5-C63-B9-C736.png)

1. 사용자의 브라우저는 http://servername.../*.jsp 형태로 요청을 한다.
  
2. 웹 서버는 사용자의 요청을 JSP 컨테이너로 넘긴다.
  
3. 만일 JSP 파일이 처음 요청된 경우라면 JSP 파일을 파싱한다. ( 파싱: JSP 파일이 오타나 논리적 오류가 없는지 자세하게 읽는 것) 그렇지 않은 경우라면 6) 의 단계로 간다. 
  

4. JSP 파일로부터 서블릿이라는 자바 파일이 생성된다. 
  
5. 서블릿 파일이 컴파일 과정을 거쳐 클래스 파일로 된다.
  
6. 클래스 파일이 메모리가 적재 되어 실행된다.
  
7. 실행 결과가 웹 서버에 넘겨져 웹 서버는 HTML 형태로 사용자에게 응답을 전송한다. 
  
8. HTML 형태의 페이지를 브라우저에서 실행시켜 사용자가 웹 페이지를 확인할 수 있다.
  

### JSP의 생명 주기

init() -> service() ->destroy()  / 초기화 -> 요청 처리 -> 소멸

초기화 단계에서 초기화 작업 진행 후, 요청 처리단계에서 실제로 사용자의 요청을 처리한다.

그리고 처리한 결과를 응답으로 생성한 후, 소멸단계를 거쳐 필요했던 모든 자원을 해체하는 방식을 가진다.

### JSP 구성 요소

- 스크립트 : 스크립트릿(Scriptlet), 표현식(Expression), 선언부(Declaration)
  
- 지시자(Directive)
  
- JSP 기본객체
  
- 표현언어(Expression Language)
  
- Action Tag
  
- JSTL
  

#### 스크립트

JSP 문서 내용을 동적으로 생성하기 위해 사용된다.

**스크립트 요소**

| 종류  | 사용용도 | 형식  |
| --- | --- | --- |
| 스크립트릿(scriptlet) | 자바코드 작성 | <% %> |
| 선언(declaration) | 변수와 메소드를 선언 | <%! %> |
| 표현식(expression) | 계산식이나 함수를 호출한 결과를 문자열 형태로 출력 | <%= %> |
| 주석(comment) | JSP 페이지 설명 작성 | <%-- --%> |
| 지시자(directive) | JSP 페이지 속성 지정 | <%@ %> |

#### 지시자(Directive)

- 웹컨테이너(Tomcat)가 JSP 번역하고 실행하는 방법을 서술
  
- page : 해당 JSP 페이지 전반적으로 환경을 설정할 내용 지정
  
- include : 현재 페이지에 다른 파일의 내용을 삽입할 때 사용
  
- taglib : 태그 라이브러리에서 태그를 사용할 수 있는 기능 제공
  

##### page

- JSP 페이지 실행 매개변수를 제어
  
- 출력처리, 오류처리 등의 내용을 포함
  

**형태**

- `<%@ 디렉티브이름 속성1="값1" 속성2="값2" ... %>`
  
- `<%@ page contentType="text/html; charset=utf-8" %>`
  
- 디렉티브 이름 : page
  
- 속성 : contentType
  
- 값 : "text/html; charset=utf-8"
  

| 속성  | 설명  | 기본값 |
| --- | --- | --- |
| language | 스크립트에서 사용할 언어 지정 | java |
| contentType | JSP가 생성할 문서의 MIME 타입과 캐릭터 인코딩 | text/html |
| import | JSP에서 사용할 java 클래스를 지정 |     |
| session | JSP가 세션을 사용할지 여부 지정 | true |
| buffer | JSP 페이지의 출력 버퍼 크기 지정, 'none'일 경우 출력 버퍼를 사용하지 않음 | 8kb |
| errorPage | JSP 실행 중 에러 발생 시 출력할 페이지 지정 |     |
| isErrorPage | 에러가 발생했을 때 보여줄 페이지인지 지정 | false |
| pageEncoding | JSP 소스코드의 인코딩 지정 | ISO-8859-1 |
| info | 현재 JSP 페이지에 대한 설명 |     |
| autoflush | 버퍼의 내용을 자동으로 브라우저로 보낼지에 대한 설정 | true |
| isThreadsafe | 현재 JSP가 멀티쓰레드로 동작해도 안전한지 여부를 설정, 'false'일 경우 SingleThread로 동작 | true |
| extends | JSP 페이지를 기본적인 클래스가 아닌 다른 클래스를 상속도록 변경 | javax.servlet.jsp.Page |

##### include

- JSP 내에 다른 HTML 문서나 JSP 페이지의 내용을 삽입할 때 사용
  
- 반복적으로 사용되는 부분(header, footer 등) 별도로 작성하여 페이지 내에 삽입하면 반복되는 코드의 재작성을 줄일 수 있다.
  
- `<%@ include file="/template/header.jsp" %>`
  

##### taglib

- JSTL 또는 사용자가 작성한 커스텀 태그를 사용할 때 작성한다.
  
- 불필요한 자바 코드를 줄일 수 있다.
  
- `<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>`
  

#### JSP 기본객체

- JSP에서는 서버 정보 및 요청, 응답에 대한 데이터를 조회, 조작하기 위한 객체를 제공하고 있다.
  

| 내장 객체 | 타입  | 설명  |
| --- | --- | --- |
| request | javax.servlet.http.HttpServletRequest | 요청 정보를 저장하는 객체 |
| response | javax.servlet.http.HttpServletResponse | 응답 정보를 저장하는 객체 |
| pageContext | javax.servlet.jsp.PageContext | JSP 페이지 정보를 저장하는 객체 |
| session | javax.servlet.http.HttpSession | HTTP 세션 정보를 저장하는 객체 |
| application | javax.servlet.ServletContext | 웹 어플리케이션 context 정보를 저장하는 객체 |
| out | javax.servlet.jsp.jspWriter | 결과를 출력하기 위해서 사용하는 스트림 |
| config | javax.servlet.ServletConfig | JSP 페이지 설정 정보를 포함하는 객체 |
| page | javax.lang.Object | JSP 페이지를 구현한 자바 클래스 인스턴스 |
| exception | javax.lang.Throwable | 에러페이지에서 사용하는 객체, 오류정보를 담고 있다. |

##### JSP 기본 객체 영역(Scope)

![ㄹㄹff](https://i.postimg.cc/4Nwg5XQB/download.png)

- **Page(pageContext)**
  
  - 하나의 페이지 정보를 담고 있는 영역, 페이지가 바뀌면 새로운 객체가 생성됨
    
- **Request(request)**
  
  - 하나의 요청을 처리할 때 사용되는 영역, 응답이 완료되면 사라진다.
    
- **session(session)**
  
  - 하나의 웹 브라우저와 관련된 영역, 로그인 정보 등을 저장한다.
    
- **application(application)**
  
  - 웹 어플리케이션 영역, 어플리케이션이 시작되면 종료될 때까지 유지된다.
    

##### JSP 기본 객체 영역(Scope) 메소드

- servlet과 페이지간 정보를 공유하기 위해서 메소드를 지원한다.
  

각 영역에서 사용할 수 있는 공통 메소드

| 메소드 | 반환형 | 설명  |
| --- | --- | --- |
| setAttribute(String name, Object value) | void | key-value 형태로 각 영역에 데이터를 저장. name이 value를 얻어 오기 위한 key가 된다. |
| getAttribute(String name) | Object | 현재 객체에서 인자로 받든 이름으로 설정된 값을 반환 |
| getAttributeNames() | Enumeration | 현재 객체에서 설정된 값의 모든 속성의 이름을 반환 |
| removeAttribute(String name) | void | 현재 객체에서 인자로 받은 이름으로 설정된 값을 삭제 |

### 페이지 이동

요청(request)을 받아서 화면을 변경하는 방법은 두 가지가 있다.

- 포워드 방식
  
  - 요청이 들어오면 요청을 받은 JSP 또는 Servlet이 직접 응답을 작성하지 않고, 요청을 서버 내부에서 전달하여 해당 요청을 처리하게 하는 방식
    
  - `RequestDispatcher dispatcher = request.getRequestDispatcher("이동할 페이지");`
    
  - request, response 객체가 전달되어 사용되기 때문에 객체가 사라지지 않는다. 브라우저에는 최초 요청한 주소가 표시된다.
    
- 리다이렉트 방식
  
  - 요청이 들어오면 내부 로직 실행 후, 브라우저의 URL을 변경하도록 하여 새로운 요청을 생성함으로써 페이지를 이동한다.
    
  - `response.sendRedirect("location");`
    
  - 브라우저가 새로운 요청을 만들어 내기 때문에 최초 요청 주소와 다른 요청주소가 화면에 보여진다.
