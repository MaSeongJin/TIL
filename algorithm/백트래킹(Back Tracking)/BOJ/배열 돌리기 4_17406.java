// https://www.acmicpc.net/problem/17406
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int M, N, K;
	public static int[][] map;
	public static int[][] copyMap;
	public static int[] iDir = { 0, 1, 0, -1 };
	public static int[] jDir = { 1, 0, -1, 0 };
	public static int[][] info;
	public static int[] order;
	public static boolean[] visited;
	public static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copyMap = new int[N][M];
		info = new int[K][3];
		order = new int[K];
		visited = new boolean[K];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0);
		System.out.println(min);
	}
	
	public static void perm(int depth) {
		if(depth == K) {
			copy();
			for (int i = 0; i < K; i++) {
				rotate(info[order[i]][0], info[order[i]][1], info[order[i]][2]);
			}
			findMax();
			return;
		}
		for (int i = 0; i < K; i++) {
			if(visited[i]) {
				continue;
			}
			visited[i] = true;
			order[depth] = i;
			perm(depth+1);
			visited[i] = false;
		}
	}
	
	public static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}

	public static void rotate(int r, int c, int s) {
		// 1, 8, 16, 24...순으로 증가
		int round = 8;
		for (int i = 1; i <= s; i++) { // 회전시킬 둘레 수
			int startI = r - i - 1;
			int startJ = c - i - 1;
			int tmpA = copyMap[startI][startJ];
			int tmpB;
			for (int j = 1; j <= 4; j++) {
				int nextI, nextJ;
				for (int k = 1; k <= round / 4; k++) {
					nextI = startI + iDir[j - 1];
					nextJ = startJ + jDir[j - 1];
					tmpB = copyMap[nextI][nextJ];
					copyMap[nextI][nextJ] = tmpA;
					tmpA = tmpB;
					startI = nextI;
					startJ = nextJ;
				}
			}
			round += 8;
		}
	}

	public static void findMax() {
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += copyMap[i][j];
				if(sum > min) {
					break;
				}
			}
			min = Math.min(min, sum);
		}
	}

}
