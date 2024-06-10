import java.io.*;
import java.util.*;

public class Main {

    final static int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] fx = new long[MAX];
        long[] gx = new long[MAX];

        Arrays.fill(fx, 1);

        for (int i = 2; i < MAX; i++) {
            for (int j = 1; i * j < MAX; j++) {
                fx[i * j] += i;
            }
        }

        for (int i = 1; i < MAX; i++) {
            gx[i] = gx[i - 1] + fx[i];
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            sb.append(gx[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

}
