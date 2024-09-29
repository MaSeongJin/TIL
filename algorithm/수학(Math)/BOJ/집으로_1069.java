import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int D = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        if (D <= T) {
            System.out.println(Math.sqrt(X * X + Y * Y));
        } else {
            double dist = Math.sqrt(X * X + Y * Y);

            int N = (int) dist / D;

            if (dist - N * D == 0) {
                System.out.println(N * T);
            } else {
                if (N == 0) {
                    double two_jump = T + T;
                    double jumpAndBack = T + D - dist;

                    System.out.println(Math.min(two_jump, Math.min(dist, jumpAndBack)));
                } else {
                    double rest = dist - N * D;

                    System.out.println(Math.min(T, rest) + N * T);
                }
            }
        }
    }

}
