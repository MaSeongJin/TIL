import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int result = 0;
        int left = 0;
        int right = arr[N - 1] - arr[0];

        while (left <= right) {
            int cnt = 1;
            int cur = arr[0];

            int mid = (right + left) / 2;

            for (int i = 1; i < N; i++) {
                if (arr[i] - cur >= mid) {
                    cnt++;
                    cur = arr[i];
                }
            }

            if (cnt >= C) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }
}
