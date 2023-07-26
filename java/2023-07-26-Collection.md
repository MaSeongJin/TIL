## Collection Framework

- 객체들을 한곳에 모아 놓고 편리하게 사용할 수 있는 환경을 제공

- 정적 자료구조(Static data structure)
  
  - 고정된 크기의 자료구조
  
  - 배열이 대표적인 정적 자료구조
  
  - 선언 시 크기를 명시하면 바꿀 수 없음

- 동적 자료구조(Dynamic data structure)
  
  - 요소의 개수에 따라 자료구조의 크기가 동적으로 증가하거나 감소
  
  - 리스트, 스택, 큐 등

- 자료구조들의 종류는 어떤 구조에서 얼마나 빨리 원하는 데이터를 찾는가에 있다.

- List(Collection) : 순서가 있는 데이터의 집합, 데이터의 중복 가능
  
  - ArrayList
  
  - LinkedList
  
  - Vector
  
  - Stack

- Set(Collection) : 순서를 유지하지 않는 데이터의 집합, 데이터 중복 불가
  
  - HashSet
  
  - TreeSet

- Queue(Collection) : 선입 선출
  
  - LinkedList

- Map : key와 value의 쌍으로 데이터를 관리하는 집합, key 중복 불가 value는 가능
  
  - HashMap
  
  - TreeMap
  
  - Hashtable
  
  - Properties

- List, Set, Map, Queue 모두 인터페이스

- Collection interface

| 분류  | Collection                                                                                |
|:---:|:-----------------------------------------------------------------------------------------:|
| 추가  | add(E e), addAll(Collection<? extends E> c)                                               |
| 조회  | contains(Object o), containsAll(Collection<?> c), equals(), isEmpty(), iterator(), size() |
| 삭제  | clear(), removeAll(Collection<?> c), retainAll(Collection<?> c)                           |
| 수정  |                                                                                           |
| 기타  | toArray()                                                                                 |

## List

- 특징 : 순서가 있고, 중복을 허용(배열과 유사)

- 구현 클래스
  
  - ArrayList
  
  - LinkedList
  
  - Vector

- 내부적으로 배열을 이용하여 데이터를 관리

- 배열과 다르게 크기가 유동적으로 변함(동적 자료구조)

- 배열을 다루는 것과 유사하게 사용가능

- 주요 메소드

| 분류  | List                                                                     |
|:---:|:------------------------------------------------------------------------:|
| 추가  | add(int index, E element), addAll(int index, Collection<? extends E> c)  |
| 조회  | get(int index), indexOf(Object o), lastIndexOf(Object o), listIterator() |
| 삭제  | remove(int index)                                                        |
| 수정  | set(int index, E element)                                                |
| 기타  | subList(int fromIndex, int toIndex)                                      |

- Array
  
  - 같은 타입의 데이터를 묶어 사용하는 자료구조
  
  - 접근 속도가 빠름
  
  - 크기를 변경할 수 없어 추가 데이터를 넣을 때, 새로운 배열을 만들고 복사함
  
  - 데이터 삭제 시, 인덱스를 재조정하기 위해 전체 데이터를 옮겨야 함
  
  - ArrayList 역시 Array를 활용하므로 이 같은 특징을 가짐

- ArrayList
  
  - add(E e) : 데이터 입력
  
  - get(int index) : 데이터 추출
  
  - size() : 입력된 데이터의 크기 반환
  
  - remove(int i) : 특정한 데이터 삭제
  
  - remove(Object o) : 특정한 데이터 삭제
  
  - clear() : 모든 데이터 삭제
  
  - contains(Object o) : 특정 객체가 포함되어 있는지 체크
  
  - isEmpty() : 비어있는지 체크(true, false)
  
  - addAll(Collection c) : 기존 등록된 컬렉션 데이터 입력
  
  - iterator() : iterator 인터페이스 객체 반환

- LinkedList
  
  - 각 요소를 Node로 정의하고 Node는 다음 요소의 참조 값과 데이터로 구성됨
  
  - 각 요소가 다음 요소의 링크 정보를 가지며 연속적으로 구성될 필요가 없음



## Set

- 특징 : 순서가 없고, 중복을 허용하지 않음

- 장점 : 빠른 속도, 효율적인 중복 데이터 제거 수단

- 단점 : 단순 집합의 개념으로 정렬하려면 별도의 처리가 필요하다.

- 구현 클래스
  
  - HaxhSet
  
  - TreeSet

- 메소드
  
  - add(E e) : 데이터 입력
  
  - size() : 입력된 데이터의 크기 반환
  
  - remove(Object o) : 특정한 데이터 삭제
  
  - clear() : 모든 데이터 삭제
  
  - contains(Object o) : 특정 객체가 포함되어 있는지 체크
  
  - isEmpty() : 비어있는지 체크(true, false)
  
  - iterator() : iterator 인터페이스 객체 반환
  
  - toArray() : Set의 내용을 Object 형의 배열로 전환



## Map

- 특징 : Key(키)와 value(값)를 하나의 Entry로 묶어서 데이터 관리, 순서는 없으며, 키에 대한 중복은 없음

- 장점 : 빠른 속도

- 구현 클래스
  
  - HashMap
  
  - TreeMap

- 메소드
  
  - V put(K key, V value) : 데이터 입력
  
  - V get(Object key) : 데이터 추출
  
  - V remove(K key) : 키의 값을 지우고 반환, 없다면 null을 반환
  
  - boolean containsKey(Object key) : 특정한 key 포함 여부
  
  - void putAll(Map<K key, V value> m) : 기존 컬레션 데이터 추가
  
  - Set<Map.Entry<K, V>> entrySet() : (key와 value) 쌍을 표현하는 Map.Entry 집합을 반환



## Queue

- Queue는 인터페이스, 구현체는 LinkedList를 사용

- 큐 자료구조 : FIFO,(first-in-first-out) 가장 먼저 들어온 값이 가장 먼저 빠져나감

- 메소드
  
  - boolean offer(E e) : 데이터를 추가
  
  - E peek() : 가장 앞에 있는 데이터 조회
  
  - E poll() : 가장 앞에 있는 데이터 빼내기
  
  - boolean isEmpty() : 큐가 비어있는지 여부



## Stack

- Stack 클래스를 사용

- 스택 자료구조 : LIFO, (last-in-first-out) 가장 나중에 들어온 값이 가장 먼저 빠져나감

- 메소드
  
  - E push(E e) : 데이터를 추가
  
  - E peek() : 가장 위에 있는 데이터 조회
  
  - E pop() : 가장 위에 있는 데이터 빼내기
  
  - boolean isEmpty() : 스택이 비어 있는지 여부



## 정렬

- 요소들을 특정 기준에 맞춰 내림차순 또는 오름차순으로 배치하는 것

- 순서를 가지는 Collection들만 정렬 가능

- Collections의 sort()를 이용한 정렬

- Comparable interface

- Comparator 활용
  
  - 객체가 Comparable을 구현하고 있지 않거나 사용자 정의 알고리즘으로 정렬하려는 경우
  
  - sort(List<T> list, Comparator<? Super T> c)
  
  - 1회성 객체 사용 시 anonymous inner class 사용
  
  - 클래스 정의, 객체 생성을 한번에 처리
  
  - 람다 표현식 사용
  
  - 
