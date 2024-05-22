import java.io.*;
import java.util.*;

public class Main {
    static int max = 1, N = 0;
    static char[][] arr;

    public static void swap(int x1, int y1, int x2, int y2) {
        char tmp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = tmp;
    }

    public static int chkRow(int x) {
        int tmp = 1, res = 1;
        char ch = arr[x][0];
        for (int i = 1; i < N; i++) {
            if (arr[x][i] != ch) {
                ch = arr[x][i];
                res = Math.max(res, tmp);
                tmp = 1;
            } else tmp++;
        }
        return Math.max(res, tmp);
    }

    public static int chkCol(int y) {
        int tmp = 1, res = 1;
        char ch = arr[0][y];
        for (int i = 1; i < N; i++) {
            if (arr[i][y] != ch) {
                ch = arr[i][y];
                res = Math.max(res, tmp);
                tmp = 1;
            } else tmp++;
        }
        return Math.max(res, tmp);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            String tmp = sc.next();
            for (int j = 0; j < N; j++) arr[i][j] = tmp.charAt(j);
            max = Math.max(max, chkRow(i));
        }

        for (int i = 0; i < N; i++) max = Math.max(max, chkCol(i));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j + 1 < N) {
                    swap(i, j, i, j + 1);
                    max = Math.max(max, chkRow(i));
                    max = Math.max(max, chkCol(j));
                    max = Math.max(max, chkCol(j + 1));
                    swap(i, j, i, j + 1);
                }
                if (i + 1 < N) {
                    swap(i, j, i + 1, j);
                    max = Math.max(max, chkRow(i));
                    max = Math.max(max, chkRow(i + 1));
                    max = Math.max(max, chkCol(j));
                    swap(i, j, i + 1, j);
                }
            }
        }
        System.out.println(max);
    }
}
