import java.io.*;

public class Main {

    static int N;
    static int[] dp;
    static int[] trace;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        trace = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                trace[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                trace[i] = i / 2;
            }
            if (i - 1 > 0 && dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                trace[i] = i - 1;
            }
        }

        bw.write(String.valueOf(dp[N]) + '\n');
        int num = N;
        for (int i = 0; i <= dp[N]; i++) {
            bw.write(String.valueOf(num) + " ");
            num = trace[num];
        }

        bw.flush();
    }
}
