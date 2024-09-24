import java.io.*;
import java.util.*;

public class Main {

    static int[] arr, tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tree = new int[N * 4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (op == 1) {
                arr[a] = b + 1;
                update(0, N - 1, 1, a);
            } else {
                sb.append(getMin(0, N - 1, 1, a, b)).append("\n");
            }
        }
        System.out.println(sb);

    }

    static int init(int s, int e, int node) {
        if (s == e) return tree[node] = arr[s];

        int mid = (s + e) / 2;
        return tree[node] = Math.min(init(s, mid, node * 2), init(mid + 1, e, node * 2 + 1));
    }

    static int update(int s, int e, int node, int idx) {
        if (s > idx || e < idx) return tree[node];
        if (s == e) return tree[node] = arr[idx];

        int mid = (s + e) / 2;
        return tree[node] = Math.min(update(s, mid, node * 2, idx), update(mid + 1, e, node * 2 + 1, idx));
    }

    static int getMin(int s, int e, int node, int l, int r) {
        if (r < s || l > e) return Integer.MAX_VALUE;
        if (l <= s && e <= r) return tree[node];

        int mid = (s + e) / 2;
        return Math.min(getMin(s, mid, node * 2, l, r), getMin(mid + 1, e, node * 2 + 1, l, r));
    }

}
