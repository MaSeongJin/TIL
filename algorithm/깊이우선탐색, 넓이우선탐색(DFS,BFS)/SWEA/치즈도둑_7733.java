// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWrDOdQqRCUDFARG
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int N;
	public static int[][] cheeze;
	public static int result, day, today;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static boolean[][] eat;
	public static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			cheeze = new int[N][N];
			eat = new boolean[N][N];
			day = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheeze[i][j] = Integer.parseInt(st.nextToken());
					day = Math.max(day, cheeze[i][j]);
				}
			}
			result = 1;
			today = 1;
			while (day-- > 0) {
				visited = new boolean[N][N];
				int tmp = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (cheeze[i][j] == today) {
							eat[i][j] = true;
						}
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!eat[i][j] && !visited[i][j]) {
							dfs(i, j);
							tmp++;
						}
					}
				}
				result = Math.max(tmp, result);
				today++;
			}

			System.out.println("#" + tc + " " + result);

		}

	}

	private static void dfs(int x, int y) {
		if (visited[x][y])
			return;
		visited[x][y] = true;
		int nextI;
		int nextJ;
		for (int i = 0; i < 4; i++) {
			nextI = x + iDir[i];
			nextJ = y + jDir[i];
			if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < N && !eat[nextI][nextJ] && !visited[nextI][nextJ]) {
				dfs(nextI, nextJ);
			}
		}
	}

}
