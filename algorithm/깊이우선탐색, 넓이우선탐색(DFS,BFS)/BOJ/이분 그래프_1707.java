import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    public static int[] check;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        String[] answer = new String[testCase];

        for (int i = 0; i < testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list = new ArrayList[n + 1];
            for (int j = 0; j < n + 1; j++) {
                list[j] = new ArrayList<>();
            }

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }

            visited = new boolean[n + 1];
            check = new int[n + 1];
            flag = true;

            for (int j = 1; j < n + 1; j++) {
                if (flag) {
                    dfs(j);
                } else {
                    break;
                }
            }
            if (flag) {
                answer[i] = "YES";
            } else {
                answer[i] = "NO";
            }
        }

        for (String item : answer) {
            System.out.println(item);
        }

    }

    public static void dfs(int start) {
        visited[start] = true;
        for (int item : list[start]) {
            if (!visited[item]) {
                check[item] = (check[start] + 1) % 2;
                dfs(item);
            } else if (check[item] == check[start]) {
                flag = false;
            }
        }
    }
    
}
