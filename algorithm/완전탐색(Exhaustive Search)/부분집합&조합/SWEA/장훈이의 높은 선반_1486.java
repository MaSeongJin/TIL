// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static int N, B;
    public static int[] height;
    public static int ans;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            height = new int[N];
 
            ans = Integer.MAX_VALUE;
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }
 
            set(0, 0);
 
            System.out.println("#" + tc + " " + (ans - B));
 
        }
    }
 
    public static void set(int depth, int sum) {
        if (depth == N) {
            if (sum >= B && sum < ans) {
                ans = sum;
            }
            return;
        }
 
        set(depth + 1, sum + height[depth]);
        set(depth + 1, sum);
 
    }
 
}
