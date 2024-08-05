import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] truth;
    static int[] parent;
    static List<List<Integer>> attendee;
    static boolean[] knowTruth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        truth = new int[len];
        for(int i = 0; i < len; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
        }

        attendee = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            attendee.add(new ArrayList<>());
            len = Integer.parseInt(st.nextToken());
            for(int j = 0; j < len; j++) {
                int id = Integer.parseInt(st.nextToken());
                attendee.get(i).add(id); //파티에 참석하는 사람
            }
        }

        knowTruth = new boolean[n+1];
        parent = new int[n+1];
        
        //진실을 알고 있는 사람
        for(int t:truth) {
            knowTruth[t] = true;
        }

        for(int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        //같이 파티에 있었던 사람 union
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < attendee.get(i).size() - 1; j++) {
                union_node(attendee.get(i).get(j), attendee.get(i).get(j+1));
            }
        }

        //진실을 아는 사람의 parent는 진실을 알게 됨
        for(int i = 1; i <= n; i++) {
            if(knowTruth[i]){
                int parent = find_parent(i);
                knowTruth[parent] = true;
            }
        }

        int answer = 0;
        //파티 참석자의 parent가 진실을 알고있다면 패스
        for(int i = 0; i < m; i++) {
            boolean flag = true;
            for(int j = 0; j < attendee.get(i).size(); j++) {
                int p = attendee.get(i).get(j);
                int parent = find_parent(p);
                if(knowTruth[parent]) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                answer++;
        }

        System.out.println(answer);
        
    }

    public static int find_parent(int u) {
        if(parent[u] == u) return u;
        return parent[u] = find_parent(parent[u]);
    }

    public static void union_node(int u, int v) {
        u = find_parent(u);
        v = find_parent(v);

        if(u > v) {
            int temp = u;
            u = v;
            v = temp;
        }

        if(u == v) return;
        else parent[v] = u;
    }

}
