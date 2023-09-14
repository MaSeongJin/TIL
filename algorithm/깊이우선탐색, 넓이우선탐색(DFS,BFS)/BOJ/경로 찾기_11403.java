import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int[][] map;
	public static int[][] resultMap;
	public static boolean[] visited;
	public static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		resultMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			flag = false;
			dfs(i, i);
			for (int j = 0; j < N; j++) {
				if(i==j) {
					if(!flag) {
						continue;
					}
				}
				if (visited[j]) {
					resultMap[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(resultMap[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void dfs(int v, int start) {
		if (visited[v]) {
			if (v == start) {
				flag = true;
			}
			return;
		}
		visited[v] = true;
		for (int i = 0; i < N; i++) {
			if (map[v][i] == 1) {
				dfs(i, start);
			}
		}
	}

}
