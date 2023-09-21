// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static int N;
	public static double E;
	public static long[] X;
	public static long[] Y;
	public static List<Edge>[] graph;

	public static class Edge implements Comparable<Edge> {
		int x;
		double cost;

		public Edge(int x, double cost) {
			super();
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st, st1;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			X = new long[N + 1];
			Y = new long[N + 1];
			graph = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			st1 = new StringTokenizer(br.readLine());
			E = Double.parseDouble(br.readLine());
			for (int i = 1; i <= N; i++) {
				X[i] = Long.parseLong(st.nextToken());
				Y[i] = Long.parseLong(st1.nextToken());
			}

			for (int i = 1; i < N; i++) {
				for (int j = i+1; j <= N; j++) {
					graph[i].add(new Edge(j, getCost(i, j)));
					graph[j].add(new Edge(i, getCost(i, j)));
				}
			}

			boolean[] visited = new boolean[N + 1];

			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.offer(new Edge(1, 0.0));

			double ans = 0.0;
			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				int v = edge.x;
				double cost = edge.cost;
				if (visited[v]) {
					continue;
				}
				visited[v] = true;
				ans += cost;

				for (Edge e : graph[v]) {
					if (!visited[e.x]) {
						pq.add(e);
					}
				}

			}
			System.out.println("#" + tc + " " + Math.round(ans * E));

		}
	}

	public static double getCost(int x, int y) {
		return (X[x] - X[y]) * (X[x] - X[y]) + (Y[x] - Y[y]) * (Y[x] - Y[y]);
	}

}
