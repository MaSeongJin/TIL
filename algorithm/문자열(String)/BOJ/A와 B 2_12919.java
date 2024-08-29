import java.io.*;

public class Main {
    static String S, T;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        ans = 0;
        makeS(T);

        System.out.println(ans);
    }

    private static void makeS(String t) {
        int len = t.length();

        if (len == S.length()) {
            if (t.equals(S)) {
                ans = 1;
            }
            return;
        }

        if (t.endsWith("A")) {
            makeS(t.substring(0, len - 1));
        }

        if (t.startsWith("B")) {
            makeS(new StringBuilder(t.substring(1)).reverse().toString());
        }
    }
}
