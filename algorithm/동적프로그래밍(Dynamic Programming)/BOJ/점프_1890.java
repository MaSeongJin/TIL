import java.util.*;

public class Main {
	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] board = new int[N][N];
		long[][] dp = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		dp[0][0] = 1;
		System.out.println(dp(board, dp));

	}

	public static long dp(int[][] board, long[][] dp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int moveCnt = board[i][j];
				if (i == N - 1 && j == N - 1) {
					return dp[N - 1][N - 1];
				}
				if (i + moveCnt < N) {
					dp[i + moveCnt][j] += dp[i][j];
				}
				if (j + moveCnt < N) {
					dp[i][j + moveCnt] += dp[i][j];
				}

			}
		}

		return -1;

	}

}
