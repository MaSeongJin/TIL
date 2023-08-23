import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int M, N, K;
	public static int[][] arr;
	public static boolean[][] visit;
	public static int count;
	public static int[] dx = { 0, -1, 0, 1 };
	public static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			count = 0;
			arr = new int[M][N];
			visit = new boolean[M][N];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[a][b] = 1;
			}
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j] == 1 && !visit[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
			
			System.out.println(count);
			
		}
		
	}
	
	public static void dfs(int i, int j) {
		visit[i][j] = true;
		for (int j2 = 0; j2 < 4; j2++) {
			int id = i + dx[j2];
			int jd = j + dy[j2];
			
			if(id >= 0 && jd >= 0 && id < M && jd < N) {
				if(!visit[id][jd] && arr[id][jd] == 1) {
					dfs(id, jd);
				}
			}
			
		}
	}
	
	public static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {i, j});
		
		while(!queue.isEmpty()) {
			i = queue.peek()[0];
			j = queue.peek()[1];
			visit[i][j] = true;
			queue.poll();
			for (int k = 0; k < 4; k++) {
				int id = i + dx[k];
				int jd = j + dy[k];
				
				if(id >= 0 && jd >= 0 && id < M && jd < N) {
					if(!visit[id][jd] && arr[id][jd] == 1) {
						queue.add(new int[] {id, jd});
						visit[id][jd] = true;
					}
				}
				
			}
		}
	}
}
