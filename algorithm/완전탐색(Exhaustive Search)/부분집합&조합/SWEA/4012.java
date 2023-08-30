// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int N, K;
	public static int[][] arr;
	public static boolean[] visit;
	public static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			visit = new boolean[N];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			combination(0, 0);
			System.out.println("#" + tc + " " + min);

		}
	}

	public static void combination(int idx, int sidx) {
		if (sidx == N / 2) {
			min = Math.min(min, cal());
			return;
		}
		for (int i = idx; i <= N / 2 + sidx; i++) {
			visit[i] = true;
			combination(i + 1, sidx + 1);
			visit[i] = false;
		}
	}

	public static int cal() {
		int synX = 0;
		int synY = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				if (visit[i] && visit[j]) {
					synX += arr[i][j];
				} else if (!visit[i] && !visit[j]) {
					synY += arr[i][j];
				}
			}
		}
		return Math.abs(synX - synY);
	}

}
