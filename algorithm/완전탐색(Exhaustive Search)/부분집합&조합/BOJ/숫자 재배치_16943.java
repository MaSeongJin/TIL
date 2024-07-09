import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static String A, B, C;
    static int a, b, c;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        A = stringTokenizer.nextToken();
        B = stringTokenizer.nextToken();
        C = "";

        a = Integer.parseInt(A);
        b = Integer.parseInt(B);
        c = -1;

        isVisited = new boolean[A.length()];

        bfs();

        System.out.println(c);
    }

    private static void bfs() {
        if (C.length() == A.length()) {
            if (Integer.parseInt(C) < b)
                c = Math.max(c, Integer.parseInt(C));
            return;
        }

        for (int i = 0; i < A.length(); i++) {
            if ((C.isEmpty() && A.charAt(i) == '0') || isVisited[i]) continue;
            isVisited[i] = true;
            C += A.charAt(i);
            bfs();
            isVisited[i] = false;
            C = C.substring(0, C.length() - 1);
        }
    }
}
