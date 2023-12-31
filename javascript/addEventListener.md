**HTML**
``` html
<div id="cols">
  <button class="btn">A</button>
  <button class="btn">B</button>
  <button class="btn">C</button>
</div>
```
**JS**
``` javascript
var cols = document.querySelectorAll("#cols .btn");

for (var i = 0; i < cols.length; i++) {
  cols[i].addEventListener("click", click);
}
cols[1].style.color = "red";

function click(e) {
  window.alert(this.innerHTML);
}
```
querySelectorAll 함수를 이용해 div안의 id 값 'cols'와 button의 class값 'btn '값을 가져온 후, 
반복문을 사용하여 해당하는 모든 값에 addEventListener를 지정해 클릭시 click함수를 실행하는 스크립트. 
또한 1번 배열의 색을 "red"로 지정함으로 .btn 1번 값인 B가 붉은색으로 변화함.
위와 같이 작성하면 버튼 추가시에도 똑같은 코드를 재작성 할 필요 없이 자동으로 이벤트가 등록됩니다.

### 여러 엘리먼트에 한번에 이벤트리스너 등록하기
1. querySelectorAll 을 통해 이벤트를 등록할 모든 엘리먼트를 선택한다
`let eventList = document.querySelectorAll('.event');`
2. 선택한 엘리먼트에 forEach를 이용해 이벤트리스너 등록
``` javascript
eventList.forEach(function (event) {
	event.addEventListener('change', function () {
		//동작할 내용 
	});
});
```

### 그 외 기능들
- click – 마우스버튼을 클릭하고 버튼에서 손가락을 떼면 발생한다.
- mouseover – 마우스를 HTML요소 위에 올리면 발생한다.
- mouseout – 마우스가 HTML요소 밖으로 벗어날 때 발생한다.
- mousedown – 클릭을 하기 위해 마우스버튼을 누르고 아직 떼기 전인 그 순간, HTML요소를 드래그할 때 사용할 수 있다.
- mouseup – 마우스버튼을 떼는 그 순간, 드래그한 HTML요소를 어딘가에 놓을 때 사용할 수 있다.
- mousemove – 마우스가 움직일때마다 발생한다. 마우스커서의 현재 위치를 계속 기록하는 것에 사용할 수 있다.
- focus – HTML요소에 포커스가 갔을때 발생한다.
- blur – HTML요소가 포커스에서 벗어났을때 발생한다.
- keypress – 키를 누르는 순간에 발생하고 키를 누르고 있는 동안 계속해서 발생한다.
- keydown – 키를 누를 때 발생한다.
- keyup – 키를 눌렀다가 떼는 순간에 발생한다.
- load – 웹페이지에서 사용할 모든 파일의 다운로드가 완료되었을때 발생한다.
- resize – 브라우저 창의 크기를 조절할때 발생한다.
- scroll – 스크롤바를 드래그하거나 키보드(up, down)를 사용하거나 마우스 휠을 사용해서 웹페이지를 스크롤할 때 발생한다. 페이지에 스크롤바가 없다면 이벤트는 발생하지 않다.
- unload – 링크를 클릭해서 다른 페이지로 이동하거나 브라우저 탭을 닫을 때 혹은 브라우저 창을 닫을 때 이벤트가 발생한다.
- change – 폼 필드의 상태가 변경되었을 때 발생한다. 라디오 버튼을 클릭하거나 셀렉트 박스에서 값을 선택하는 경우를 예로 들수 있다.
