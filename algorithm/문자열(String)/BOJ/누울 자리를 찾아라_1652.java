import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] matrix;

		int N = Integer.parseInt(br.readLine());

		matrix = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				String input = inputs[j];

				if (input.equals(".")) {
					matrix[i][j] = true;
				} else {
					matrix[i][j] = false;
				}
			}
		}

		int rowCount = 0;
		int colCount = 0;
    
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (matrix[j][i]) {
					count++;
				} else {
					if (count > 1) {
						colCount++;
					}
					count = 0;
				}
			}

			if (count > 1) {
				colCount++;
			}
		}

		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (matrix[i][j]) {
					count++;
				} else {
					if (count > 1) {
						rowCount++;
					}
					count = 0;
				}
			}

			if (count > 1) {
				rowCount++;
			}
		}

		System.out.println(rowCount + " " + colCount);
	}
}
