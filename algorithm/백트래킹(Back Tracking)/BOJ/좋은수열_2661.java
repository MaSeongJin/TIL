import java.util.Scanner;

public class Main {

    static int start = 1;
    static int end = 3;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        backtracking("");
    }

    public static void backtracking(String str) {
        if (str.length() == n) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = start; i <= end; i++) {
            if (isPossible(str + i)) backtracking(str + i);
        }
    }

    public static boolean isPossible(String str) {
        for (int i = 1; i <= str.length() / 2; i++) {
            String front = str.substring(str.length() - i * 2, str.length() - i);
            String back = str.substring(str.length() - i);
            if (front.equals(back)) return false;
        }
        return true;
    }
}
