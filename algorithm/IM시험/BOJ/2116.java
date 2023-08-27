// https://www.acmicpc.net/problem/2116
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[N][6];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int bottomIdx = 0;
		int topIdx = 0;
		int sum = 0;
		int max = 0;
		for (int round = 0; round < 6; round++) {
			bottomIdx = round;
			sum = 0;
			for (int i = 0; i < N; i++) {
				if(bottomIdx == 0) topIdx = 5;
				else if(bottomIdx == 1) topIdx = 3;
				else if(bottomIdx == 2) topIdx = 4;
				else if(bottomIdx == 3) topIdx = 1;
				else if(bottomIdx == 4) topIdx = 2;
				else if(bottomIdx == 5) topIdx = 0;
				if(arr[i][bottomIdx] != 6 && arr[i][topIdx] != 6) {
					sum += 6;
				} else if(arr[i][bottomIdx] != 5 && arr[i][topIdx] != 5) {
					sum += 5;
				} else {
					sum += 4;
				}
				for (int j = 0; j < 6; j++) {
					if(i < N - 1) {
						if(arr[i][topIdx] == arr[i+1][j]) {
							bottomIdx = j;
						}
					}
				}
			}
			if(max < sum) {
				max = sum;
			}
		}
		
		System.out.println(max);
	}
}
