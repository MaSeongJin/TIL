// https://www.acmicpc.net/problem/1629
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static long A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		A %= C;

		System.out.println(pow(A, B) % C);
	}

	public static long pow(long a, long b) {
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		long tmp = pow(a, b / 2) % C;
		if (b % 2 == 0) {
			return tmp * tmp % C;
		} else {
			return tmp * tmp % C * a % C;
		}
	}
}
