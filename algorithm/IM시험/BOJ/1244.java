import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N + 1];

		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) {
				for (int j = b; j < arr.length; j += b) {
					if (arr[j] == 1) {
						arr[j] = 0;
					} else {
						arr[j] = 1;
					}
				}
			} else {
				int x = -1;
				int y = 1;
				if (arr[b] == 1) {
					arr[b] = 0;
				} else {
					arr[b] = 1;
				}
				while (b + x > 0 && b + y <= N && arr[b + x] == arr[b + y]) {
					if (arr[b + x] == 1) {
						arr[b + x] = 0;
						arr[b + y] = 0;
					} else {
						arr[b + x] = 1;
						arr[b + y] = 1;
					}
					x--;
					y++;
				}

			}
		}
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for (int i = 1; i < arr.length; i++) {
			cnt++;
			sb.append(arr[i]).append(" ");
			if(cnt % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
