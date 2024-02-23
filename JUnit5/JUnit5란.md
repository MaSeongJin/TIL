### JUnit 5란?

세 가지 하위 프로젝트의 여러 모듈로 구성됨

`JUnit 플랫폼 + JUnit Jupiter + JUnit 빈티지`

#### JUnit 플랫폼

-  JVM에서 테스트 프레임워크를 시작하기 위한 기반 역할
  
- TestEngine 플랫폼에서 실행되는 테스트 프레임워크를 개발하기 위한 API를 정의
  
- 명령줄에서 플랫폼을 실행하기 위한 Console Lancher와 플랫폼에서 하나 이상의 테스트 엔진을 사용하여 사용자 정의 테스트 스위트를 실행하기 위한 JUnit Platform Suite Engine을 제공
  

#### JUnit Jupiter

- JUnit 5에서 테스트 및 확장을 작성하기 위한 프로그래밍 모델과 확장 모델의 조합
  
- Jupiter 서브 프로젝트는 플랫폼에서 Jupiter 기반 테스트를 실행하기 위한 Test Engine을 제공
  

#### JUnit Vintage

- 플랫폼에서 JUnit 3 및 JUnit 4 기반 테스트를 실행하기 위한 Test Engine을 제공
  
- 클래스 경로 또는 모듈 경로에 JUnit 4.12 이상이 있어야함
