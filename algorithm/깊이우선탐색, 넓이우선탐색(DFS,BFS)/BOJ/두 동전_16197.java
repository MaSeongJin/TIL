import java.util.*;
import java.io.*;

public class Main {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Point coin1 = null;
        Point coin2 = null;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'o') {
                    if (coin1 == null) {
                        coin1 = new Point(i, j);
                    } else {
                        coin2 = new Point(i, j);
                    }
                }
            }
        }
        System.out.printf("%d", bfs(coin1, coin2));
    }

    public static int bfs(Point coin1, Point coin2) {
        Queue<Point[]> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        q.add(new Point[]{coin1, coin2});
        String start = "";
        start += coin1.x;
        start += coin1.y;
        start += coin2.x;
        start += coin2.x;
        start += coin2.y;
        visited.add(start);

        int depth = 1;

        while (!q.isEmpty()) {
            if (depth > 10) return -1;
            int len = q.size();
            for (int l = 0; l < len; l++) {
                Point[] now = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx1 = now[0].x + dist[i][0];
                    int ny1 = now[0].y + dist[i][1];

                    int nx2 = now[1].x + dist[i][0];
                    int ny2 = now[1].y + dist[i][1];

                    if ((!isIn(nx1, ny1) && isIn(nx2, ny2)) || (isIn(nx1, ny1) && !isIn(nx2, ny2))) {
                        return depth;
                    }
                    if ((!isIn(nx1, ny1) && !isIn(nx2, ny2))) continue;

                    if (map[nx1][ny1] == '#' && map[nx2][ny2] == '#') continue;

                    if (map[nx1][ny1] == '#' && map[nx2][ny2] != '#') {
                        if (now[0].x == nx2 && now[0].y == ny2) continue;
                        nx1 = now[0].x;
                        ny1 = now[0].y;
                    } else if (map[nx1][ny1] != '#' && map[nx2][ny2] == '#') {
                        if (now[1].x == nx1 && now[1].y == ny1) continue;
                        nx2 = now[1].x;
                        ny2 = now[1].y;
                    }
                    String next = "";
                    next += nx1;
                    next += ny1;
                    next += nx2;
                    next += ny2;

                    if (visited.contains(next)) continue;

                    visited.add(next);
                    q.add(new Point[]{new Point(nx1, ny1), new Point(nx2, ny2)});
                }
            }
            depth++;
        }

        return -1;
    }

    public static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
