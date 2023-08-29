## 너비 우선 탐색(BFS, Breadth First Search)
넓이 우선 탐색과 가장 쉽게 비교할 수 있는 것은 트리(Tree) 구조의 계층 순회(Level Order) 이다. 트리 구조에서 계층 순회를 시작하면 각 계층의 모든 노드를 탐색한 뒤 그 아래 계층으로 넘어간다.

그래프도 동일한 방식으로 동작한다고 보면 되는데, 최초 시작 정점에서 가장 먼저 이어져 있는(간선으로 연결된) 정점을 모두 순회한 뒤, 각 순회된 정점부터 또 시작하여 가장 먼저 이어진 정점을 순회하는 방식을 반복한다.
  
트리와의 큰 차이점은, 그래프는 순환(Cycle)할 수 있다는 것이다. 그래서, 순환 탐지(Cycle Detection)를 할 수 있도록 추가적인 기능을 구현해야 한다는 것이다.
### BFS 이해
<p align="center"><img src="https://i.postimg.cc/Y9Y3HYgm/img.gif"></p>
  
위의 Gif를 보면 A 정점에서 시작하여 B, C를 우선 탐색하고, 차례대로 B, C가 인접하고 있는 정점을 순환하고 있다. 위의 그림을 통해 순환 되는 순서는 A - B - C - D - E - F - G - H 이다.

### BFS 사용 예시
넓이 우선 탐색은 퍼즐 게임 등의 해결 시에 굉장히 많이 쓰일 수 있다. 루빅 큐브 등의 움직임을 해결할 수 있는 방식으로 활용 될 수 있다. 또한 굉장히 유명한 다익스트라(Dijkstra) 알고리즘으로 최단 경로를 찾을 때도 활용되며 Flow Network의 Maximum Flow를 찾기 위한 Ford-Fulkerson 알고리즘에도 사용된다.

#### 최단 경로 문제
최단 경로 문제는 특히, DFS가 아닌 BFS로 풀어야 문제의 결과를 정확하게 나타낼 수 있다. 왜냐하면, BFS는 모든 정점의 방문 결과를 최단 경로로 보장이 가능하지만 DFS는 보장할 수 없기 때문이다.

하지만 모든 경우에 최단 경로 문제를 BFS로 풀 수 있는 것은 아니다. BFS로 최단 경로 문제를 해결 가능한 것은 가중치가 1일 때 만이다. 1이 아니라면 다익스트라 또는 벨만 포드 알고리즘과 같이 응용하여 사용해야 한다.

추가로, 그 가중치가 구하려는 문제의 답에 해당하는 것이어야 한다.(최단 거리면 거리가 가중치, 최단 시간이면 시간이 가중치여야 함!) 또한, 정점과 간선의 수가 너무 많으면 안된다.(O(V+E) 이기 때문에 보통 E가 V보다 많은데 E가 너무 많으면 시간 내에 해결이 불가함)
<p align="center"><img src="https://i.postimg.cc/rFMKgxwY/img1-daumcdn.png"></p>

만약 우리가 주황색으로 칠해진 곳에서 빨간색으로 칠해진 곳으로 가고자 한다고 할 때, 유일한 경로는 파란 화살표와 빨간 화살표가 있을 수 있다.(다른 것도 가능하지만 대표적으로)

그럼 BFS로 탐색을 하면 빨간 부분, 파란 부분 인접 정점을 동시에 차례 대로 탐색을 하게 되며 시작점이 고정이라서 무조건 모든 인접 정점이 최소의 경우로만 이동하도록 보장이 된다.

그래서 BFS로 탐색 시, 원하는 위치에 도달했다면 추가 탐색을 그만두어 빠르게 문제를 해결할 수 있게 된다.

그런데 DFS로 탐색을 하면, 상단의 빨간색 부분 부터 우선 탐색이 이루어질 수 있다. 하지만 이 경로는 최소 경로라는 보장이 불가능하다! 실제 위의 예시에서는 최소 경로가 아니다.

