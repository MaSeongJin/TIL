// https://www.acmicpc.net/problem/2559
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		for (int i = 1; i <= K; i++) {
			sum += arr[i];
		}
		int max = sum;
		for (int i = K; i < N; i++) {
			sum -= arr[i - K + 1];
			sum += arr[i + 1];
			if (max < sum) {
				max = sum;
			}
		}

		System.out.println(max);

	}

}
