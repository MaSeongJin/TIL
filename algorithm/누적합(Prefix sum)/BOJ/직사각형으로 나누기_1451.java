import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int[] divide;
    static int[][] num;
    static long[][] sum;
    static long answer = 0;
    static long[] temp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N][M];
        sum = new long[N][M];
        temp = new long[3];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                num[i][j] = s.charAt(j) - '0';

                if (i == 0 && j == 0) {
                    sum[0][0] = num[0][0];
                } else if (i == 0) {
                    sum[0][j] = sum[0][j - 1] + num[i][j];
                } else if (j == 0) {
                    sum[i][0] = sum[i - 1][0] + num[i][j];
                }
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + num[i][j];
            }
        }

        divide = new int[2];
        divideRow(0, 0);
        divideCol(0, 0);
        dividePoint();

        System.out.println(answer);
    }
    
    public static long findSum(int x1, int y1, int x2, int y2) {
        if (x1 == 0 && y1 == 0) {
            return sum[y2][x2];
        } else if (x1 == 0) {
            return sum[y2][x2] - sum[y1 - 1][x2];
        } else if (y1 == 0) {
            return sum[y2][x2] - sum[y2][x1 - 1];
        }

        return sum[y2][x2] - sum[y2][x1 - 1] - sum[y1 - 1][x2] + sum[y1 - 1][x1 - 1];
    }

    public static void divideRow(int count, int index) {
        if (count >= 2) {
            temp[0] = findSum(0, 0, M - 1, divide[0] - 1);
            temp[1] = findSum(0, divide[0], M - 1, divide[1] - 1);
            temp[2] = findSum(0, divide[1], M - 1, N - 1);

            answer = Math.max(answer, temp[0] * temp[1] * temp[2]);
            return;
        }

        for (int i = index; i + 1 < N; i++) {
            divide[count] = i + 1;
            divideRow(count + 1, i + 1);
        }
    }

    public static void divideCol(int count, int index) {
        if (count >= 2) {
            temp[0] = findSum(0, 0, divide[0] - 1, N - 1);
            temp[1] = findSum(divide[0], 0, divide[1] - 1, N - 1);
            temp[2] = findSum(divide[1], 0, M - 1, N - 1);

            answer = Math.max(answer, temp[0] * temp[1] * temp[2]);
            return;
        }

        for (int i = index; i + 1 < M; i++) {
            divide[count] = i + 1;
            divideCol(count + 1, i + 1);
        }
    }

    public static void dividePoint() {
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M - 1; j++) {
                // ㅗ 모양
                temp[0] = findSum(0, 0, j - 1, i - 1);
                temp[1] = findSum(j, 0, M - 1, i - 1);
                temp[2] = findSum(0, i, M - 1, N - 1);
                answer = Math.max(answer, temp[0] * temp[1] * temp[2]);

                // ㅜ 모양
                temp[0] = findSum(0, 0, M - 1, i - 1);
                temp[1] = findSum(0, i, j - 1, N - 1);
                temp[2] = findSum(j, i, M - 1, N - 1);
                answer = Math.max(answer, temp[0] * temp[1] * temp[2]);

                // ㅏ 모양
                temp[0] = findSum(0, 0, j - 1, N - 1);
                temp[1] = findSum(j, 0, M - 1, i - 1);
                temp[2] = findSum(j, i, M - 1, N - 1);
                answer = Math.max(answer, temp[0] * temp[1] * temp[2]);

                // ㅓ 모양
                temp[0] = findSum(0, 0, j - 1, i - 1);
                temp[1] = findSum(0, i, j - 1, N - 1);
                temp[2] = findSum(j, 0, M - 1, N - 1);
                answer = Math.max(answer, temp[0] * temp[1] * temp[2]);
            }
        }
    }

}
