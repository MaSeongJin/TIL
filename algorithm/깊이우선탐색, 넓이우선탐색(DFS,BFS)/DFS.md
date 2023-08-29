## 깊이 우선 탐색(DFS-Depth First Search)
최초 시작 정점에서 가장 먼저 이어져 있는(간선으로 연결된) 정점을 하나 찾고 해당 정점에 또 인접한 정점을 찾아 더 이상 깊이 갈 수 없을 때까지
탐색한 뒤 돌아오는 방식이다.

트리와의 큰 차이점은 그래프는 순환할 수 있다는 것이다. 그래서, 순환 탐지를 할 수 있도록 추가적인 기능을 구현해야 한다.

BFS와의 큰 차이점은 DFS는 탐색을 한 뒤 이전의 정점으로 돌아온다는 것이다. 이것을 백트래킹이라 한다.

<p align="center"><img src="https://i.postimg.cc/0NnLPjNG/img.gif"></p>

위의 애니메이션을 보면 0번에서 1, 2로 이동한 뒤 2에서 더 이상 깊이 갈 수 없어 2를 탐색 완료 후, 1로 돌아온다.
다시 1에서 3, 4로 더 깊이 들어갔다가 나오는 방식을 반복한다. 말 그대로, 깊이 갈 수 있을 때까지 탐색하고 더 이상 탐색이 불가하면 되돌아오는 방식이다.

### DFS를 사용하는 예시
그래프의 순환이 있는지 확인하는 경우 많이 쓰인다. BFS로도 가능하지만 DFS가 더 메모리 효율적이다. 기타 경로 찾기, 위상 정렬, 미로 찾기 등에서 사용될 수 있다.

하지만 최단 경로를 찾을 때는 BFS를 기반으로 탐색해야 한다. BFS는 최단 경로를 즉각적으로 보장해주면서 탐색을 할 수 있지만 DFS는 그렇지 못한 경우가 있다.

<p align="center"><img src="https://i.postimg.cc/Wz5ycHb2/image.png"></p>

위의 예시를 보면 만약 우리가 주황색으로 칠해진 곳에서 빨간색으로 칠해진 곳으로 가고자 할 때, 
유일한 경로는 파란 화살표와 빨간 화살표가 있을 수 있다.
  
그럼 BFS로 탐색을 하면 빨간 부분, 파란 부분 인접 정점을 동시에 차례대로 탐색을 하게 되며 시작점이 고정이라서 무조건 모든
인접 정접이 최소의 경우로만 이동하도록 보장된다.

그래서 BFS로 탐색 시, 원하는 위치에 도달했다면 추가 탐색을 그만두어 빠르게 문제를 해결할 수 있게 된다.

그런데 DFS로 탐색을 하면, 상단의 빨간색 부분부터 우선 탐색이 이뤄질 수 있다. 하지만 이 경로는 최소 경로라는 보장이 불가능하다.

보장을 위해서는 이미 방문한 경로의 정점을 미 방문 상태로 전환하고 다른 모든 동일 위치에 도달할 수 있는 경우를 체크해야 하는데,
그것은 DFS가 아니라 Brute Force 방법이 된다.

(DFS,BFS는 기본적으로 이미 방문한 정점을 다시 방문하지 않아야 한다)

### 구현 방법
스택 또는 재귀 방식을 이용하여 구현할 수 있다. 스택은 LIFO방식이기 때문에 인접한 정점이 우선이 아닌 더 멀리 있는(인접 정점의 끝) 정점을
우선 탐색한 뒤 돌아올 수 있다.

재귀 방식으로 진행도 가능하다. 재귀 자체가 내부적으로 Stack을 사용하기 때문이다. 둘 중에서는 재귀방식이 더 많이 쓰인다.
  
시간복잡도 : O(V+E), V는 정점 / E는 간선의 개수로 그 개수에 따라 전체 탐색에 시간이 소요된다.(인접 행렬은 O(V^2))

공간복잡도 : O(V), 최악의 경우, 정점이 1열로 이어져 전체 정점의 수 만큼 스택이 쌓일 수 있다.

