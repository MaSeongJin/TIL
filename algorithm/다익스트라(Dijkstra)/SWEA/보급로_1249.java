// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] map;
	static int[][] dist;
	static int[] dc = { -1, 0, 1, 0 };
	static int[] dr = { 0, 1, 0, -1 };

	static class Point implements Comparable<Point> {
		int col, row, cost;

		public Point(int col, int row, int cost) {
			this.col = col;
			this.row = row;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			System.out.println("#" + tc + " " + dijkstra());
		}
	}

	private static int dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		dist[0][0] = map[0][0];
		pq.add(new Point(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Point p = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nc = p.col + dc[i];
				int nr = p.row + dr[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					int tmp = dist[p.col][p.row] + map[nc][nr];
					if (dist[nc][nr] > tmp) {
						dist[nc][nr] = tmp;
						pq.offer(new Point(nc, nr, dist[nc][nr]));
					}
				}
			}
		}

		return dist[N - 1][N - 1];
	}

}
