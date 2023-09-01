// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GKs06AU0DFAXB
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static int N;
	public static int[][] chess;
	public static int cnt;
	public static int[] iDir = { -1, -1, -1, 0, 1, 1, 1, 0 };
	public static int[] jDir = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			chess = new int[N][N];
			cnt = 0;
			choice(0);
			System.out.println("#" + tc + " " + cnt);
		}
	}

	private static void choice(int depth) {
		if (depth == N) {
			cnt++;
			return;
		}
		for (int i = 0; i < N; i++) {
			chess[depth][i] = 1;
			if (check(depth, i)) {
				choice(depth + 1);
			}
			chess[depth][i] = 0;
		}
	}

	private static boolean check(int depth, int y) {
		for (int i = 0; i < 8; i++) {
			int nextI = depth + iDir[i];
			int nextJ = y + jDir[i];
			while (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < N) {
				if (chess[nextI][nextJ] == 1) {
					return false;
				}
				nextI += iDir[i];
				nextJ += jDir[i];
			}
		}
		return true;
	}

}
