// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			char[] ch = str.toCharArray();
			char[] now = new char[ch.length];
			for (int i = 0; i < now.length; i++) {
				now[i] = '0';
			}
			int cnt = 0;
			char tmp;
			for (int i = 0; i < now.length; i++) {
				if(ch[i] != now[i]) {
					cnt++;
					if(ch[i] == '0') {
						tmp = '0';
					} else {
						tmp = '1';
					}
					for (int j = i; j < now.length; j++) {
						now[j] = tmp;
					}
				}
			}
			

			System.out.println("#" + tc + " " + cnt);

		}
	}

}
