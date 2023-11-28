import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int T, A, B;
	public static int[] arrA;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			arrA = new int[4];

			for (int i = 3; i >= 0; i--) {
				arrA[i] = A % 10;
				A /= 10;
			}
			
			bfs();

		}

	}

	public static void bfs() {
		Queue<Node> que = new LinkedList<>();
		boolean[][][][] visited = new boolean[10][10][10][10];
		que.add(new Node(arrA, ""));
		visited[arrA[0]][arrA[1]][arrA[2]][arrA[3]] = true;

		while (!que.isEmpty()) {

			Node node = que.poll();
			int[] now = node.arrA;
			String str = node.str;

			int numA = (((now[0] * 10 + now[1]) * 10 + now[2]) * 10 + now[3]);

			if (numA == B) {
				System.out.println(str);
				break;
			}
			
			int[] tmp = new int[4];

			// D
			int D = numA * 2 % 10000;

			for (int i = 3; i >= 0; i--) {
				tmp[i] = D % 10;
				D /= 10;
			}
			if (!visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]]) {
				que.add(new Node(tmp, str + "D"));
				visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]] = true;
			}

			// S
			tmp = new int[4];
			int S = numA - 1;
			if (S < 0) {
				S = 9999;
			}
			for (int i = 3; i >= 0; i--) {
				tmp[i] = S % 10;
				S /= 10;
			}
			if (!visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]]) {
				que.add(new Node(tmp, str + "S"));
				visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]] = true;
			}

			// L
			tmp = new int[4];
			for (int i = 0; i < 4; i++) {
				tmp[i] = now[(i + 1) % 4];
			}
			if (!visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]]) {
				que.add(new Node(tmp, str + "L"));
				visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]] = true;
			}
			
			// R
			tmp = new int[4];
			for (int i = 0; i < 4; i++) {
				if(i == 0) {
					tmp[i] = now[3];
					continue;
				}
				tmp[i] = now[i - 1];
			}
			if (!visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]]) {
				que.add(new Node(tmp, str + "R"));
				visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]] = true;
			}

		}
	}

	public static class Node {
		public int[] arrA;
		public String str;

		public Node(int[] arrA, String str) {
			this.arrA = arrA;
			this.str = str;
		}
	}

}
