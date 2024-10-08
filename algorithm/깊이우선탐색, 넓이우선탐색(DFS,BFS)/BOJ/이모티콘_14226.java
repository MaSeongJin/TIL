import java.io.*;
import java.util.*;

public class Main {

    static class Step{
        int emoticon_num;
        int clipboard_num;
        int time;

        public Step(int emoticon_num, int clipboard_num, int time) {
            this.emoticon_num = emoticon_num;
            this.clipboard_num = clipboard_num;
            this.time = time;
        }
    }

    static boolean[][] visited;
    static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        visited = new boolean[2001][2001];

        bfs();
    }

    public static void bfs() {
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(1, 0, 0));

        while (!queue.isEmpty()) {
            Step now = queue.poll();

            int emoticon_num = now.emoticon_num;
            int clipboard_num = now.clipboard_num;
            int time = now.time;

            if(emoticon_num == S){
                System.out.println(time);
                return;
            }

            if(emoticon_num > 0 && emoticon_num < 2000){
                if(!visited[emoticon_num][emoticon_num]){
                    visited[emoticon_num][emoticon_num] = true;

                    queue.offer(new Step(emoticon_num, emoticon_num, time + 1));
                }

                if (!visited[emoticon_num - 1][clipboard_num]) {
                    visited[emoticon_num - 1][clipboard_num] = true;

                    queue.offer(new Step(emoticon_num - 1, clipboard_num, time + 1));
                }
            }

            if (clipboard_num > 0 && emoticon_num + clipboard_num < 2000) {
                if (!visited[emoticon_num+clipboard_num][clipboard_num]) {
                    visited[emoticon_num+clipboard_num][clipboard_num] = true;

                    queue.offer(new Step(emoticon_num + clipboard_num, clipboard_num, time + 1));
                }
            }
        }
    }
}
