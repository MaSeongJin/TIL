import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N + P * 2][N + P * 2];
			for (int i = P; i < arr.length - P; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = P; j < arr.length - P; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt1;
			int cnt2;
			int max = 0;
			int realMax = 0;
			int[] iDir = { -1, 0, 1, 0 };
			int[] jDir = { 0, 1, 0, -1 };
			int[] iDir1 = { -1, 1, 1, -1 };
			int[] jDir1 = { 1, 1, -1, -1 };
			for (int i = P; i < arr.length - P; i++) {
				for (int j = P; j < arr.length - P; j++) {
					cnt1 = arr[i][j];
					cnt2 = arr[i][j];
					for (int j2 = 0; j2 < 4; j2++) {
						for (int k = 1; k <= P; k++) {
							cnt1 += arr[i + iDir[j2] * k][j + jDir[j2] * k];
							cnt2 += arr[i + iDir1[j2] * k][j + jDir1[j2] * k];
						}
					}
					if(cnt1 >= cnt2) {
						max = cnt1;
					} else {
						max = cnt2;
					}
					if(realMax < max) {
						realMax = max;
					}
				}
			}

			System.out.println("#" + tc + " " + realMax);

		}
	}
}
