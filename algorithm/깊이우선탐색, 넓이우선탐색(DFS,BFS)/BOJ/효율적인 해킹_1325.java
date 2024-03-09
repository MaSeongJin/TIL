import java.io.*;
import java.util.*;

public class Main {

    static class Computer{
        int idx;
        ArrayList<Computer> adj;

        public Computer(int idx) {
            this.idx = idx;
            this.adj = new ArrayList<>();
        }
    }

    static Computer[] comps;
    static int n;
    static int m;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        comps = new Computer[n + 1];
        for (int i = 1; i <= n; i++) {
            comps[i] = new Computer(i);
        }

        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            
            comps[b].adj.add(comps[a]);
        }

        answer = new int[n + 1];
        for(int i=1; i<=n; i++){
            visited = new boolean[n+1];
            visited[i] = true;
            dfs(i, i);
        }

        int max = 0;
        for(int i=1; i<=n; i++){
            max = Math.max(max, answer[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            if(max == answer[i]){
                sb.append(i+" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int start, int now){

        for (Computer c : comps[now].adj) {
            if (!visited[c.idx]) {
                visited[c.idx] = true;
                dfs(start, c.idx);
                answer[start] ++;
            }
        }

    }
}
