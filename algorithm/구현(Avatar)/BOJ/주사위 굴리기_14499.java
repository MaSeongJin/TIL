import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X, Y;
    static int[][] map;
    static int[] dice;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int order = Integer.parseInt(st.nextToken());
            Simulation(order - 1);
        }

        System.out.println(sb);
    }

    static void Simulation(int dir) {
        int nx = X + dx[dir];
        int ny = Y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= N || ny >= M)
            return;

        int tmp = dice[5];
        switch (dir) {
            case 0:
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = tmp;
                break;
            case 1:
                dice[5] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = tmp;
                break;
            default:
                dice[5] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = tmp;
        }

        sb.append(dice[0]).append("\n");

        X = nx;
        Y = ny;

        if (map[X][Y] == 0) {
            map[X][Y] = dice[5];
        }

        else {
            dice[5] = map[X][Y];
            map[X][Y] = 0;
        }
    }
}
