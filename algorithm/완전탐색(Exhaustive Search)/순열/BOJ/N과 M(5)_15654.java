// https://www.acmicpc.net/problem/15654
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[] seq;
	public static int[] result;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		seq = new int[N];
		result = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(seq);
		perm(0);
	}

	private static void perm(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) {
				continue;
			}
			result[depth] = seq[i];
			visited[i] = true;
			perm(depth + 1);
			visited[i] = false;
		}
	}
}
