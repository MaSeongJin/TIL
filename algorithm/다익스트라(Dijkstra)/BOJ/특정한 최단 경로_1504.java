import java.io.*;
import java.util.*;

public class Main {
 
    static class Node implements Comparable<Node> {
        int end, weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    
    static int INF = 200_000_100; // 간선 개수(E) 최대값 * 거리(c) 최대값 == 200,000,000
    static int N, E;
    static List<Node>[] graph; // 노드, 간선 그래프

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); // 정점 개수
        E = Integer.parseInt(input[1]); // 간선 개수
        
        // 그래프 생성
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<Node>();
        }
        // 그래프 정보 입력
        for (int e = 0; e < E; e++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        // 반드시 거쳐야 하는 두 개의 정점
        input = br.readLine().split(" ");
        int v1 = Integer.parseInt(input[0]);
        int v2 = Integer.parseInt(input[1]);

        int distA = 0; // v1 -> v2 일 때 최단거리
        int distB = 0; // v2 -> v1 일 때 최단거리
        // v1 -> v2
        distA += dijkstra(1, v1);
        distA += dijkstra(v1, v2);
        distA += dijkstra(v2, N);
        // v2 -> v1
        distB += dijkstra(1, v2);
        distB += dijkstra(v2, v1);
        distB += dijkstra(v1, N);
        
        // 정답 구하기
        int answer = (distA >= INF && distB >= INF) ? -1 : Math.min(distA, distB);

        System.out.println(answer);
    }

    static int dijkstra(int start, int end) {
        // 우선순위 큐 사용
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // start부터 각 정점까지의 거리 저장할 배열 -> 처음엔 INF로 초기화
        int[] distance = new int[N + 1];
        for(int i=0; i<=N; i++) {
            distance[i] = INF;
        }

        pq.add(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            int dist = node.weight;
            int nowNum = node.end;
            
            // 현재 노드까지의 거리가 기록된 거리보다 길면 해당 경로는 버림
            if(distance[nowNum] < dist) {
                continue;
            }
            // 현재 노드에 연결된 다른 노드를 탐색
            for(Node nxt : graph[nowNum]) {
                int nxtNum = nxt.end;
                int nxtDist = nxt.weight;

                int cost = dist + nxtDist;
                // 더 짧은 거리가 있으면
                if(cost < distance[nxtNum]) {
                    distance[nxtNum] = cost;
                    pq.add(new Node(nxtNum, cost));
                }
            }
        }

        return distance[end];
    }
   
}
