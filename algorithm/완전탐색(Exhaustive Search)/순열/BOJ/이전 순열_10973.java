import java.util.*;

public class Main {
  
    static int N;
    static int[] arr;
  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for (int i =0; i < N; i++)
            arr[i] = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        if (nextPermutation()) {
            for (int i: arr)
                sb.append(i).append(" ");
        } else {
            sb.append(-1);
        }
        System.out.println(sb);
 
    }
  
    public static boolean nextPermutation() {
        int i = arr.length-1;
        while (i > 0 && arr[i] > arr[i-1]) {
            i--;
        }
        if(i == 0)
            return false;
        int j = arr.length-1;
        while (arr[i-1] < arr[j]) {
            j--;
        }
        swap(i-1, j);
        j = arr.length-1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }
        return true;
    }
  
    public static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
