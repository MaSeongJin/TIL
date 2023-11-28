import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static char[][] arr;
	public static boolean[][] visited;
	public static int numA, numB;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		visited = new boolean[N][N];
		numA = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					numA++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j] == 'R') {
					arr[i][j] = 'G';
				}
			}
		}
		
		visited = new boolean[N][N];
		numB = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					numB++;
				}
			}
		}
		
		System.out.println(numA + " " + numB);
		
	}

	public static void dfs(int nowI, int nowJ) {
		visited[nowI][nowJ] = true;
		for (int i = 0; i < 4; i++) {
			int nextI = nowI + iDir[i];
			int nextJ = nowJ + jDir[i];
			if (nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < N && !visited[nextI][nextJ]) {
				if (arr[nowI][nowJ] == arr[nextI][nextJ]) {
					dfs(nextI, nextJ);
				}
			}
		}
	}

}