보장을 하기 위해서는 이미 방문한 경로의 정점을 미 방문 상태로 전환하고 다른 모든 동일 위치에 도달할 수 있는 경우를 체크해야 하는데, 그것은 DFS가 아니라 Brute Force 방법이 된다. 그러면 문제를 푸는데, 시간복잡도가 높아져 문제를 풀 수 없게 된다.

(DFS, BFS는 기본적으로 이미 방문한 정점을 다시 방문하지 않아야 함!)

### 구현방법
큐(Queue) 자료구조를 통해 구현된다.
#### 큐를 사용하는 이유
큐는 FIFO(First-In First-Out) 방식이다. 만약 내가 A 정점을 방문했고 B, C 정점이 인접 정점이라면 B, C를 모두 탐색한 뒤 B, C를 차례로 현재 정점으로 인식하여 다음 정점으로 이동해야 한다.

즉, A정점을 방문 시에, 다음으로 이동할 정점을 큐에 저장하여 First-things-First 라는 Fairness를 구현하고자 함이다.

시간복잡도 : O(V+E), V는 정점 / E는 간선의 개수로 그 개수에 따라 전체 탐색에 시간이 소요된다.(인접 행렬은 O(V^2))

공간복잡도 : O(W), W는 Width로 너비의 크기를 말한다. 즉, 현재 정점의 인접 정점이 많으면 그만큼 큰 공간 복잡도를 갖는다.
### 코드
인접 리스트로 그래프를 구현했으며, Vertex 클래스를 별도로 만들어 인접리스트를 만들었다. 그래프 구현은 필요에 따라 상황에 맞게 수행한다.
```java
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    public static void main(String[] args){
        Vertex v1 = new Vertex('A');
        Vertex v2 = new Vertex('B');
        Vertex v3 = new Vertex('C');
        Vertex v4 = new Vertex('D');
        Vertex v5 = new Vertex('E');
        Vertex v6 = new Vertex('F');
        Vertex v7 = new Vertex('G');
        Vertex v8 = new Vertex('H');

        Graph graph = new Graph(8);
        graph.addEdge(v1, v2); // A - B 연결
        graph.addEdge(v1, v3); // A - C 연결
        graph.addEdge(v2, v4); // B - D 연결
        graph.addEdge(v2, v5); // B - E 연결
        graph.addEdge(v3, v6); // C - F 연결
        graph.addEdge(v3, v7); // C - G 연결
        graph.addEdge(v5, v6); // E - F 연결
        graph.addEdge(v5, v8); // E - H 연결
        graph.addEdge(v6, v7); // F - G 연결

        graph.bfs(v1);

        // Disconnected 된 Graph일 경우 아래와 같이 방문 상태가 아닌 모든 정점에서 부터 시작하면 된다.
        // 사실, 아래와 같이 별도의 Class로 Vertex를 정의했고 그 안에서 인접 정점을 지정하였기 때문에 아래와 같이 배열을 생성함
        // 실제로 인접 리스트 방식으로 생성하면 단순히 배열을 순환하면 된다.
        // 참조 : https://www.geeksforgeeks.org/bfs-disconnected-graph/?ref=rp
        
        /*
        Vertex[] vertexes = {v1, v2, v3, v4, v5, v6, v7, v8};
        for(Vertex v : vertexes){
            if(!v.visited) {
                graph.bfs(v);
            }
        }
         */
        
    }

    // 정점 데이터를 저장하는 클래스
    private static class Vertex{
        char data; // 현재 정점의 데이터
        boolean visited = false; // 현재 정점을 이미 방문 했는지 확인(Cycle 방지)

        // 현재 정점의 인접 정점 리스트
        LinkedList<Vertex> adList = new LinkedList<>();
        public Vertex(char data){
            this.data = data;
        }
    }

    private int v; // 정점의 개수
    public Graph(int v){
        this.v = v;
    }

    // Source 정점에서 Dest 정점을 이어주는 메소드
    // 상호 연결을 수행해줌.
    public void addEdge(Vertex s, Vertex d){
        s.adList.add(d);
        d.adList.add(s);
    }

    // BFS를 수행하는 메소드
    // 탐색 시작할 Vertex를 parameter로 전달달
    public void bfs(Vertex s){
        Queue<Vertex> queue = new LinkedList<>();
        s.visited = true; // 시작 정점을 우선 탐색 완료 처리
        queue.offer(s); // 시작 정점 큐에 추가

        StringBuilder builder = new StringBuilder();
        // 더 이상 탐색할 정점이 없기 전까지 계속 반복 수행
        while(!queue.isEmpty()){
            Vertex current = queue.poll();
            builder.append(current.data).append(" ");

            for(Vertex v : current.adList){
                // 현재 방문이 완료된 정점이 아니라면 다음 방문에 추가!
                if(!v.visited){
                    queue.offer(v);
                    v.visited = true; // 방문 완료 처리
                }
            }
        }
        System.out.println(builder);
    }
}
```
### 방향 그래프(Directed Graph) 순환 탐지 하기(Cycle Detection)
아래와 같은 방향성이 주어진 그래프가 있다고 가정해보자.
<p align="center"><img src="https://i.postimg.cc/kGsC4V8T/img1-daumcdn.png"></p>

