## JavaScript
### DOM(Document Object Model)
- window 제공 함수
  - alert
  - confirm
  - prompt
  - open
  - parseInt, parseFloat
  - setTimeout, clearTimeout
  - setInterval, clearInterval<br><br>
[![11.png](https://i.postimg.cc/htMxb9rY/11.png)](https://postimg.cc/BjLXJPR5)<br>
- DOM
  - XML, HTML 문서의 각 항목을 계층으로 표현하여 생성, 변형, 삭제할 수 있도록 돕는 인터페이스
  - DOM은 문서 요소 집합을 트리 형태의 계층 구조로 HTML 표현
  - HTML 문서의 요소를 제어하기 위해 지원
  - 상단의 document 노드를 통해 접근<br><br>
[![123.png](https://i.postimg.cc/dt9P61TX/123.png)](https://postimg.cc/gnrTkGsq)<br>
- 문서 접근 방식 이해
  - getElementById(string)
    - ` var ele = document.getElementById("id"); `
  - querySelector(css selector)
    - ` var ele = document.querySelector("#id"); `
    - ` var ele = document.querySelector("div"); `
    - ` var ele = document.querySelector(".class"); `
    - ` var ele = document.querySelector("[name='name']"); `
  - querySelectorAll(css selector)
    - querySelector와 사용방식 동일
    - 결과를 배열처럼 사용한다.
    - ` var list = document.querySelectorAll("div"); `
    - ``` javascript
      for (var i = 0; i < list.length; i++){
        console.log(list[i])
      }
      ```
    - ` var ele = document.querySelectorAll("#id"); `
    - ` var ele = document.querySelectorAll("div"); `
    - ` var ele = document.querySelectorAll(".class"); `
    - ` var ele = document.querySelectorAll("[name='name']"); `
 - 문서 조작 방식 이해
   - createElement(tagName)
     - ``` javascript
       // 엘리멘트 생성
       var ele = document.createElement("img");
       // 추가할 기존 엘리먼트 접근
       var parent = document.getElementById("list");
       // 엘리먼트 추가
       parent.append(ele)
       ```
   - createTextNode(text)
   - appendChild(node)
   - append(String | node)
   - removeChild(node)
   - setAttribute(name, value)
     - ``` javascript
       var ele = document.createElement("img");
       // 생성된 img 엘리먼트에 속성 추가하기
       ele.setAttribute("src", "./images/img.jpg");
       ele.setAttribute("width", 200);
       ele.setAttribute("height", 150);
       // --------------------------------------------
       ele.src = "./images/img.jpg";
       ele.width = 200;
       ele.height = 150;       
       ```
     - 사용자 정의 속성은 setAttribute를 사용해야 적용된다.
   - innerHTML
     - ``` javascript
       var list = document.getElementById("list");
       list.innerHTML = "<img src = './images/img.jpg'/>";
       ```
   - innerTEXT
- Event
  - Event
    - 웹 페이지에서 여러 종류의 상호작용이 있을 때 마다 이벤트가 발생
    - 마우스를 이용했을 때, 키보드를 눌렀을 때 등 많은 이벤트가 존재
    - JavaScript를 사용하여 DOM에서 발생하는 이벤트를 감지하고 대응하는 작업을 수행할 수 있음
  - 이벤트 종류
    - 키보드 : keyup, keydown, keypress
    - 마우스 : click, mousemove, mouseup, mousedown, mouseenter, mouseleave
    - 로딩 : load, unload
    - 폼 : input, change, blur, focus, submit
  - 이벤트 처리 방식의 이해
    - 고전 이벤트 처리 방식 : attribute / property 방식으로 등록
    - 표전 이벤트 처리 방식 : addEventListener 메소드 이용
  - 고전 이벤트 처리방식 1
    - 인라인 이벤트 설정 -> 엘리먼트에 직접 지정
    - 설정하려는 이벤트를 정하고 on이벤트 종류의 형식으로 지정
    - `<button onclick="alert('click')">클릭</button>`
  - 고전 이벤트 처리방식 2
    - 엘리먼트에서 이벤트를 직접 설정하지 않고 스크립트에서 이벤트 설정
    - ``` javascript
      var btn = document.querySelector("#btn");
      btn.onclick = doAction;
      function doAction() {
        alert("클릭");
      }
      ```
  - 표준 이벤트 처리 방식
    - 이벤트 요소.addEventListener(이벤트타입, 이벤트리스트, [option]);
    - ``` javascript
      var btn = document.querySelector("#btn");
      btn.addEventListener("click", doAction);
      function doAction() {
        alert("클릭");
      }
      ```
  - 이벤트 전파(Event propagation)
    - 캡쳐링 단계(capturing phase) : 이벤트가 상위 요소에서 하위 요소 방향으로 전파
    - 타깃 단계(target phase) : 이벤트가 타깃에 도달
    - 버블링 단계(bubling phase) : 이벤트가 하위 요소에서 상위 요소 방향으로 전파
  - 고전처리방식 vs 표준 처리 방식
    - 고전 처리방식 : 타깃 단계와 버블링 단계의 이벤트만 캐치 가능
    - 표준 처리방식 : 타깃 단계와 버블링 단계 뿐만 아니라 캡쳐링 단계의 이벤트도 선별적으로 캐치 가능
    - 캡쳐링 단계의 이벤트를 캐치하려면 addEventListener의 3번째 인수로 true를 전달
    - 3번째 인수를 생략하거나, false를 전달 => 타깃단계와 버블링 단계의 이벤트 캐치











