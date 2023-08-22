import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K_MIN = Integer.parseInt(st.nextToken());
			int K_MAX = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[] score = new int[N];
			for (int i = 0; i < score.length; i++) {
				score[i] = Integer.parseInt(st.nextToken());
			}
			int[] cnt = new int[101];
			for (int i = 0; i < score.length; i++) {
				cnt[score[i]]++;
			}
			for (int i = 0; i < cnt.length - 1; i++) {
				cnt[i + 1] += cnt[i];
			}
			int scoreSort[] = new int[N];
			for (int i = score.length - 1; i >= 0; i--) {
				int idx = score[i];
				scoreSort[--cnt[idx]] = idx;
			}

			int max = K_MAX - K_MIN;
			int flag = -1;
			for (int T1 = 1; T1 <= 99; T1++) {
				for (int T2 = T1 + 1; T2 <= 100; T2++) {
					int[] classRoom = new int[3];
					for (int i = 0; i < scoreSort.length; i++) {
						if (scoreSort[i] < T1) {
							classRoom[0]++;
						} else if (scoreSort[i] < T2) {
							classRoom[1]++;
						} else if (scoreSort[i] >= T2) {
							classRoom[2]++;
						}
					}
					if (classRoom[0] >= K_MIN && classRoom[1] >= K_MIN && classRoom[2] >= K_MIN && classRoom[0] <= K_MAX
							&& classRoom[1] <= K_MAX && classRoom[2] <= K_MAX) {
						Arrays.sort(classRoom);
						int tmp = classRoom[2] - classRoom[0];
						if (tmp < max) {
							max = tmp;
							flag = 0;
						}
					}
				}
			}

			System.out.print("#" + tc + " ");
			if (flag == 0) {
				System.out.println(max);
			} else {
				System.out.println(flag);
			}

		}
	}
}
