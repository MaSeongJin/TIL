// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PTeo6AHUDFAUq
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 1; tc++) {
			int N = Integer.parseInt(br.readLine());
			String tmp = "";
			StringBuilder sb = new StringBuilder();
			String check;
			for (int i = 1; i <= N; i++) {
				tmp = Integer.toString(i);
				int res;
				check = "";
				for (int j = 0; j < tmp.length(); j++) {
					res = tmp.charAt(j);
					if (res == '3' || res == '6' || res == '9') {
						check += "-";
					}
				}
				if(check == "") {
					sb.append(tmp).append(" ");
				} else {
					sb.append(check).append(" ");
				}

			}
			System.out.println(sb);

		}
	}

}
