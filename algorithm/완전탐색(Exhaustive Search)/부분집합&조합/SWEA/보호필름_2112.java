// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int D, W, K;
	public static int[][] cell;
	public static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			cell = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					cell[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = Integer.MAX_VALUE;

			dfs(0, 0);

			System.out.println("#" + tc + " " + ans);

		}
	}

	public static void dfs(int depth, int cnt) {
		if (check()) {
			ans = Math.min(ans, cnt);
			return;
		}

		if (cnt > ans) {
			return;
		}

		if (depth == D) {
			return;
		}

		int[] tmp = new int[W];
		for (int i = 0; i < W; i++) {
			tmp[i] = cell[depth][i];
		}

		dfs(depth + 1, cnt);

		for (int i = 0; i < W; i++) {
			cell[depth][i] = 0;
		}

		dfs(depth + 1, cnt + 1);

		for (int i = 0; i < W; i++) {
			cell[depth][i] = 1;
		}

		dfs(depth + 1, cnt + 1);

		cell[depth] = tmp;

	}

	public static boolean check() {
		for (int i = 0; i < W; i++) {
			int cnt = 1;
			boolean flag = false;
			for (int j = 1; j < D; j++) {
				if (cell[j][i] == cell[j - 1][i]) {
					cnt++;
				} else {
					cnt = 1;
				}
				if (cnt == K) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				return false;
			}
		}
		return true;
	}

}
