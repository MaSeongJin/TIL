import java.io.*;
import java.util.*;

public class Main {

	static class Node{
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	static int answer;
	static List<Node>[] list;
	static int[] cost;
  
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		cost = new int[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Node(v,w));
			list[v].add(new Node(u,w));
		}
        
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			dfs(to, -1, from, 0);
			System.out.println(answer);
		}
	}
	
	static void dfs(int to, int pa, int from, int cost) {
		if(to == from) {
			answer = cost;
		}
		for(Node nxt : list[to]) {
			if(nxt.to != pa) {
				dfs(nxt.to, to, from, cost+nxt.cost);
			}
		}
	}
}
