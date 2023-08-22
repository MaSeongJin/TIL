import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            char[][] arr = new char[N + 6][N + 6];
            for (int i = 3; i < arr.length - 3; i++) {
                String str = br.readLine();
                for (int j = 3; j < arr.length - 3; j++) {
                    arr[i][j] = str.charAt(j - 3);
                }
            }
            int cnt = 0;
            int[] iDir = { -1, 0, 1, 0 };
            int[] jDir = { 0, 1, 0, -1 };
            for (int i = 3; i < arr.length - 3; i++) {
                for (int j = 3; j < arr.length - 3; j++) {
                    cnt = 0;
                    if (arr[i][j] == 'A') {
                        cnt = 1;
                    } else if (arr[i][j] == 'B') {
                        cnt = 2;
                    } else if (arr[i][j] == 'C') {
                        cnt = 3;
                    }
                    for (int j2 = 0; j2 < jDir.length; j2++) {
                        for (int k = 1; k <= cnt; k++) {
                            if (arr[i + iDir[j2] * k][j + jDir[j2] * k] == 'H') {
                                arr[i + iDir[j2] * k][j + jDir[j2] * k] = 'X';
                            }
                        }
                    }
                }
            }
            int result = 0;
            for (int i = 3; i < arr.length - 3; i++) {
                for (int j = 3; j < arr.length - 3; j++) {
                    if (arr[i][j] == 'H') {
                        result++;
                    }
                }
            }
 
            System.out.println("#" + tc + " " + result);
 
        }
    }
}
