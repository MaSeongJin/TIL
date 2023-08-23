### 깊이 우선 탐색(DFS-Depth First Search)
최초 시작 정점에서 가장 먼저 이어져 있는(간선으로 연결된) 정점을 하나 찾고 해당 정점에 또 인접한 정점을 찾아 더 이상 깊이 갈 수 없을 때까지
탐색한 뒤 돌아오는 방식이다.

트리와의 큰 차이점은 그래프는 순환할 수 있다는 것이다. 그래서, 순환 탐지를 할 수 있도록 추가적인 기능을 구현해야 한다.

BFS와의 큰 차이점은 DFS는 탐색을 한 뒤 이전의 정점으로 돌아온다는 것이다. 이것을 백트래킹이라 한다.

<p align="center"><img src="https://i.postimg.cc/0NnLPjNG/img.gif"></p>

위의 애니메이션을 보면 0번에서 1, 2로 이동한 뒤 2에서 더 이상 깊이 갈 수 없어 2를 탐색 완료 후, 1로 돌아온다.
다시 1에서 3, 4로 더 깊이 들어갔다가 나오는 방식을 반복한다. 말 그대로, 깊이 갈 수 있을 때까지 탐색하고 더 이상 탐색이 불가하면 되돌아오는 방식이다.

#### DFS를 사용하는 예시
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

#### 구현 방법
스택 또는 재귀 방식을 이용하여 구현할 수 있다. 스택은 LIFO방식이기 때문에 인접한 정점이 우선이 아닌 더 멀리 있는(인접 정점의 끝) 정점을
우선 탐색한 뒤 돌아올 수 있다.

재귀 방식으로 진행도 가능하다. 재귀 자체가 내부적으로 Stack을 사용하기 때문이다. 둘 중에서는 재귀방식이 더 많이 쓰인다.
  
시간복잡도 : O(V+E), V는 정점 / E는 간선의 개수로 그 개수에 따라 전체 탐색에 시간이 소요된다.(인접 행렬은 O(V^2))

공간복잡도 : O(V), 최악의 경우, 정점이 1열로 이어져 전체 정점의 수 만큼 스택이 쌓일 수 있다.

#### 코드
```java
pacakge com.test;

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



























