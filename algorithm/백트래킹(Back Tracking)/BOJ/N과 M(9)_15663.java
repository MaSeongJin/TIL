// https://www.acmicpc.net/problem/15663
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[] arr;
	public static boolean[] visited;
	public static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new boolean[N];
		result = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		perm(0);
	}

	public static void perm(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		int before = 0;
		for (int i = 0; i < N; i++) {
			if(visited[i]) {
				continue;
			}
			if(before != arr[i]) {
				visited[i] = true;
				result[depth] = arr[i];
				before = arr[i];
				perm(depth + 1);
				visited[i] = false;
				
			}
		}
	}

}
