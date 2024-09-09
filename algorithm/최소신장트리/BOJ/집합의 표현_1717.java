import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] parent;

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (x < y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        for (int i = 0; i < N + 1; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int sel = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (sel == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }

    }
}
