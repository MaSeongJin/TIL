// https://www.acmicpc.net/problem/11279
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp != 0) {
				pq.add(tmp * -1);
			} else {
				int res;
				if(pq.peek() == null) {
					res = 0;
				} else {
					res = pq.poll() * -1;
				}
				sb.append(res).append("\n");
			}
		}
		System.out.println(sb);
	}
}
