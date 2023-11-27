import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[][] arrN;
	public static int[][] arrM;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arrN = new int[N][2];
		arrM = new int[M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arrN[i][0] = Integer.parseInt(st.nextToken());
			arrN[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			arrM[i][0] = Integer.parseInt(st.nextToken());
			arrM[i][1] = Integer.parseInt(st.nextToken());
		}

		bfs();

	}

	public static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		boolean[] visited = new boolean[106];
		que.add(new int[] { 1, 0 });
		visited[1] = true;

		while (!que.isEmpty()) {
			int[] arrNow = que.poll();
			int now = arrNow[0];
			int cnt = arrNow[1];
			if (now >= 100) {
				System.out.println(cnt);
				break;
			}
			for (int i = 6; i > 0; i--) {
				for (int j = 0; j < N; j++) {
					if ((now + i) == arrN[j][0] && !visited[now + i]) {
						que.add(new int[] { arrN[j][1], cnt + 1 });
						visited[arrN[j][1]] = true;
						visited[now + i] = true;
					}
				}
			}
			for (int i = 6; i > 0; i--) {
				for (int j = 0; j < M; j++) {
					if ((now + i) == arrM[j][0] && !visited[now + i]) {
						que.add(new int[] { arrM[j][1], cnt + 1 });
						visited[arrM[j][1]] = true;
						visited[now + i] = true;
					}
				}
			}
			for (int i = 6; i > 0; i--) {
				if (!visited[now + i]) {
					que.add(new int[] { now + i, cnt + 1 });
					visited[now + i] = true;
				}
			}
		}
	}

}
