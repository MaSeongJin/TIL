import java.util.*;
import java.io.*;

public class Main {

    static class Cheese {
        int x, y;

        Cheese(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int col, row, cheeseCnt = 0, count;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        col = Integer.parseInt(str[0]);
        row = Integer.parseInt(str[1]);
        arr = new int[col][row];
        visited = new boolean[col][row];


        for (int i = 0; i < col; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < row; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
                if (arr[i][j] == 1)
                    cheeseCnt++;
            }
        }


        int result = 0;
        count = 0;
        while (cheeseCnt > 0) {
            result = cheeseCnt; // 마지막 치즈 개수를 얻으려면 BFS보다 먼저 선언.
            bfs();
            visited = new boolean[col][row]; // 치즈를 계속 확인해야 하기 때문에 방문값 초기화.
            count++;
        }
        System.out.println(count);
        System.out.println(result);


    }

    public static void bfs() {
        Queue<Cheese> q = new LinkedList<>();
        q.add(new Cheese(0, 0));
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            Cheese ch = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ch.x + dx[i];
                int ny = ch.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= col || ny >= row)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (arr[nx][ny] == 0) { // 치즈가 아니라면 큐에 등록.
                    q.add(new Cheese(nx, ny));
                } else {
                    cheeseCnt--;
                    arr[nx][ny] = 0;
                }
                visited[nx][ny] = true;
            }
        }
        
    }
}
