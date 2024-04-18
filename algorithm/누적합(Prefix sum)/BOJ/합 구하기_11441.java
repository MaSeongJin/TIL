import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int[] cumSum = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			cumSum[i] = Integer.parseInt(st.nextToken()) + cumSum[i - 1];
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			sb.append((cumSum[to] - cumSum[from - 1]) + "\n");
		}
		System.out.println(sb.toString());
	}

}
