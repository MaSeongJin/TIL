import java.util.*;
import java.io.*;

public class Main {

    static int K;
    static int[] dp;
    static int[] coin;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        K = sc.nextInt();
        coin = new int[N];
        dp = new int[K + 1];
        Arrays.fill(dp, 100001);

        for (int i = 0; i < N; i++) {
            coin[i] = sc.nextInt();
        }

        solve();
    }

    public static void solve() {
        dp[0] = 0;

        for (int c : coin) {
            for (int i = c; i <= K; i++) {
                dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }

        if (dp[K] == 100001) {
            dp[K] = -1;
        }
        System.out.println(dp[K]);
    }
}
