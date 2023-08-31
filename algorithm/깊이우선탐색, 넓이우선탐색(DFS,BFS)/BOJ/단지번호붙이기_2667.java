// https://www.acmicpc.net/problem/2667
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static int N;
	public static int[][] node;
	public static boolean[][] visited;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static ArrayList<Integer> cnts = new ArrayList<>();
	public static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		node = new int[N + 2][N + 2];
		visited = new boolean[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= N; j++) {
				node[i][j] = str.charAt(j-1) - '0';
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (!visited[i][j] && node[i][j] == 1) {
					cnt = 1;
					visited[i][j] = true;
					bfs(i, j);
					cnts.add(cnt);
				}
			}
		}
		
		Collections.sort(cnts);
		
		System.out.println(cnts.size());
		for (int i = 0; i < cnts.size(); i++) {
			System.out.println(cnts.get(i));
		}
		
	}

	public static void bfs(int x, int y) {
		Queue<int[]> que = new LinkedList<>();
		if(node[x][y] == 1) {
			que.offer(new int[] {x, y});
		}
		while(!que.isEmpty()) {
			int[] start = que.poll();
			int startI = start[0];
			int startJ = start[1];
			for (int i = 0; i < 4; i++) {
				int nextI = startI + iDir[i];
				int nextJ = startJ + jDir[i];
				if(node[nextI][nextJ] == 1 && !visited[nextI][nextJ]) {
					visited[nextI][nextJ] = true;
					que.offer(new int[] {nextI, nextJ});
					cnt++;
				}
			}
		}
	}

}
