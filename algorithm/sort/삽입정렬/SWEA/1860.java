import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			String str = "Possible";
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i < arr.length; i++) {
				int key = arr[i];
				int j;
				for (j = i - 1; j >= 0 && key < arr[j]; j--) {
					arr[j + 1] = arr[j];
				}
				arr[j + 1] = key;
			}
			int cnt = 0;
			int idx = 0;
			if (arr[0] == 0) {
				str = "Impossible";
			} else {
				for (int i = 1; i <= arr[N - 1]; i++) {
					if (i % M == 0) {
						cnt += K;
					}
					if (idx < N && i == arr[idx]) {
						idx++;
						if (cnt > 0) {
							cnt--;
						} else {
							str = "Impossible";
							break;
						}
					}
				}
			}

			System.out.println("#" + tc + " " + str);
		}
	}
}
