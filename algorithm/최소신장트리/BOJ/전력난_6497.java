import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static ArrayList<Edge> graph = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            graph = new ArrayList<>();
            int total = 0;
            int sum = 0;
            parent = new int[N + 1];


            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                sum += cost;
                graph.add(new Edge(start, end, cost));
            }

            graph.sort(Comparator.comparingInt(o -> o.cost));

            for (Edge edge : graph) {
                if (find(edge.start) != find(edge.end)) {
                    total += edge.cost;
                    union(edge.start, edge.end);
                }
            }

            System.out.println(sum - total);
        }
    }

    static int find(int start) {
        if (parent[start] == start) {
            return start;
        }
        return parent[start] = find(parent[start]);
    }

    static void union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start != end) {
            parent[end] = start;
        }
    }

}
