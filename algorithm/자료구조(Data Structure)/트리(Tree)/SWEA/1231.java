// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV140YnqAIECFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static String[] arr;
	public static int N;
	public static String result = "";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new String[N + 1];
			for (int i = 1; i < arr.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr[Integer.parseInt(st.nextToken())] = st.nextToken();
				while (st.hasMoreTokens()) {
					st.nextToken();
				}
			}
			inOrder(1);
			System.out.println("#" + tc + " " + result);
			result = "";
		}
	}

	public static void inOrder(int i) {
		if (i <= N) {
			inOrder(i * 2);
			result += arr[i];
			inOrder(i * 2 + 1);
		}
	}

}
