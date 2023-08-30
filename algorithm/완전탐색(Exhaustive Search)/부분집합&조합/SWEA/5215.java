// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int N, L;
	public static int[][] arr;
	public static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			arr = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			max = 0;
			combination(0, 0, 0);
			System.out.println("#" + tc + " " + max);
		}
	}

	public static void combination(int idx, int sum, int cal) {
		if (cal > L) {
			return;
		}
		if (cal <= L) {
			max = Math.max(max, sum);
		}
		if (idx == N) {
			return;
		}
		combination(idx + 1, sum + arr[idx][0], cal + arr[idx][1]);
		combination(idx + 1, sum, cal);
	}
}