너비 우선 탐색에서 순환을 탐지하기 위해서는 위상 정렬(Topological Sort)에서 사용했던 Khan's 알고리즘을 이용할 수 있다.
- 최초에 내차수가 0인 정점을 모두 찾아 각 정점을 큐에 넣는다.
- 큐에서 하나씩 빼서 방문 완료 처리하고 방문 완료 정점의 수를 1 늘리고, 해당 정점의 인접 정점의 내차수를 1씩 뺀 다음, 인접 정점 중 내차수가 0이된 것만 큐에 넣는다.
- 큐가 빌 때까지 위의 과정을 지속 반복한다.
- 큐가 비었는데 전체 정점의 수와 방문 완료된 정점의 수가 다르다면 Cycle이 있다는 것이다.

#### 방향 그래프의 Cycle을 찾는 알고리즘
```java
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {
    public static void main(String[] args){
        Graph g = new Graph(5);

        // 위 그림의 index는 -1씩 해서 적용
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(3, 0);
        g.addEdge(3, 2);
        g.addEdge(4, 3);

        if(g.isCycle()){
            System.out.println("Cycle Detected!");
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        int v; // 정점의 개수
        LinkedList<Integer>[] adj; // 인접 리스트 방식으로 구현(정점 자체를 별도 클래스로 하지 않았음)
        int[] inDegree;

        // 그래프 생성하는 생성자
        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[v];
            for(int i=0; i < this.v; i++){
                adj[i] = new LinkedList<>();
            }
            this.inDegree = new int[this.v];
        }

        // 간선 추가
        public void addEdge(int s, int d){
            this.adj[s].add(d); // S -> D 로 방향 그래프 생성
            this.inDegree[d]++; // D는 내차수 증가
        }

        public boolean isCycle(){
            Queue<Integer> q = new LinkedList<>();
            for(int i=0; i < this.v; i++){
                // 현재 내차수가 0인 것만 큐에 넣는다.
                if(this.inDegree[i] == 0){
                    q.add(i);
                }
            }

            int visited = 0; // 방문 완료된 정점의 개수
            while(!q.isEmpty()){
                int current = q.poll();
                for(int dest : adj[current]){
                    // 내차수 개수가 0인 것만 추가로 넣는다.
                    if(--this.inDegree[dest] == 0){
                        q.add(dest);
                    }
                }
                visited++;
            }
            if(visited != this.v){
                return true;
            } else {
                return false;
            }
        }

    }
}
// 결과
// Cycle Detected!
```
### 무방향 그래프 순환 탐지
무방향 그래프는 상호 연결되어 있기 때문에 방향 그래프에서 순환 탐지 하듯이 탐지를 시도하면 무조건 순환이 있는 것으로 탐지된다.
- 그래프를 만들고 BFS 탐색 함수를 생성하여 현재 정점, 방문 여부 배열을 전달한다.
- 현재 탐색 정점을 방문 완료로 표시하고 parent배열을 따로 생성한 뒤, 최초 탐색 정점의 parent는 -1로 저장
  - parent는 현재 노드와 이어진 이전에 탐색된 노드가 저장된 배열
