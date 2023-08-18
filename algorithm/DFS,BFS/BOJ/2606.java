// bfs 풀이
public static int[][] node;
public static boolean visit[];	// 정점 탐색 여부 체크
public static int N, M;	// 정점, 간선, 시작 정점

public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  N = Integer.parseInt(br.readLine());	// 컴퓨터의 수(정점)
  M = Integer.parseInt(br.readLine());	// 연결된 컴퓨터 쌍의 수(간선)
  
  node = new int[N+1][N+1];
  visit = new boolean[N+1];
  for (int i = 0; i < M; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    node[a][b] = 1;
    node[b][a] = 1;
  }

  bfs(1);
  
}

public static void bfs(int start) {
  Queue<Integer> queue = new LinkedList<Integer>();
  
  visit[start] = true;
  queue.offer(start);
  int cnt = 0;	// 정점과 연결된 바이러스 걸리는 컴퓨터 수
  while(!queue.isEmpty()) {
    int x = queue.poll();
    for (int i = 1; i < node.length; i++) {
      if(node[x][i] == 1 && !visit[i]) {
        queue.offer(i);
        visit[i] = true;
        cnt++;
      }
    }
  }
  System.out.println(cnt);
}
// dfs 풀이
public static int[][] node;
public static boolean visit[];	// 정점 탐색 여부 체크
public static int N, M;	// 정점, 간선, 시작 정점
public static int cnt = -1;	// 정점과 연결된 바이러스 걸리는 컴퓨터 수, 1번 컴퓨터는 제외하기 때문에 -1

public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  N = Integer.parseInt(br.readLine());	// 컴퓨터의 수(정점)
  M = Integer.parseInt(br.readLine());	// 연결된 컴퓨터 쌍의 수(간선)
  
  node = new int[N+1][N+1];
  visit = new boolean[N+1];
  for (int i = 0; i < M; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    node[a][b] = 1;
    node[b][a] = 1;
  }

  dfs(1);
  System.out.println(cnt);
}

public static void dfs(int v) {
  if(visit[v]) {	// 재귀호출 종료 부
    return;
  }
  visit[v] = true;
  cnt++;
  
  for (int i = 0; i < node[v].length; i++) {
    if(node[v][i] == 1 && !visit[i]) {
      dfs(i);
    }
  }
  
}
