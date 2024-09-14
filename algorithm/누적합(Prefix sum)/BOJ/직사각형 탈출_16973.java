import java.util.*;
import java.io.*;

public class Main {
    
    static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static int N, M;
    public static int[][] map;
    public static boolean[][] visited;
    public static int H, W, sr, sc, fr, fc;
    public static int result = 0;
    public static ArrayList<Integer[]> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    arr.add(new Integer[]{i, j});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        fr = Integer.parseInt(st.nextToken());
        fc = Integer.parseInt(st.nextToken());

        bfs();

        if (result != 0) {
            System.out.println(result);
        } else {
            System.out.println("-1");
        }

    }

    public static void bfs() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sr, sc, 0));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Node temp = q.poll();
            int x = temp.x;
            int y = temp.y;
            int cnt = temp.cnt;

            if (x == fr && y == fc) {
                result = cnt;
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 1 || nx + H - 1 > N || ny < 1 || ny + W - 1 > M) continue;
                if (!Check(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 1) continue;
                q.offer(new Node(nx, ny, cnt + 1));
                visited[nx][ny] = true;
            }
        }
    }

    public static boolean Check(int x, int y) {
        for (Integer[] integers : arr) {
            int wallx = integers[0];
            int wally = integers[1];

            if (x <= wallx && wallx <= x + H - 1 && y <= wally && wally <= y + W - 1) {
                return false;
            }
        }
        return true;
    }

}
