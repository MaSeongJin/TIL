import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= t; tc++) {
 
            int N = Integer.parseInt(br.readLine());
            char[][] arr = new char[N + 2][N + 2];
 
            for (int i = 1; i <= N; i++) {
                String str = br.readLine();
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = str.charAt(j - 1);
                }
            }
 
            int[] iDir = { -1, -1, -1, 0, 1, 1, 1, 0 };
            int[] jDir = { -1, 0, 1, 1, 1, 0, -1, -1 };
 
            String ans = null;
            a: for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    for (int j2 = 0; j2 < jDir.length; j2++) {
                        int cnt = 0;
                        int st = i;
                        int ed = j;
                        while (arr[st][ed] == 'o') {
                            cnt++;
                            st += iDir[j2];
                            ed += jDir[j2];
                        }
                        if (cnt >= 5) {
                            ans = "YES";
                            break a;
                        } else {
                            ans = "NO";
                        }
                    }
                }
            }
 
            System.out.println("#" + tc + " " + ans);
 
        }
 
    }
 
}
