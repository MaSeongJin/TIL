import java.util.*;
import java.io.*;

public class Main {
  
    static class Edge {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
  
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        List<Edge> edges = new ArrayList<>();

        // 간선 정보 입력 받기
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = i + 1; j < N; j++) {
                edges.add(new Edge(i, j, Integer.parseInt(input[j])));
            }
        }

        // 간선을 비용 기준으로 오름차순 정렬
        edges.sort(Comparator.comparingInt(edge -> edge.cost));

        // 부모 배열 초기화
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        long answer = 0; // 비용 합계
        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                answer += edge.cost;
            }
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX; // 하나의 집합으로 합치기
            return true;
        }
        return false;
    }

}
