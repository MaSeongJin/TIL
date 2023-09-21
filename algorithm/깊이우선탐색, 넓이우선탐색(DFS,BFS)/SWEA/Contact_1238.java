// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static int N, start;
	public static int[][] node;
	public static boolean[] visited;
	public static int contact;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			node = new int[101][101];
			visited = new boolean[101];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				node[a][b] = 1;
			}
			System.out.println("#" + tc + " " + bfs(start));
		}
	}

	private static int bfs(int v) {
		Queue<Integer> que = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<>();
		que.add(v);
		while(!que.isEmpty()) {
			int r = que.size();
			contact = 0;
			for (int round = 0; round < r; round++) {
				int now = que.poll();
				for (int i = 1; i < node[now].length; i++) {
					if(node[now][i] == 1 && !visited[i]) {
						que.add(i);
						visited[i] = true;
						contact = Math.max(contact, i);
						stack.add(contact);
					}
				}
			}
		}
		return stack.pop();
	}

}
