import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R;
    static int C;
    static char[][] maze;
    static int[][] fire;
    static int[][] jihoon;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new char[R][C];
        fire = new int[R][C];
        jihoon = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(fire[i], -1);
            Arrays.fill(jihoon[i], -1);
        }

        Queue<Pair> queF = new LinkedList<>();
        Queue<Pair> queJ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                maze[i][j] = str[j].charAt(0);

                if (maze[i][j] == 'F') {
                    queF.offer(new Pair(i, j));
                    fire[i][j] = 0;
                }

                if (maze[i][j] == 'J') {
                    if (isEdge(i, j)) {
                        System.out.println(1);
                        return;
                    }

                    queJ.offer(new Pair(i, j));
                    jihoon[i][j] = 0;
                }
            }
        }
        br.close();

        bw.write(findEscape(queF, queJ));
        bw.flush();
        bw.close();

    }

    public static boolean isNotRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C;
    }

    public static boolean isEdge(int x, int y) {
        return x == 0 || x == R - 1 || y == 0 || y == C - 1;
    }

    public static String findEscape(Queue<Pair> queF, Queue<Pair> queJ) {

        while (!queF.isEmpty()) {
            Pair pollCellF = queF.poll();

            for (int k = 0; k < 4; k++) {
                int nx = pollCellF.x + dx[k];
                int ny = pollCellF.y + dy[k];

                if (isNotRange(nx, ny) || maze[nx][ny] == '#' || fire[nx][ny] != -1) continue;

                queF.offer(new Pair(nx, ny));
                fire[nx][ny] = fire[pollCellF.x][pollCellF.y] + 1;
            }
        }

        while (!queJ.isEmpty()) {
            Pair pollCellJ = queJ.poll();

            int time = jihoon[pollCellJ.x][pollCellJ.y] + 1;

            if (isEdge(pollCellJ.x, pollCellJ.y)) return String.valueOf(time);

            for (int k = 0; k < 4; k++) {
                int nx = pollCellJ.x + dx[k];
                int ny = pollCellJ.y + dy[k];

                if (isNotRange(nx, ny) || maze[nx][ny] == '#'
                        || (fire[nx][ny] != -1 && fire[nx][ny] <= time)
                        || jihoon[nx][ny] != -1) continue;

                queJ.offer(new Pair(nx, ny));
                jihoon[nx][ny] = jihoon[pollCellJ.x][pollCellJ.y] + 1;
            }
        }

        return "IMPOSSIBLE";
    }
}
