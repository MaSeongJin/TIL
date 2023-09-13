// https://www.acmicpc.net/problem/14940
public class Main {

	public static int n, m;
	public static int[][] map;
	public static int[][] resultMap;
	public static int startI, startJ;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		resultMap = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					map[i][j] = 0;
					startI = i;
					startJ = j;
				} else if (map[i][j] == 0) {
					visited[i][j] = true;
				}
			}
		}

		visited[startI][startJ] = true;
		bfs(startI, startJ);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j]) {
					System.out.print(resultMap[i][j] + " ");
				} else {
					System.out.print(-1 + " ");
				}
			}
			System.out.println();
		}

	}

	private static void bfs(int x, int y) {
		Queue<Integer[]> que = new LinkedList<>();
		que.add(new Integer[] { x, y });
		while (!que.isEmpty()) {
			Integer[] now = que.poll();
			int nowI = now[0];
			int nowJ = now[1];
			for (int i = 0; i < 4; i++) {
				int nextI = nowI + iDir[i];
				int nextJ = nowJ + jDir[i];
				if (nextI >= 0 && nextJ >= 0 && nextI < n && nextJ < m && !visited[nextI][nextJ]) {
					que.add(new Integer[] { nextI, nextJ });
					visited[nextI][nextJ] = true;
					resultMap[nextI][nextJ] = resultMap[nowI][nowJ] + 1;
				}
			}

		}
	}
}
