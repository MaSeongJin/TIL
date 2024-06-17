import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] dist;

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        scan.nextLine();
        board = new int[N][N];
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            String temp = scan.nextLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = temp.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(dist[N - 1][N - 1]);
    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            Node current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && dist[nx][ny] > dist[current.x][current.y]) {
                    if (board[nx][ny] == 1) dist[nx][ny] = dist[current.x][current.y];
                    else dist[nx][ny] = dist[current.x][current.y] + 1;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

}
