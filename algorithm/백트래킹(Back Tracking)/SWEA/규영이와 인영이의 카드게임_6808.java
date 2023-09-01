// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    public static int[] arrA = new int[9];
    public static int[] arrB = new int[9];
    public static List<Integer> arrBList;
    public static int loseCnt;
    public static int winCnt;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            arrBList = new ArrayList<Integer>();
             
            for (int i = 1; i <= 18; i++) {
                arrBList.add(i);
            }
 
            for (int i = 0; i < 9; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
                arrBList.remove(arrBList.indexOf(arrA[i]));
            }
 
            for (int i = 0; i < arrB.length; i++) {
                arrB[i] = arrBList.get(i);
            }
 
            loseCnt = 0;
            winCnt = 0;
             
            perm(0);
 
            System.out.println("#" + tc + " " + loseCnt + " " + winCnt);
 
        }
 
    }
 
    public static void perm(int depth) {
        if (depth == 9) {
            compare();
            return;
        }
        for (int i = depth; i < 9; i++) {
            swap(depth, i);
            perm(depth + 1);
            swap(depth, i);
        }
    }
 
    private static void swap(int depth, int i) {
        int tmp = arrB[depth];
        arrB[depth] = arrB[i];
        arrB[i] = tmp;
 
    }
 
    private static void compare() {
        int manA = 0;
        int manB = 0;
        for (int i = 0; i < arrA.length; i++) {
            if (arrA[i] > arrB[i]) {
                manA += arrA[i] + arrB[i];
            } else {
                manB += arrA[i] + arrB[i];
            }
        }
        if (manA > manB) {
            loseCnt++;
        } else {
            winCnt++;
        }
 
    }
}
