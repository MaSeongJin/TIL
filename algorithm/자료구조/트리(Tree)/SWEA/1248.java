// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15PTkqAPYCFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static String[] arr;
	public static int[][] tree;
	public static Stack<Double> stack;
	public static int check;
	public static int a;
	public static int b;
	public static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			tree = new int[V + 1][2];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= E; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (tree[x][0] == 0) {
					tree[x][0] = y;
				} else {
					tree[x][1] = y;
				}
			}
			int max = 0;
			int minCnt = Integer.MAX_VALUE;
			check = 0;
			for (int i = 1; i <= V; i++) {
				find(i);
				if (check == 2) {
					if (cnt < minCnt) {
						max = i;
						minCnt = cnt;
					}
				}
				check = 0;
				cnt = 0;
			}
			cnt = 0;
			find(max);
			System.out.println("#" + tc + " " + max + " " + cnt);
		}
	}

	public static void find(int i) {
		if (i == a || i == b) {
			check++;
		}
		if (i != 0) {
			cnt++;
			find(tree[i][0]);
			find(tree[i][1]);
		}
	}

}
