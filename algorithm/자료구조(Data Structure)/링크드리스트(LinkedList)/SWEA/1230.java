import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			LinkedList<Integer> list = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.addLast(Integer.parseInt(st.nextToken()));
			}
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int x, y;
			for (int i = 0; i < M; i++) {
				String choice = st.nextToken();
				switch (choice) {
				case "I":
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						list.add(x, Integer.parseInt(st.nextToken()));
						x++;
					}
					break;
				case "D":
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						list.remove(x);
					}
					break;
				case "A":
					y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						list.addLast(Integer.parseInt(st.nextToken()));
					}
					break;
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 10; i++) {
				sb.append(list.pollFirst()).append(" ");
			}
			System.out.println("#" + tc + " " + sb);

		}
	}
}
