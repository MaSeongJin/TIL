### 연결 리스트
- 특성
  - 자료의 논리적인 순서와 메모리 상의 물리적인 순서가 일치하지 않고, 개별적으로 위치하고 있는 원소의 주소를 연결하여 하나의 전체적인 자료구조를 이룬다.
  - 링크를 통해 원소에 접근하므로, 순차 리스트에서처럼 물리적인 순서를 맞추기 위한 작업이 필요하지 않다.
  - 자료구조의 크기를 동적으로 조정할 수 있어, 메모리의 효율적인 사용이 가능하다.
- 노드
  - 연결 리스트에서 하나의 원소에 필요한 데이터를 갖고 있는 자료단위
  - 구성요소
    - 데이터 필드
      - 원소의 값을 저장하는 자료구조
      - 저장할 원소의 종류나 크기에 따라 구조를 정의하여 사용함
    - 링크 필드
      - 다음 노드의 주소를 저장하는 자료구조
- 헤드
  - 리스트의 처음 노드를 가리키는 레퍼런스
### 단순 연결 리스트(Singly Linked List)
- 연결 구조
  - 노드가 하나의 링크 필드에 의해 다음 노드와 연결되는 구조를 가진다.
  - 헤드가 가장 앞의 노드를 가리키고, 링크 필드가 연속적으로 다음 노드를 가리킨다.
  - 최종적으로 NULL을 가리키는 노드가 리스트의 가장 마지막 노드이다.
- 삽입 과정(A, C, D를 원소로 갖고 있는 리스트의 두 번쨰에 B 노드를 삽입할 때)
  1. 메모리를 할당하여 새로운 노드 new 생성
  2. 새로운 노드 new의 데이터 필드에 B 저장
  3. 삽입될 위치의 바로 앞에 위치한 노드의 링크 필드를 new에 복사
  4. new의 주소를 앞 노드의 링크 필드에 저장
- 삭제 과정(A, B, C, D 리스트의 B 노드를 삭제할 때)
  1. 삭제할 노드의 앞 노드(선행노드) 탐색
  2. 삭제할 노드의 링크 필드를 선행노드의 링크 필드에 복사
- 구현
- ```java 
  // 데이터 필드, 다음 노드를 사리키는 링크필드 한개가 존재한다.
  public class Node {
  	public String data; // 예시로 문자열을 넣는다. 제네릭을 이용하면 좀 더 포괄식으로
  	public Node link; // 주소를 저장할 것이라 Node라고 하는 자료형을 사용
  
  	// 생성자를 선언하는 순간 기본생성자가 만들어지지 않으므로 습관처럼 생성하자
  	public Node() {
  	}
  
  	public Node(String data) {
  		this.data = data;
  		this.link = null; // 필요없는 문장
  	}
  
  	@Override
  	public String toString() {
  		return "Node [data=" + data + "]";
  	}
  }
    
  public class SinglyLinkedList {
  	// 필드로 가지고 있으면 좋은 것들
  	private Node head; // 노드의 시작점
  	private int size; // 연결리스트의 노드의 개수 : 필수는 아니지만 있으면 편함
  
  	public SinglyLinkedList() {
  	}
  
  	// 첫번째 위치에 원소 삽입
  	public void addFirst(String data) {
  		// 노드 생성
  		Node node = new Node(data); // 생성자를 만들어 놓았으니 인스턴스 생성 가능
  		node.link = head;
  		head = node;
  		size++;
  	}
  
  	// 조회
  	public Node get(int idx) {
  		// 인덱스는 0부터
  		// 0보다 작은 값이 들어오면 첫번째 노드 반환 가능
  		// size 이상이 들어오면 마지막 노드 반환 가능
  		if (idx < 0 || idx >= size) {
  			return null;
  		}
  		Node curr = head;
  		for (int i = 0; i < idx; i++) {
  			curr = curr.link;
  		}
  		return curr;
  	}
  
  	// 마지막 위치에 원소 삽입
  	public void addLast(String data) {
  		// head가 null 이라는 것은 size가 0이고 공백리스트라는 뜻
  		if (size == 0) {
  			addFirst(data);
  			return;
  		}
  		Node node = new Node(data);
  		// 마지막 노드 찾기
  		Node curr = head;
  		while (curr.link != null) {
  			curr = curr.link;
  		}
  		curr.link = node;
  		size++;
  	}
  
  	// 연결리스트의 내용물을 출력하는 메소드
  	public void printList() {
  		Node curr = head;
  
  		if (head == null) {
  			System.out.println("비어있음");
  			return;
  		}
  
  		// size라는 필드를 사용하게 된다면 for문 가능
  		// size 필드를 사용하지 않으면 몇번 돌릴지 모른다.
  		while (curr != null) {
  			System.out.print(curr.data + " -> ");
  			curr = curr.link;
  		}
  		System.out.println();
  	}
  
  	// 중간 원소 삽입
  	public void add(int idx, String data) {
  		if (idx < 0 || idx > size)
  			return;
  		if (idx == 0) {
  			addFirst(data);
  			return;
  		}
  		if (idx == size) {
  			addLast(data);
  			return;
  		}
  		Node pre = get(idx - 1); // 조회 함수를 통해 이전 노드를 찾는다.
  		Node node = new Node(data);
  		node.link = pre.link;
  		pre.link = node;
  		size++;
  	}
  
  	// 첫번째 원소 삭제
  	// 데이터 반환
  	public String remove() {
  		if (head == null)
  			return null;
  		String data = head.data;
  		head = head.link;
  		size--;
  		return data;
  	}
  
  	// 중간 원소 삭제
  	public String remove(int idx) {
  		if (idx == 0) {
  			return remove();
  		}
  		// 범위를 벗어났다면
  		if (idx < 0 || idx >= size) {
  			return null;
  		}
  		Node pre = get(idx - 1);
  		Node rmNode = pre.link;
  		String data = rmNode.data;
  
  		pre.link = rmNode.link;
  		size--;
  		return data;
  	}
  }
  ```
### 이중 연결 리스트(Doubly Linked List)
- 특성
  - 양쪽 방향으로 순회할 수 있도록 노드를 연결한 리스트
  - 두 개의 링크 필드와 한 개의 데이터 필드로 구성
- 삽입 과정(cur가 가리키는 노드 다음으로 D값을 가진 노드를 삽입)
  1. 메모리를 할당하여 새로운 노드 new를 생성하고 데이터 필드에 D를 저장
  2. cur의 next를 new의 next에 저장하여 cur의 오른쪽 노드를 삽입할 노드 new의 오른쪽 노드로 연결한다.
  3. new의 주소를 cur의 next에 저장하여 노드 new를 cur의 오른쪽 노드로 연결한다.
  4. cur에 있는 링크 값을 new의 prev에 저장하여 cur를 new의 왼쪽노드로 연결한다.
  5. new의 주소를 new의 오른쪽 노드의 prev에 저장하여 노드 new의 오른쪽노드의 왼쪽노드로 new를 연결한다.
- 삭제 과정
  1. 삭제할 노드 cur의 오른쪽노드의 주소를 cur의 왼쪽노드의 next에 저장하여 cur의 오르쪽노드를 cur의 왼쪽노드의 오른쪽노드로 연결한다.
  2. 삭제할 노드 cur의 왼쪽노드의 주소를 cur의 오른쪽노드의 prev에 저장하여 cur의 왼쪽노드를 cur의 오른쪽노드의 왼쪽노드로 연결한다.
  3. cur가 가리키는 노드에 할당된 메모리를 반환한다.
