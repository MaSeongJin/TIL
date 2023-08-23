// https://www.acmicpc.net/problem/11724
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int[][] node;
	public static int N, M;
	public static int cnt = 0;
	public static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		node = new int[N + 1][N + 1];
		visit = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			node[a][b] = 1;
			node[b][a] = 1;
		}
		for (int i = 1; i < node.length; i++) {
			if(!visit[i]) {
				dfs(i);
				cnt++;
			}
		}

		System.out.println(cnt);

	}

	public static void dfs(int v) {
		if (visit[v]) {
			return;
		}
		visit[v] = true;
		for (int i = 0; i < node[v].length; i++) {
			if (!visit[i] && node[v][i] != 0)
				dfs(i);
		}
	}

}
