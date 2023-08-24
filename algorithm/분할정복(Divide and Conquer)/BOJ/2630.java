// https://www.acmicpc.net/problem/2630
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] map;
	public static int blue = 0;
	public static int white = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		find(N, 0, 0);
		System.out.println(white);
		System.out.println(blue);
	}

	public static void find(int n, int a, int b) {
		int flag = map[a][b];
		if (n == 1) {
			if (flag == 1) {
				blue++;
			} else {
				white++;
			}
			return;
		}
		int cnt = 0;
		for (int i = a; i < n + a; i++) {
			for (int j = b; j < n + b; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}
		if (cnt == n * n) {
			blue++;
			return;
		} else if (cnt == 0) {
			white++;
			return;
		}
		find(n / 2, a, b);
		find(n / 2, a, b + n / 2);
		find(n / 2, a + n / 2, b);
		find(n / 2, a + n / 2, b + n / 2);
	}

}
