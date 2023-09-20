// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	public static int N, M;
	public static int[] p;
	public static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			p = new int[N + 1];
			arr = new int[M][2];

			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});

			for (int i = 0; i < M; i++) {
				int px = findset(arr[i][0]);
				int py = findset(arr[i][1]);
				union(px, py);
			}
			
			int ans = 0;
			boolean[] chk = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				if (!chk[findset(p[i])]) {
					chk[findset(p[i])] = true;
					ans++;
				}
			}

			System.out.println("#" + tc + " " + ans);

		}
	}

	private static void union(int x, int y) {
		p[y] = x;
	}

	private static int findset(int i) {
		if (p[i] != i) {
			p[i] = findset(p[i]);
		}
		return p[i];
	}

}
