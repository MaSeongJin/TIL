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
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N+1][N+1];
			arr[R][C] = M;
			int[] iDir = { 0, 1, 0, -1 };
			int[] jDir = { 1, 0, -1, 0 };
			int tmp = 1;
			int time = N;
			while (time-- > 0) {
				int num = M + D;
				if(num >= 255) {
					num = 255;
				}
				if(num <= 0) {
					num = 0;
				}
				R--;
				C--;
				int x = R;
				int y = C;
				for (int dir = 0; dir < jDir.length; dir++) {
					for (int i = 0; i < 2 * tmp; i++) {
						if (x >= 1 && x <= N && y >= 1 && y <= N) {
							arr[x][y] = num;
						}
						x += iDir[dir];
						y += jDir[dir];
					}
				}
				tmp++;
				M += D;
			}
			int cnt;
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < arr.length; i++) {
				cnt = 0;
				for (int j = 1; j < arr.length; j++) {
					cnt += arr[i][j];
				}
				sb.append(cnt).append(" ");
			}
			System.out.println("#" + tc + " " + sb);

		}
	}
}
