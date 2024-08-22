import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] height = new int[W];
        int answer = 0;

        for (int i = 0; i < W; i++)
            height[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < W - 1; i++) {
            int left = 0, right = 0;
            for (int j = 0; j < i; j++)
                left = Math.max(left, height[j]);
            for (int j = i + 1; j < W; j++)
                right = Math.max(right, height[j]);
            if (height[i] < left && height[i] < right) {
                answer += Math.min(left, right) - height[i];
            }
        }
        bw.write(answer + "");
        bw.flush();
    }
    
}
