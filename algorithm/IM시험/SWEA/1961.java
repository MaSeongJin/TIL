// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pq-OKAVYDFAUq
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < arr.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < arr.length; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int idx = 0; idx < arr.length; idx++) {
				for (int i = N - 1; i >= 0; i--) {
					sb.append(arr[i][idx]);
				}
				sb.append(" ");
				for (int i = 0; i < arr.length; i++) {
					sb.append(arr[N-1-idx][N-1-i]);
				}
				sb.append(" ");
				for (int i = 0; i < arr.length; i++) {
					sb.append(arr[i][N-1-idx]);
				}
				sb.append("\n");
			}
			sb.delete(sb.length()-1, sb.length());
			System.out.println("#" + tc + "\n" + sb);
			
		}
	}

}