### 코드
```java
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    public static void main(String[] args){
        Graph g = new Graph(6);
        g.addEdge(0, 1); // 0 -> 1 연결
        g.addEdge(0, 5); // 0 -> 5 연결
        g.addEdge(1, 2); // 1 -> 2 연결
        g.addEdge(1, 3); // 1 -> 3 연결
        g.addEdge(1, 4); // 1 -> 4 연결
        g.addEdge(2, 0); // 2 -> 0 연결
        g.addEdge(3, 4); // 3 -> 4 연결
        g.addEdge(4, 2); // 4 -> 2 연결

        g.dfs();
    }

    private int v; // 정점의 개수
    private LinkedList<Integer> adj[]; // 인접 리스트
    public Graph(int v){
        this.v = v;
        this.adj = new LinkedList[v];
        for(int i=0; i < v; i++){
            adj[i] = new LinkedList<>();
        }
    }

    // Source 정점에서 Dest 정점을 이어주는 메소드
    public void addEdge(int s, int d){
        this.adj[s].add(d);
    }

    // DFS를 수행하는 메소드
    public void dfs(){
        // 전체 정점이 최초에는 visited를 false 설정
        boolean visited[] = new boolean[this.v];

        // 반복문 기반으로 Disconnected Graph라도 전체 탐색이 가능
        // 재귀 기반 DFS 수행 시작
        for(int i=0; i < this.v; i++){
            if(!visited[i]){
                dfs_recurvise(i, visited);
            }
        }

        System.out.println();

        // 스택 기반 수행을 위해 다시 false로 초기화
        visited = new boolean[this.v];

        // 스택 기반 DFS 수행 시작
        for(int i=0; i < this.v; i++){
            if(!visited[i]){
                dfs_stack(i, visited);
            }
        }
    }

    // DFS 재귀 수행 메소드
    public void dfs_recurvise(int v, boolean visited[]){
        visited[v] = true;
        System.out.print(v + " ");

        // 방문하지 않은 인접 정점을 모두 찾아 우선 탐색 수행
        Iterator<Integer> i = this.adj[v].listIterator();
        while(i.hasNext()){
            int n = i.next();
            if(!visited[n]){
                dfs_recurvise(n, visited);
            }
        }
    }

    // DFS 스택 수행 메소드
    public void dfs_stack(int start, boolean visited[]){
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty()){
            int v = stack.pop();
            System.out.print(v + " ");

            // 방문하지 않은 인접 정점을 모두 찾아 우선 탐색 수행
            Iterator<Integer> i = this.adj[v].listIterator();
            while(i.hasNext()){
                int n = i.next();
                if(!visited[n]){
                    visited[n] = true;
                    stack.push(n);
                }
            }
        }
    }
}

/*결과
0 1 2 3 4 5 
0 5 1 4 3 2 
Process finished with exit code 0

결과가 아래가 다른 이유는 print 시점 때문
재귀는 탐색 시에 print하고 스택 시에는 stack에서 뺄 때 print함
중요한 것은 모든 것이 탐색 되느냐로 본다.
*/
```

### 방향 그래프 순환 탐지
DFS에서는 Back Edge가 있으면 순환이 있다고 본다. Back Edge는 자기 자신을 가리키거나(self-loop) 자신의 이전 정점(조상 정점)을 가리키는 경우의 Edge(간선)을 의미한다.

<p align="center"><img src="https://i.postimg.cc/8zz0Cp3v/img1-daumcdn.png"></p>

2번 노드에서 시작한다고 가정하면 여기서 Back Edge는 1 -> 2를 가리키는 것과, 3이 자신을 가리키는 것, 그리고 0이 2를 가리키는 것의 3개가 Back Edge가 된다.

따라서 이 그래프는 순환한다고 볼 수 있다.

#### 구현방법
- 재귀 DFS를 수행할 때, 각 정점의 index와 방문 여부 배열(visited), 재귀 stack을 전달한다.
- 현재 정점을 visited = true로 하고 현재 정점을 재귀 stack에 넣는다.
- 인접한 모든 미 방문 상태의 정점을 찾고 재귀적으로 함수를 수행한다.(재귀 함수가 true 반환 시 return)
- 만약 현재 방문 정점이 재귀 stack에 있다면 순환 발생. true를 반환
- 이 전체를 구현하기 위한 wrapper 클래스를 구현하여 순환 발생 시 true를 리턴한다.

