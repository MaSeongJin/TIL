import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static char[][] arr; // 글자판
	static final int LENGTH = 100;

	public static boolean solve(int l) {
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j <= (LENGTH - l); j++) {
				if (solveRow(i, j, l) || solveColumn(j, i, l))
					return true;
			}
		}

		return false;
	}

	// 가로 탐색
	public static boolean solveRow(int i, int j, int l) {
		for (int k = 0; k < l / 2; k++) {
			if (arr[i][j + k] != arr[i][j + l - 1 - k])
				return false;
		}
		return true;
	}

	// 세로 탐색
	public static boolean solveColumn(int i, int j, int l) {
		for (int k = 0; k < l / 2; k++) {
			if (arr[i + k][j] != arr[i + l - 1 - k][j])
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test = 0; test < 10; test++) {
			int t = Integer.parseInt(br.readLine()); // 테스트 케이스 번호
			arr = new char[LENGTH][LENGTH];

			// 글자판 입력
			for (int i = 0; i < LENGTH; i++) {
				String str = br.readLine();
				for (int j = 0; j < LENGTH; j++) {
					arr[i][j] = str.charAt(j);
				}
			}

			for (int i = LENGTH; i > 0; i--) {
				if (solve(i)) {
					System.out.println("#" + t + " " + i);
					break;
				}
			}
		}
	}
}
