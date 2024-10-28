import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str;

        while ((str = bf.readLine()) != null) {
            int wid = Integer.parseInt(str) * 10000000;
            int num = Integer.parseInt(bf.readLine());
            boolean ans = false;

            int[] nums = new int[num];

            for (int i = 0; i < num; i++) {
                nums[i] = Integer.parseInt(bf.readLine());
            }

            Arrays.sort(nums);

            int p1 = 0;
            int p2 = num - 1;

            while (p1 < p2) {
                int sum = nums[p1] + nums[p2];

                if(sum == wid){
                    ans = true;
                    break;
                } else if(sum > wid) {
                    p2--;
                } else {
                    p1++;
                }
            }

            if (ans)
                System.out.printf("yes %d %d\n", nums[p1], nums[p2]);
            else
                System.out.print("danger\n");
        }

        bw.flush();
        bw.close();
    }

}
