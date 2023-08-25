// https://www.acmicpc.net/problem/21736
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static char[][] map;
	public static int p = 0;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N + 1][M + 1];
		int v1 = 0;
		int v2 = 0;
		for (int i = 1; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 1; j < map[i].length; j++) {
				map[i][j] = str.charAt(j - 1);
				if (map[i][j] == 'I') {
					v1 = i;
					v2 = j;
				}
			}
		}
		visit = new boolean[N+1][M+1];
		dfs(v1, v2);
		if(p == 0) {
			System.out.println("TT");
		} else {
			System.out.println(p);
		}
	}

	public static void dfs(int v1, int v2) {
		if (v1 <= 0 || v2 <= 0 || v1 > N || v2 > M || visit[v1][v2]) {
			return;
		}
		visit[v1][v2] = true;
		if (map[v1][v2] == 'P') {
			p++;
		} else if (map[v1][v2] == 'X') {
			return;
		}
		for (int i = 0; i < iDir.length; i++) {
			int x = v1 + iDir[i];
			int y = v2 + jDir[i];
			if (x > 0 && y > 0 && x <= N && y <= M && !visit[x][y]) {
				dfs(x,y);
			}
		}
	}

}
