import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] ns = new int[n + 2];
        ns[0] = 0;
        ns[n + 1] = l;

        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                ns[i] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(ns);

        int left = 1;
        int right = l - 1;

        int ans = 0;

        while (left <= right) {
            int dist = (left + right) / 2;
            int cnt = countRest(dist, ns);

            if (cnt > m) {
                left = dist + 1;
            } else {
                right = dist - 1;
                ans = dist;
            }
        }
        System.out.print(ans);
    }

    static int countRest(int dist, int[] ns) {
        int cnt = 0;
        for (int i = 0; i <= ns.length - 2; i++) {
            int tempDist = ns[i + 1] - ns[i];
            cnt += (tempDist) / dist;

            if (tempDist % dist == 0)
                cnt--;
        }
        return cnt;
    }
}
