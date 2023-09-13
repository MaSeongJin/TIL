// https://www.acmicpc.net/problem/7576
public class Main {

	public static int N, M;
	public static int[][] map;
	public static int startI, startJ;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static boolean[][] visited;
	public static int result;
	public static List<Integer[]> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					list.add(new Integer[] { i, j });
				}
			}
		}

		bfs();

		loop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					result = 0;
					break loop;
				} else {
					result = Math.max(result, map[i][j]);
				}
			}
		}

		System.out.println(result - 1);

	}

	private static void bfs() {
		Queue<Integer[]> que = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			Integer[] start = list.get(i);
			startI = start[0];
			startJ = start[1];
			visited[startI][startJ] = true;
			que.add(new Integer[] { startI, startJ });
		}
		while (!que.isEmpty()) {
			Integer[] now = que.poll();
			int nowI = now[0];
			int nowJ = now[1];
			for (int i = 0; i < 4; i++) {
				int nextI = nowI + iDir[i];
				int nextJ = nowJ + jDir[i];
				if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M && !visited[nextI][nextJ]
						&& map[nextI][nextJ] == 0) {
					que.add(new Integer[] { nextI, nextJ });
					visited[nextI][nextJ] = true;
					map[nextI][nextJ] = map[nowI][nowJ] + 1;
				}
			}

		}
	}
}
