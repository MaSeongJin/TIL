// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWcPjEuKAFgDFAU4
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] nums = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = -1;
			for (int i = 0; i < nums.length; i++) {
				for (int j = i + 1; j < nums.length; j++) {
					int tmp = find(nums[i]*nums[j]);
					if(max < tmp) {
						max = tmp;
					}
				}
			}

			System.out.println("#" + tc + " " + max);
			
		}
	}
	
	public static int find(int value) {
		String str = String.valueOf(value);
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length-1; i++) {
			if(c[i] > c[i+1]) {
				return -1;
			}
		}
		return value;
	}
	
}
