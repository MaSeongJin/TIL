import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static int[] num = {1, 5, 10, 50};
    public static HashSet<Integer> hashset = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        simulate(0, 0, 0);
        System.out.println(hashset.size());
    }

    public static void simulate(int level, int sum, int idx) {
        if (level == N) {
            hashset.add(sum);
            return;
        }
        for (int i = idx; i < 4; i++) {
            simulate(level + 1, sum + num[i], i);
        }

    }
}
