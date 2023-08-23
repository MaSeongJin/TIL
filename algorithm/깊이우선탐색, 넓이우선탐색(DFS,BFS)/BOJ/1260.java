import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int[][] node;
	public static boolean[] visit;
	public static int N, M, V;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		node = new int[N + 1][N + 1];
		visit = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			node[a][b] = 1;
			node[b][a] = 1;
		}

		dfs(V);
		bfs(V);
		System.out.println(sb);
	}

	public static void dfs(int v) {
		if (visit[v]) {
			return;
		}
		visit[v] = true;
		sb.append(v).append(" ");
		for (int i = 0; i < node[v].length; i++) {
			if (node[v][i] == 1 && !visit[i]) {
				dfs(i);
			}
		}
	}

	public static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visit = new boolean[N + 1];
		visit[v] = true;
		queue.offer(v);
		sb.append("\n");
		sb.append(v).append(" ");
		while (!queue.isEmpty()) {
			int x = queue.poll();
			for (int i = 0; i < node.length; i++) {
				if (node[x][i] == 1 && !visit[i]) {
					queue.offer(i);
					visit[i] = true;
					sb.append(i).append(" ");
				}
			}
		}
	}

}
