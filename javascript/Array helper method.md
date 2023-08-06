## Array helper method

- forEach
  - ``` javascript
    var fruits = ['apple','banana','peach','blue berry'];
    
    for(var i = 0 ; i < fruits.length ; i++) {
      console.log(fruits[i]);
    }
    ```
  - ``` javascript
    var fruits = ['apple','banana','peach','blue berry'];
  
    fruits.forEach(function(fruit) { // .forEach((fruit) =>
      console.log(fruit);
    });
    ```

- map
  - ``` javascript
    var fruits = ['apple','banana','peach','blue berry'];
    var juice = [];

    for(var i = 0 ; i < fruits.length ; i++){
    	juice.push(fruits[i]+' juice');
    }
    ```
  - ``` javascript
    var fruits = ['apple','banana','peach','blue berry'];

    var juice = fruits.map(function(fruit) { // .map((fruit) =>
    	return fruit + ' juice';
    });
    ```
  - ``` javascript
    var juice = fruits.map(fruit => `${fruit} juice`);
    ```
  - map 은 각각 배열 원소들에 대해서 전달받은 함수를 호출하고 그 결과를 모아서 새로운 배열을 만든다. 이때 return이 없는 함수인 경우 원래 배열의 원소 갯수만큼 undefined로 채워진 배열이 만들어진다.
  - ``` javascript
    var comments = [
      { id: 3, content: '굿 모닝' },
      { id: 6, content: '좋은 아침이네요' },
      { id: 10, content: '아침에는 시원한 물 한잔' }];
    
    var idx = comments.map( comment => {
        return comment.id;
    });
    ```
  - 위와 같이 객체로 이루어진 배열에서 특정원소의 멤버 변수로만 이루어진 배열을 만들때도 사용할 수 있다.

- filter
  - 객체 배열에서 어떤 특정 조건에 맞는 원소들로만 배열을 만들려면 아래와 같이 구현할 수 있습니다.
  - ``` javascript
    var datas = [
      { id: 3, type: 'comment', content: '굿 모닝'},
      { id: 6, type: 'post', content: '좋은 아침이네요' },
      { id: 10, type: 'comment' ,content: '아침에는 시원한 물 한잔' },
      { id: 6, type: 'post', content: '공부하기 싫어요' }];
    
    var filteredData = [];
    
    for(var i = 0 ; i < datas.length ; i++){
      if (datas[i].type === 'post'){
        filteredData.push(datas[i]);
      }
    }
    ```
  - ```javascript
    var filteredData = datas.filter( data => {
      return data.type === 'post';
    });
    ```
  - filter 함수는 각각 배열의 원소에 대해서 전달받은 함수의 결과가 true를 반환한 원소들로만 배열을 만든다.
  - 다음 예제처럼 조건이 여러개일 경우 논리 연산자를 조합해서 사용할 수 있다. data에서 like가 5개 이상인 comment만 골라낸다면 다음과 같이 구현할 수 있다.
  - ```javascript
    var datas = [
      { id: 3, type: 'comment', content: '굿 모닝', like: 1},
      { id: 6, type: 'comment', content: '좋은 아침이네요' , like: 5},
      { id: 7, type: 'comment', content: '공부하기 싫어요' , like: 30},
      { id: 10, type: 'comment' ,content: '아침에는 시원한 물 한잔' , like: 10},
      { id: 15, type: 'comment', content: '저는 공부가 좋은데요?' , like: 0},
      { id: 16, type: 'comment', content: '여기 이상한 사람이 있어요' , like: 15}];
    
    var filteredData = datas.filter( data => {
      return data.type === 'comment'&& data.like > 5;
    });
    ```
- find
  - 배열에서 특정 값을 검색을 주로 아래와 같은 방법으로 구현한다. 아래와 같은 객체배열이 있고 id가 10인 객체를 찾는다고 하면 아래와 같이 구현가능하다.
  - ``` javascript
    var datas = [
      { id: 3, type: 'comment', content: '굿 모닝', like: 1},
      { id: 6, type: 'comment', content: '좋은 아침이네요' , like: 5},
      { id: 7, type: 'comment', content: '공부하기 싫어요' , like: 30},
      { id: 10, type: 'comment' ,content: '아침에는 시원한 물 한잔' , like: 10},
      { id: 15, type: 'comment', content: '저는 공부가 좋은데요?' , like: 0},
      { id: 16, type: 'comment', content: '여기 이상한 사람이 있어요' , like: 15}];
    
    var ret;
    for(var i = 0 ; i < datas.length ; i++){
    	if( datas[i].id === 10){
        ret = datas[i];
        break;
      }
    }
    ```
  - ``` javascipt
    var ret = datas.find( data => {
    	return data.id === 10;
    });
    ```
  - filter 와 비슷해보이는데 filter와 find와 가장 큰 차이점은 find함수는 배열 원소에 대해서 주어진 함수연산을 하다가 함수가 true를 반환하면 find함수도 같이 종료된다.
   for 로 구현한 예제의 break;와 같이 동작한다고 이해할 수 있다. find함수로 조건에 만족하는 원소를 반환하지 못하는 경우 undefined 를 반환합니다.

- every
  - ``` javascript
    var scores = [
      { subject: '국어', point: '100'},
      { subject: '영어', point: '90'},
      { subject: '수학', point: '80'},
      { subject: '컴퓨터', point: '10'}];
    
    var pass = true;
    
    for(var i = 0 ; i < scores.length ; i++){
      if(scores[i].point < 70){
        pass = false;
      }
    }
    ```
  - every 는 배열의 모든 원소들이 제공된 함수로 구현된 테스트를 통과하는지 검사한다. 배열의 원소들에 대해서 함수를 실행하게 되고 함수가 false를 리턴하게 되면 every는 false를 리턴하게된다.
  - ``` javascript
    var pass = scores.every( score => {
    	return score.point > 70;
    });
    ```
- some
  - 위 every 예제에서는 모든 과목이 70점보다 높아야만 pass가 true가 반환되는 코드를 작성했다. 한 과목이라도 70보다 높을때 true를 반환하고자 할 때는 some 을 사용하면 된다.
  - ``` javascript
    var pass = scores.some( score => {
    	return score.point > 70;
    });
    ```
- reduce
  - ``` javascript
    var scores = [1,2,3,4,5];
    var sum = 0;
    for(var i = 0 ; i < scores.length ; i++){
      sum += scores[i];
    }
    ```
  - ``` javascript
    var sum = scores.reduce((sum, number) => sum + number,0);
    ```
  - reduce 는 배열의 각 원소에 대해서 첫번째 원소부터 마지막 원소 순으로 연산한 값이 줄도록 함수를 적용한다.
    이를테면 위 예제에서 (((( 1 + 2 ) + 3 ) + 4 ) + 5) 순으로 연산을 하게 됩니다. 0은 initial value로서 누산값의 초기값을 의미합니다.
