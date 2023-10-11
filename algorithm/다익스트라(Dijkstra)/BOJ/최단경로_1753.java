// https://www.acmicpc.net/problem/1753
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Node implements Comparable<Node> {
		public int x;
		public int w;

		public Node(int x, int w) {
			this.x = x;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}

	}

	public static int V, E;
	public static int[] distance;
	public static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		nodeList = new ArrayList[V + 1];
		distance = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			distance[i] = Integer.MAX_VALUE;
			nodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			nodeList[Integer.parseInt(st.nextToken())]
					.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		dijkstra(start);
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}
	}

	public static void dijkstra(int v) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		que.add(new Node(v, 0));
		distance[v] = 0;
		while (!que.isEmpty()) {
			Node now = que.poll();
			int nowI = now.x;
			if (!visited[nowI]) {
				visited[nowI] = true;
				for (int i = 0; i < nodeList[nowI].size(); i++) {
					Node node = nodeList[nowI].get(i);
					int tmp = node.w + distance[nowI];
					if (distance[node.x] > tmp) {
						distance[node.x] = tmp;
						que.add(new Node(node.x, distance[node.x]));
					}
				}
			}
		}
	}

}
