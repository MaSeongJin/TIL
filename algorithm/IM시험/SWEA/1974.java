// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Psz16AYEDFAUq
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int[] check;
	public static int[][] map;
	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			map = new int[9][9];

			StringTokenizer st;
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 1;
			for (int round = 0; round < map.length; round++) {
				check = new int[10];
				for (int i = 0; i < map.length; i++) {
					check[map[round][i]]++;
				}
				check();
				check = new int[10];
				for (int i = 0; i < map.length; i++) {
					check[map[i][round]]++;
					
				}
				check();
				for (int cnt = 0; cnt < 3; cnt++) {
					for (int cnt1 = 0; cnt1 < 3; cnt1++) {
						check = new int[10];
						for (int i = 3 * cnt; i < 3 * cnt + 3; i++) {
							for (int j = 3 * cnt1; j < 3 * cnt1 + 3; j++) {
								check[map[i][j]]++;
							}
						}
						check();
					}
				}

			}

			System.out.println("#" + tc + " " + result);
		}
	}

	public static void check() {
		for (int i = 1; i < map.length; i++) {
			if (check[i] > 1) {
				result = 0;
				return;
			}
		}
	}

}
