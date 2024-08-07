import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
 
        String input = br.readLine();
 
        char[] arr = input.toCharArray();
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (K > 0 && !dq.isEmpty() && dq.getLast() < arr[i]) {
                dq.removeLast();
                K--;
            }
            dq.addLast(arr[i]);
        }
 
        StringBuilder ans = new StringBuilder();

        while (dq.size() > K) {
            ans.append(dq.removeFirst());
        }
 
        bw.write(ans.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
}
