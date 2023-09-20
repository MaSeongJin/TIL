// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	public static int N;
	public static double E;
	public static int[] p;
	public static long[] X;
	public static long[] Y;
	public static point[] pointList;

	public static class point {
		int x, y;
		double cost;

		public point(int x, int y, double cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st, st1;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			p = new int[N + 1];
			X = new long[N + 1];
			Y = new long[N + 1];
			int al = N * (N - 1) / 2;
			pointList = new point[al];

			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			st = new StringTokenizer(br.readLine());
			st1 = new StringTokenizer(br.readLine());
			E = Double.parseDouble(br.readLine());
			for (int i = 1; i <= N; i++) {
				X[i] = Long.parseLong(st.nextToken());
				Y[i] = Long.parseLong(st1.nextToken());
			}

			int idx = 0;
			for (int i = 1; i < N; i++) {
				for (int j = i + 1; j <= N; j++) {
					pointList[idx++] = new point(i, j, getCost(i, j));
				}
			}
			
			Arrays.sort(pointList, new Comparator<point>() {

				@Override
				public int compare(point o1, point o2) {
					if ((o1.cost - o2.cost) < 0) {
						return -1;
					} else if ((o1.cost - o2.cost) > 0) {
						return 1;
					} else {
						return 0;
					}
				}
			});

			idx = 0;
			int pick = 0;
			double ans = 0.0;
			while (pick < N - 1) {
				int px = findset(pointList[idx].x);
				int py = findset(pointList[idx].y);

				if (px != py) {
					union(px, py);
					ans += pointList[idx].cost;
					pick++;
				}
				idx++;
			}
			System.out.println("#" + tc + " " + Math.round(ans*E));

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

	public static double getCost(int x, int y) {
		return (X[x] - X[y]) * (X[x] - X[y]) + (Y[x] - Y[y]) * (Y[x] - Y[y]);
	}

}
