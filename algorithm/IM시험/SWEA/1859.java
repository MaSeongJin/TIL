// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] prices = new int[N];
			for (int i = 0; i < prices.length; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			long price = 0;
			int max = prices[prices.length - 1];

			for (int i = prices.length - 1; i >= 0; i--) {
				if(max > prices[i]) {
					price += max - prices[i];
				} else {
					max = prices[i];
				}
			}

			System.out.println("#" + tc + " " + price);
		}
	}

}
