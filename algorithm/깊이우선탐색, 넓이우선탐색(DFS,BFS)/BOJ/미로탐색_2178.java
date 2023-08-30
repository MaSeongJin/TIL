// https://www.acmicpc.net/problem/2178
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[][] node;
	public static boolean[][] visited;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		node = new int[N + 2][M + 2];
		visited = new boolean[N + 2][M + 2];

		String str;
		for (int i = 1; i <= N; i++) {
			str = br.readLine();
			for (int j = 1; j <= M; j++) {
				node[i][j] = str.charAt(j - 1) - '0';
			}
		}

		visited[1][1] = true;
		
		bfs(1, 1);
		
		System.out.println(node[N][M]);
		
	}

	public static void bfs(int x, int y) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { x, y });

		while(!que.isEmpty()) {
			int tmp[] = que.poll();
			int startI = tmp[0];
			int startJ = tmp[1];
			for (int i = 0; i < 4; i++) {
				int nextI = startI + iDir[i];
				int nextJ = startJ + jDir[i];
				if(!visited[nextI][nextJ] && node[nextI][nextJ] == 1) {
					visited[nextI][nextJ] = true;
					que.offer(new int[] {nextI, nextJ});
					node[nextI][nextJ] = node[startI][startJ] + 1;
				}
			}
		}
	}
}