#### 방향 그래프의 Cycle을 찾는 알고리즘을 DFS로 구현
```java
import java.util.LinkedList;

public class CycleDetection {
    public static void main(String[] args){
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        if(g.isCycle()){
            System.out.println("Cycle Detected!");
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        int v; // 정점의 개수
        LinkedList<Integer>[] adj; // 인접 리스트 방식으로 구현

        // 그래프 생성하는 생성자
        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[v];
            for(int i=0; i < this.v; i++){
                adj[i] = new LinkedList<>();
            }
        }

        // 간선 추가
        public void addEdge(int s, int d){
            this.adj[s].add(d); // S -> D 로 방향 그래프 생성
        }

        // 재귀를 직접 수행하지는 않는 Wrapper 클래스
        public boolean isCycle(){
            // 현재 방문 완료된 내역
            boolean[] visited = new boolean[this.v];

            // 현재 recStack에 있다는 것은 방문 진행 중이라는 의미임!(방문 완료 아님)
            // 즉, beingVisited와 동일한 의미
            boolean[] recStack = new boolean[this.v];

            // Disconnected를 대비하여 모든 정점에서 시작
            for(int i=0; i < this.v; i++){
                if(isCycleUtil(i, visited, recStack)){
                    return true;
                }
            }
            return false;
        }


        // 직접 재귀를 수행하여 Cycle을 찾는 클래스
        public boolean isCycleUtil(int i, boolean[] visited, boolean[] recStack){
            // 이미 현재 Stack에 있다면 방문 중이라는 의미! Cycle 있음
            if(recStack[i]){
                return true;
            }

            // 방문 완료된 경우에는 되돌아감
            if(visited[i]){
                return false;
            }

            visited[i] = true;
            recStack[i] = true;

            for(int v : this.adj[i]){
                // 순환이 발생했다면 true 반환
                if(isCycleUtil(i, visited, recStack)){
                    return true;
                }
            }
            // 방문이 끝났으면 방문 중인 상태에서는 제외함
            recStack[i] = false;
            return false;
        }

    }
}
// 결과
// Cycle Detected!
```

### 무방향 그래프 순환 탐지
무방향 그래프는 상호 연결되어 있기 때문에 방향 그래프에서 순환 탐지 하듯이 탐지를 시도하면 무조건 순환이 있는 것으로 탐지된다.
#### 구현 방법
- 그래프를 만들고 재귀 형태로 탐색하는 함수를 생성하여 현재 정점, 방문 여부 배열, 재귀, parent 배열을 전달한다.
  - parent는 현재 노드와 이어진 이전에 탐색된 노드가 저장된 배열
- 현재 탐색 정점을 방문 완료로 표시하고 parent를 이전 정점으로 저장
- 현재 정점에 이어진 인접 정점을 모두 차례로 탐색 수행한다.
- 인접 정점이 아직 미방문 상태라면 재귀 호출을 하여 그 결과를 받아 반환
- 인접 정점이 기 방문 상태이며 parent가 현재 정점 값이 아니라면 순환 있음으로 결과값 반환
  - 인접 정점의 parent가 현재 정점인 경우, 현재를 A, 인접을 B정점이라고 할 때, A방문 후, B에서 다시 A를 방문했다는 의미이므로, 무방향 그래프에서 상호 인접 정점끼리는 무조건 재 탐색 수행을 하고자 하기 때문에 이를 제외 해야 한다.
  - 인접 정점의 parent가 현재 정점이 아니라면 A -> B -> A의 순이 아니라 A -> B -> ? -> ? -> A 와 같이 중간에 다른 정점들을 추가로 탐색했다는 의미이며, 이는 당연히 Cycle이 있는 것이다.
<p align="center"><img src="https://i.postimg.cc/zXC8RYN7/img1-daumcdn.png"></p>

