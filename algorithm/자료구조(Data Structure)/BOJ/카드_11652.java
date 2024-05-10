import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		HashMap <Long, Integer> map = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			long tmp = sc.nextLong();
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);
		}
		
		int max = Integer.MIN_VALUE;
		long  answer = 0;
		for(long x: map.keySet()) {
			if(map.get(x) > max) {
				max = map.get(x);
				answer = x;
			} else if(map.get(x) == max) {
				answer = Math.min(answer, x);
			}	
		}
		
		System.out.println(answer);
		
		sc.close();
	}
}
