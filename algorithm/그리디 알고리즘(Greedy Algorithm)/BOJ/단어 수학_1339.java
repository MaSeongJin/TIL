import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] alphaArr = new int[26];
        Arrays.fill(alphaArr, 0);

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            int len = str.length();
            for (int j = 0; j < len; j++) {
                char c = str.charAt(j);
                alphaArr[c - 'A'] += (int) Math.pow(10, len - 1 - j);
            }
        }

        Arrays.sort(alphaArr);
        int multipleNum = 9;
        int index = 25;
        int sum = 0;
        while (alphaArr[index] > 0) {
            sum += alphaArr[index] * multipleNum;
            index--;
            multipleNum--;
        }
        System.out.println(sum);
    }
}
