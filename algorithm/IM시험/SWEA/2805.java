// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			String str;
			int tmp;
			for (int i = 0; i < map.length; i++) {
				str = br.readLine();
				for (int j = 0; j < map.length; j++) {
					tmp = str.charAt(j) - '0';
					map[i][j] = tmp;
				}
			}
			int cnt = 0;
			for (int i = 0; i < map.length / 2 + 1; i++) {
				for (int j = map.length / 2 - i; j < map.length / 2 + 1 + i; j++) {
					cnt += map[i][j];
				}
			}
			for (int i = map.length / 2 + 1; i < N; i++) {
				for (int j = i - map.length / 2; j < N - i + map.length / 2; j++) {
					cnt += map[i][j];
				}
			}

			System.out.println("#" + tc + " " + cnt);

		}
	}

}
