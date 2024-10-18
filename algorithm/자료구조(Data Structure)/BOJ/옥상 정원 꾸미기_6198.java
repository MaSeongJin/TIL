import java.io.*;
import java.util.*;

public class Main {

	public static Stack<Integer> stack = new Stack<>();
	public static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			
			int height = Integer.parseInt(br.readLine());
			
			while(!stack.isEmpty()) {
				
				if(stack.peek() <= height) {
					stack.pop();
				}
				else break;
			}
			ans += stack.size();
			stack.push(height);
		}
		bw.write(String.valueOf(ans));
		bw.close();
	}
}
