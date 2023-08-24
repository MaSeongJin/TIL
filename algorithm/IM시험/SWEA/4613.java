// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWQl9TIK8qoDFAXj
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
			char[][] ch = new char[N][M];
			for (int i = 0; i < N; i++) {
				ch[i] = br.readLine().toCharArray();
			}
			int[][] color = new int[N][3];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (ch[i][j] == 'W')
						color[i][0]++;
					else if (ch[i][j] == 'B')
						color[i][1]++;
					else
						color[i][2]++;
				}
			}

			int cnt;
			int min = Integer.MAX_VALUE;
			int rb;
			for (int i = 1; i < N - 1; i++) {
				rb = N - i;
				int b;
				for (int j = 1; j < rb; j++) {
					cnt = 0;
					b = i + j;
					for (int j2 = 0; j2 < i; j2++) {
						cnt += color[j2][1];
						cnt += color[j2][2];
					}
					for (int j2 = i; j2 < b; j2++) {
						cnt += color[j2][0];
						cnt += color[j2][2];
					}
					for (int j2 = b; j2 < N; j2++) {
						cnt += color[j2][0];
						cnt += color[j2][1];
					}
					if (cnt < min) {
						min = cnt;
					}
				}
			}

			System.out.println("#" + tc + " " + min);

		}
	}

}
