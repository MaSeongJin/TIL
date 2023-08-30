// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7IzvG6EksDFAXB
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int N, K;
	public static int[] arr;
	public static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			cnt = 0;
			combination(0, 0);
			System.out.println("#" + tc + " " + cnt);
		}
	}

	public static void combination(int idx, int sum) {
		if (sum > K) {
			return;
		}
		if (sum == K) {
			cnt++;
			return;
		}
		if (idx == N) {
			return;
		}
		combination(idx + 1, sum + arr[idx]);
		combination(idx + 1, sum);
	}
}
