// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18TrIqIwUCFAZN
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static int V, E;
	public static int[][] node;
	public static boolean[] visited;
	public static ArrayList<ArrayList<Integer>> graph;
	public static int[] edgeCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeCnt = new int[V + 1];
			graph = new ArrayList<>();
			for (int i = 0; i < edgeCnt.length; i++) {
				graph.add(new ArrayList<>());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				edgeCnt[b]++;
			}
			System.out.print("#" + tc + " ");
			topologicalSort();
			System.out.println();
		}
	}

	private static void topologicalSort() {
		Queue<Integer> que = new LinkedList<>();
		for (int i = 1; i < edgeCnt.length; i++) {
			if (edgeCnt[i] == 0) {
				que.add(i);
			}
		}
		while (!que.isEmpty()) {
			int node = que.poll();
			System.out.print(node + " ");
			List<Integer> list = graph.get(node);
			for (int i = 0; i < list.size(); i++) {
				edgeCnt[list.get(i)]--;
				if (edgeCnt[list.get(i)] == 0) {
					que.add(list.get(i));
				}
			}
		}

	}

}
