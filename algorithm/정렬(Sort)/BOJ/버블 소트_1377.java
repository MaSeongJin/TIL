import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node> {
        int i, v;
        
        public Node(int i, int v) {
            this.i = i;
            this.v = v;
        }
        
        public int compareTo (Node n) {
            return this.v - n.v;    
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Node [] arr = new Node[N];

        for(int i = 0 ; i < N ; i++) {
            int v = Integer.parseInt(br.readLine());
            arr[i] = new Node(i, v);
        }

        Arrays.sort(arr);

        int maxMinus = -1;

        for(int i = 0 ; i < N ; i++) {
            Node n = arr[i];
            if(n.i - i > maxMinus) {
                maxMinus = n.i - i;
            }

        }

        bw.write(String.valueOf(maxMinus + 1));
        bw.flush();
        bw.close();
    }

}
