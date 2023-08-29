// https://www.acmicpc.net/problem/1697
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	static int visited[] = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int result = bfs(N);
		System.out.println(result);
	}

	private static int bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(v);
		int n = 0;
		visited[v] = 1;
		while (!queue.isEmpty()) {
			n = queue.poll();

			if (n == K) {
				return visited[n] - 1;
			}

			if (n - 1 >= 0 && visited[n - 1] == 0) {
				visited[n - 1] = visited[n] + 1;
				queue.add(n - 1);
			}
			if (n + 1 <= 100000 && visited[n + 1] == 0) {
				visited[n + 1] = visited[n] + 1;
				queue.add(n + 1);
			}
			if (2 * n <= 100000 && visited[2 * n] == 0) {
				visited[2 * n] = visited[n] + 1;
				queue.add(2 * n);
			}
		}
		return -1;
	}
}
