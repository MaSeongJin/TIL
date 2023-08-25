// https://www.acmicpc.net/problem/1074
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, r, c;
	public static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		recur((int) Math.pow(2, N), 0, 0);
		System.out.println(cnt);
	}

 //  private static void find(int size, int r, int c) {
	// 	if(size == 1)
	// 		return;
		
	// 	if(r < size/2 && c < size/2) {
	// 		find(size/2, r, c);
	// 	}
	// 	else if(r < size/2 && c >= size/2) {
	// 		count += size * size / 4;
	// 		find(size/2, r, c - size/2);
	// 	}
	// 	else if(r >= size/2 && c < size/2) {
	// 		count += (size * size / 4) * 2;
	// 		find(size/2, r - size/2, c);
	// 	}
	// 	else {
	// 		count += (size * size / 4) * 3;
	// 		find(size/2, r - size/2, c - size/2);
	// 	}
	// }
  
	public static void recur(int n, int x, int y) {
		if (x == r && y == c) {
			return;
		}
		if (n == 1) {
			cnt++;
			return;
		}
		if (x <= r && y <= c && x + n / 2 > r && y + n / 2 > c) {
			recur(n / 2, x, y);
			return;
		} else {
			cnt+= n/2 * n/2;
		}
		if (x <= r && y + n / 2 <= c && x + n / 2 > r && y + n > c) {
			recur(n / 2, x, y + n / 2);
			return;
		} else {
			cnt+= n/2 * n/2;
		}
		if (x + n / 2 <= r && y <= c && x + n > r && y + n / 2 > c) {
			recur(n / 2, x + n / 2, y);
			return;
		} else {
			cnt+= n/2 * n/2;
		}
		if (x + n / 2 <= r && y + n / 2 <= c && x + n > r && y + n > c) {
			recur(n / 2, x + n / 2, y + n / 2);
			return;
		}
	}

}
