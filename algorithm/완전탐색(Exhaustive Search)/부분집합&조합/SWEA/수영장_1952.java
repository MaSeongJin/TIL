// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int[] money;
	public static int[] plan;
	public static int[] plans;
	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			money = new int[4];
			plan = new int[12];
			plans = new int[12];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				money[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			result = Integer.MAX_VALUE;
			dfs(0);
			System.out.println("#" + tc + " " + result);
		}
	}

	public static void dfs(int month) {
		if (month >= 12) {
			result = Math.min(result, calc());
			return;
		}
		plans[month] = 1;
		dfs(month + 1);
		plans[month] = 30;
		dfs(month + 1);
		plans[month] = 3;
		dfs(month + 3);
		plans[month] = 12;
		dfs(month + 12);
		plans[month] = 0;
	}

	private static int calc() {
		int tmp = 0;
		for (int i = 0; i < 12; i++) {
			if (plans[i] == 1) {
				tmp += money[0] * plan[i];
			} else if (plans[i] == 30) {
				tmp += money[1];
			} else if (plans[i] == 3) {
				tmp += money[2];
			} else if (plans[i] == 12) {
				tmp += money[3];
			}
		}
		return tmp;
	}

}
