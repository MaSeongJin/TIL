import java.util.*;
import java.io.*;

public class Main {
    
    static int n;
    static int[][] arr;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static HashSet<String> hashset = new HashSet<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        arr = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
        String s = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, s);
            }
        }
        System.out.println(hashset.size());
    }

    public static void dfs(int x, int y, int cnt, String s) {
        if (cnt == 6) {
            hashset.add(s);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                dfs(nx, ny, cnt + 1, s + arr[nx][ny]);
            }
        }
    }
}