#### 무방향 그래프의 순환을 DFS로 탐색
```java
import java.util.LinkedList;

public class CycleDetection {
    public static void main(String[] args){
        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 4);

        if(g.isCycle()){
            System.out.println("Cycle Detected! - The List are below!");
            for(int i=0; i < g.v; i++){
                if(g.cycle[i]) System.out.print(i + ", ");
            }
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        int v; // 정점의 개수
        LinkedList<Integer>[] adj; // 인접 리스트 방식으로 구현
        boolean[] cycle; // 순환에 해당하는 정점 리스트를 저장(순환 시 true 값으로)

        // 그래프 생성하는 생성자
        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[v];
            cycle = new boolean[v];
            for(int i=0; i < this.v; i++){
                adj[i] = new LinkedList<>();
            }
        }

        // 간선 추가 - 무방향 그래프 이므로 양쪽으로 연결
        public void addEdge(int s, int d){
            this.adj[s].add(d); // S -> D 로 연결
            this.adj[d].add(s); // D -> S 로 연결
        }

        // 재귀를 직접 수행하지는 않는 Wrapper 클래스
        public boolean isCycle(){
            // 현재 방문 완료된 내역
            boolean[] visited = new boolean[this.v];

            // Disconnected를 대비하여 모든 정점에서 시작
            for(int i=0; i < this.v; i++){
            
                
                // 이미 방문한 정점은 다시 탐색할 필요 없다. 이미 탐색으로 Cycle여부를 확인하였기 때문
                // 아래 주석처리된 코드는 단순 Cycle 체크만 한다면 사용해도 됨
                // if(visited[i]) continue;
            
                // 최초 방문 정점은 parent를 -1로 지정한다.
                // start는 어떤 정점에서 부터 시작하느냐 이므로 i값을 넣는다.
                if(isCycleUtil(i, visited, -1, i)){
                    return true;
                }
                
                // 새로운 정점에서 시작할 때는, 방문 여부를 초기화 해야 한다.
                // 단순 Cycle 체크만 한다면 위의 주석 코드를 해제하고 아래를 주석 처리
                visited = new boolean[this.v];
            }
            return false;
        }


        // 직접 재귀를 수행하여 Cycle을 찾는 클래스
        public boolean isCycleUtil(int i, boolean[] visited, int parent, int start){

            visited[i] = true;
            for(int v : this.adj[i]){
                // 아직 방문되지 않은 정점이면 추가 탐색 수행
                if(!visited[v]){
                    if(isCycleUtil(v, visited, i, start)){
                        // true가 반환되었다면 인접 정점 부터 탐색한 것들이 모두 순환 정점이라는 것
                        cycle[v] = true;
                        return true;
                    }
                
                // 만약 다음 인접 정점이 현재 정점의 parent가 아니라면
                // 중간에 다른 정점을 방문한 뒤 도착한 것이라서 Cycle이 있는 것임
                
                // 그런데 v == start 라는 것은 최초 시작한 지점으로 돌아왔다는 의미인데,
                // 이것이 없어도 Cycle Detection 자체는 가능하지만, 없으면 어떤 부분이 Cycle인지
                // 확인 시에 오류가 발생할 수 있다.
                // start 지점으로 돌아왔다면 현재 탐색된 내역 모두가 Cycle을 이루는 정점이라고
                // 체크할 수 있지만, 그게 아니라 중간의 부분만 Cycle 이었다면 Cycle이 아닌 정점까지
                // Cycle의 일부로 체크하게 되는 오류가 발생할 수 있기에 이와 같이 처리
                } else if(v != parent && v == start){

                    // 현재 탐색할 정점을 순환 정점으로 표시
                    cycle[v] = true;
                    return true;
                }
            }
            return false;
        }

    }
}

// 결과
//Cycle Detected! - The List are below!
//0, 1, 2, 
```
위의 내용 중 주석을 보면 v == start 라는 코드가 필요한 이유에 대해 설명한 부분이 있다. 실제로, 이 내용이 없어도 Cycle 체크 자체는 가능할 것이다.
  
단순히 현재 그래프 상에 Cycle이 있는지 없는지만 체크하고자 하는 경우, isCycle 메소드에서 주석처리된 부분을 읽어서 코드를 약간 수정한 뒤, v == start 부분을 제외하면 된다.(애초에 start 패러미터를 전달하지 않아도 된다.)
  
하지만 그렇게 하게 되면, Cycle이 중간에 있고 가지로 연결된 정점에서 탐색을 시작했을 때, Cycle에 속하지 않은 가지 부분의 정점마저 Cycle을 이루는 정점이라고 반환할 수 있는 오류가 있다.