- BFS 탐색을 위해 큐를 생성하고 최초 탐색 수행 정점을 큐에 넣는다.
- 큐가 비어 있지 않은 동안 반복문을 수행하며 큐의 정점을 하나씩 빼고 방문되지 않은 인접 정점들을 탐색한다.
- 인접 정점이 기 방문 상태이며 parent가 현재 정점 값이 아니라면 순환 있음으로 결과값 반환
  - 인접 정점의 parent가 현재 정점인 경우, 현재를 A, 인접을 B정점이라고 할 때, A방문 후, B에서 다시 A를 방문했다는 의미이므로, 무방향 그래프에서 상호 인접 정점끼리는 무조건 재 탐색 수행을 하고자 하기 때문에 이를 제외 해야 한다.
  - 만약, 현재 정점이 parent가 아니라면 A -> B -> A의 순이 아니라 A -> B -> ? -> ? -> A 와 같이 중간에 다른 정점들을 추가로 탐색했다는 의미이며, 이는 당연히 Cycle이 있는 것이다.
<p align="center"><img src="https://i.postimg.cc/wMm4RG7f/img1-daumcdn.png"></p>

#### 무방향 그래프의 순환을 BFS로 탐색하는 코드
```java
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {
    public static void main(String[] args){
        Graph g = new Graph(5);

        // 위 그림의 index는 -1씩 해서 적용
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 4);

        if(g.isCycle()){
            System.out.println("Cycle Detected!");
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        int v; // 정점의 개수
        LinkedList<Integer>[] adj; // 인접 리스트 방식으로 구현(정점 자체를 별도 클래스로 하지 않았음)
        boolean[] visited; // 각 정점 방문 여부 저장
        boolean[] cycle; // 각 정점이 순환 내에 있는지 저장

        // 그래프 생성하는 생성자
        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[v];
            visited = new boolean[v];
            cycle = new boolean[v];
            for(int i=0; i < this.v; i++){
                adj[i] = new LinkedList<>();
            }
        }

        // 간선 추가, 양 방향이므로 양쪽에 추가
        public void addEdge(int s, int d){
            this.adj[s].add(d);
            this.adj[d].add(s);
        }
        
        // Cycle 탐지를 수행하기 위한 Wrapper 함수
        public boolean isCycle(){
            // disconnected 부분을 체크하기 위해 모든 부부넹서 탐색 수행
            for(int i=0; i < this.v; i++){
                if(!visited[i] && isCycleUtil(i)){
                    return true;
                }
            }
            return false;
        }

        // 실제 순환 탐지를 수행하는 함수
        public boolean isCycleUtil(int i){
            // 이전 정점(부모 정점?)을 저장하는 배열
            int[] parent = new int[this.v];
            
            // 방문 완료 표시 및 큐에 정점 삽입
            Queue<Integer> q = new LinkedList<>();
            visited[i] = true;
            q.add(i);
            
            // 큐가 빌 때까지 탐색 수행
            while(!q.isEmpty()){
                int u = q.poll();
                
                // 인접 정점 모두 탐색
                for(int v : adj[u]){
                
                    // 아직 방문되지 않은 정점이면 탐색 수행
                    if(!visited[v]){
                        visited[v] = true;
                        q.add(v);
                        parent[v] = u;
                    
                    // 현재 정점의 부모 정점이 v가 아니면 Cycle이 있는 것
                    } else if(parent[u] != v){
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
// 결과
// Cycle Detected!
```
## 너비 우선 탐색의 응용
- 최단 경로 찾기 및 최소 스패닝 트리
- P2P 네트워크
- 검색 엔진 Crawling
- 소셜 네트워킹
- GPS Navigation system
- Garbage Collection
- Network Broadcasting
- Ford-Fulkerson 알고리즘
  
이 예시들의 공통점은 인접한 데이터에 대해 우선적으로 찾는 것이 중요하다는 점이다. 간단히 P2P 네트워크나 소셜 네트워킹을 수행한다면 가장 인접해 있는 정점들과의 연결이 매우 중요할 것이다. GPS 시스템도 그러하다.






























