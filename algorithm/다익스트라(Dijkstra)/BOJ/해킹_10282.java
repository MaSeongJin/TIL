import java.io.*;
import java.util.*;

public class Main {

    static class Computer implements Comparable<Computer> {
        int depend;
        int time;

        public Computer(int depend, int time) {
            this.depend = depend;
            this.time = time;
        }

        @Override
        public int compareTo(Computer o) {
            return this.time - o.time;
        }
    }

    static int INF = Integer.MAX_VALUE;
    static ArrayList<Computer>[] list;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list = new ArrayList[n + 1];
            dist = new int[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i < n + 1; i++) {
                dist[i] = INF;
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list[b].add(new Computer(a, s));
            }

            dijkstra(c);

            int infection = 0;
            int total = 0;

            for (int i = 1; i < n + 1; i++) {
                if (dist[i] != INF) {
                    infection++;
                    total = Math.max(total, dist[i]);
                }
            }

            System.out.println(infection + " " + total);
        }

    }

    public static void dijkstra(int start) {
        PriorityQueue<Computer> que = new PriorityQueue<>();

        dist[start] = 0;
        que.offer(new Computer(start, 0));

        while (!que.isEmpty()) {
            int cur = que.poll().depend;

            if (!visited[cur]) {
                visited[cur] = true;

                for (Computer next : list[cur]) {
                    if (dist[next.depend] > dist[cur] + next.time) {
                        dist[next.depend] = dist[cur] + next.time;
                        que.add(new Computer(next.depend, dist[next.depend]));
                    }
                }
            }
        }

    }

}
