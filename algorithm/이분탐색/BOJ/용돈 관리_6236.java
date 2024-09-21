import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        arr = new int[N];

        int end = 0;
        int start = 0;
        int max = 0;
        int result = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            end += arr[i];
            max = Math.max(max, arr[i]);

        }

        start = max;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (M >= getMid(mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(result);
    }

    static int getMid(int tempMoney) {
        int count = 1;
        int money = tempMoney;

        for (int num : arr) {
            money -= num;

            if (money < 0) {
                count++;
                money = tempMoney - num;
            }

        }
        return count;
    }
}
