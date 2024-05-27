import java.io.*;
import java.util.*;

public class Main {
    static int result = Integer.MIN_VALUE;
    static int N;
    static int[] arr, selected;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        selected = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(result);
    }

    public static void dfs(int count) {
        if (count == N) {
            result = Math.max(result, getResult());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[count] = arr[i];
                dfs(count + 1);
                visited[i] = false;
            }
        }
    }

    public static int getResult() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(selected[i] - selected[i + 1]);
        }
        return sum;
    }
}
