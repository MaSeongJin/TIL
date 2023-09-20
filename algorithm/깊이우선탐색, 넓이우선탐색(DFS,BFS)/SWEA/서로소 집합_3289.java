// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int N, M;
	public static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			p = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				if (Integer.parseInt(st.nextToken()) == 0) {
					int px = findset(Integer.parseInt(st.nextToken()));
					int py = findset(Integer.parseInt(st.nextToken()));
					union(px, py);
				} else {
					if(findset(Integer.parseInt(st.nextToken())) == findset(Integer.parseInt(st.nextToken()))) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}

			System.out.println("#" + tc + " " + sb);

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
