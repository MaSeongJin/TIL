// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N;
	public static int[][] map;
	public static int[] iDir = { 1, 1, -1, -1 };
	public static int[] jDir = { 1, -1, -1, 1 };
	public static boolean[][] visited;
	public static boolean[] dessert;
	public static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					visited = new boolean[N][N];
					dessert = new boolean[101];
					visited[i][j] = true;
					dessert[map[i][j]] = true;
					dfs(1, i, j, i, j, 0);
				}
			}

			if (max == 0)
				max = -1;
			System.out.println("#" + tc + " " + max);
		}

	}

	private static void dfs(int cnt, int startI, int startJ, int initI, int initJ, int prevDir) {

		for (int i = prevDir; i < 4; i++) {
			int nextI = startI + iDir[i];
			int nextJ = startJ + jDir[i];

			if (nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < N) {
				if ((nextI == initI) && (nextJ == initJ) && cnt > 2) {

					max = Math.max(max, cnt);
					return;
				}
				if (!visited[nextI][nextJ] && !dessert[map[nextI][nextJ]]) {
					visited[nextI][nextJ] = true;
					dessert[map[nextI][nextJ]] = true;
					dfs(cnt + 1, nextI, nextJ, initI, initJ, i);
					visited[nextI][nextJ] = false;
					dessert[map[nextI][nextJ]] = false;
				}

			}

		}
	}
}
