import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int n = 0;
			switch (str) {
			case "add":
				n = Integer.parseInt(st.nextToken());
				if(queue.contains(n)) {
					break;
				} else {
					queue.add(n);
				}
				break;
			case "remove":
				n = Integer.parseInt(st.nextToken());
				if(queue.contains(n)) {
					queue.remove(n);
				} 
				break;
			case "check":
				n = Integer.parseInt(st.nextToken());
				if(queue.contains(n)) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
				break;
			case "toggle":
				n = Integer.parseInt(st.nextToken());
				if(queue.contains(n)) {
					queue.remove(n);
				} else {
					queue.add(n);
				}
				break;
			case "all":
				queue.clear();
				for (int j = 1; j <= 20; j++) {
					queue.add(j);
				}
				break;
			case "empty":
				queue.clear();
			}
			
		}
		
		System.out.println(sb);
	}
}
