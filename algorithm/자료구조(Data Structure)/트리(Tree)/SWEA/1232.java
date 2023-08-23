// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141J8KAIcCFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static String[] arr;
	public static int[][] tree;
	public static int N;
	public static Stack<Double> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N+1][2];
			arr = new String[N + 1];
			stack = new Stack<>();
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr[Integer.parseInt(st.nextToken())] = st.nextToken();
				while (st.hasMoreTokens()) {
					tree[i][0] = Integer.parseInt(st.nextToken());
					tree[i][1] = Integer.parseInt(st.nextToken());
				}
			}
			postOrder(1);

			System.out.println("#" + tc + " " + String.format("%.0f", stack.peek()));
		}
	}

	public static void postOrder(int i) {
		if (i != 0) {
			postOrder(tree[i][0]);
			postOrder(tree[i][1]);
			if (arr[i].equals("-") || arr[i].equals("+") || arr[i].equals("*") || arr[i].equals("/")) {
				cal(arr[i]);
			} else {
				stack.push(Double.parseDouble(arr[i]));
			}
		}
	}

	public static void cal(String key) {
		double b = stack.pop();
		double a = stack.pop();

		if (key.equals("-")) {
			stack.push(a - b);
		} else if (key.equals("+")) {
			stack.push(a + b);
		} else if (key.equals("*")) {
			stack.push(a * b);
		} else if (key.equals("/")) {
			stack.push(a / b);
		}
	}

}
