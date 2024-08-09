import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;
    static boolean[] isVisited;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }
        list = new ArrayList<>();
        isVisited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            isVisited[i] = true;
            dfs(i, i);
            isVisited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

    static void dfs(int start, int target) {
        if (!isVisited[arr[start]]) {
            isVisited[arr[start]] = true;
            dfs(arr[start], target);
            isVisited[arr[start]] = false;
        }

        if (arr[start] == target) {
            list.add(target);
        }
    }

}
