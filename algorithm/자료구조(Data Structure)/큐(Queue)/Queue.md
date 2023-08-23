## Queue란?

Queue의 사전적 의미는 무엇을 기다리는 사람, 차량 등의 줄 혹은 줄을 서서 기다리는 것을 의미하는데 
이처럼 줄을 지어 순서대로 처리되는 것이 큐라는 자료구조입니다. 큐는 데이터를 일시적으로 쌓아두기 위한 자료구조로 
스택과는 다르게 FIFO(First In First Out)의 형태를 가집니다. 
FIFO 형태는 뜻 그대로 먼저 들어온 데이터가 가장 먼저 나가는 구조를 말합니다.

![img1 daumcdn](https://github.com/MaSeongJin/TIL/assets/73871625/d4c3fb0b-d08c-40b1-9958-02a4d04d9c27)
- Enqueue : 큐 맨 뒤에 데이터 추가
- Dequeue : 큐 맨 앞쪽의 데이터 삭제

## Queue의 특징
1. 먼저 들어간 자료가 먼저 나오는 구조 FIFO(First In FIrst Out) 구조 
2. 큐는 한 쪽 끝은 프런트(front)로 정하여 삭제 연산만 수행함
3. 다른 한 쪽 끝은 리어(rear)로 정하여 삽입 연산만 수행함  
4. 그래프의 넓이 우선 탐색(BFS)에서 사용
5. 컴퓨터 버퍼에서 주로 사용, 마구 입력이 되었으나 처리를 하지 못할 때, 버퍼(큐)를 만들어 대기 시킴

## Queue 사용법
### 선언
- ``` java
  import java.util.LinkedList; //import
  import java.util.Queue; //import
  Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언, linkedlist 이용
  Queue<String> queue = new LinkedList<>(); //String형 queue 선언, linkedlist 이용
  ```
- 자바에서 큐는 LinkedList를 활용하여 생성해야 한다. 그렇기에 Queue와 LinkedList가 다 import되어 있어야 사용이 가능하다.
  Queue<Element> queue = new LinkedList<>()와 같이 선언해주면 됩니다.
### 값 추가
- `queue.add(1);` : 삽입에 성공하면 true를 반환하고, 큐에 여유공간이 없어 삽입에 실패하면 IllegalStateException을 발생시킴
- `queue.offer(3);`
### 값 삭제
- `queue.poll();` : 첫번째 값을 반환하고 제거, 비어있다면 null 반환
- `queue.remove();` : 첫번째 값 제거
- `queue.clear();` : 초기화
### 가장 먼저 들어간 값 출력
- `queue.peek();` : 첫번째로 저장된 값 리턴
