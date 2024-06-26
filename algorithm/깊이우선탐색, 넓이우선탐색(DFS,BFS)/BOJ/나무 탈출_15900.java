import java.io.*;
import java.util.*;

public class Main {
    
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int totalDepth = 0;
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0;i<=N;i++)
            tree.add(new ArrayList<>());
        //노드의 관계 저장
        for(int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        search(0, 1, 0);		//DFS탐색으로 리프 노드 깊이의 합 구하기
        if(totalDepth%2==1)		//홀수 일 때, 성원 승!
            bw.write("Yes");
        else		//짝수 일 때, 형섭 승!
            bw.write("No");
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //DFS탐색으로 리프 노드의 깊이를 구하는 함수
    static void search(int depth, int cur, int parent){
        boolean check = false;	//리프 노드 확인 변수
        //자식 노드 존재시 탐색
        for(int next : tree.get(cur)){
            if(parent != next){
                check = true;
                search(depth+1, next, cur);
            }
        }
        //리프 노드일 때 깊이 더하기
        if(!check)
            totalDepth += depth;
    }
}
