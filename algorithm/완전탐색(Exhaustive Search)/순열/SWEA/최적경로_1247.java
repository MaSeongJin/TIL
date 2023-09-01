// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	public static int N;
	public static Map<Integer, int[]> map;
	public static int cnt, min;
	public static int officeI, officeJ, houseI, houseJ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			map = new HashMap<Integer, int[]>();

			officeI = Integer.parseInt(st.nextToken());
			officeJ = Integer.parseInt(st.nextToken());
			houseI = Integer.parseInt(st.nextToken());
			houseJ = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				map.put(i, new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
			}

			min = Integer.MAX_VALUE;
			cnt = 0;

			perm(0);

			System.out.println("#" + tc + " " + min);
		}
	}

	private static void perm(int depth) {
		if (depth == N) {

			cal();
			return;
		}
		for (int i = depth; i < N; i++) {
			swap(depth, i);
			perm(depth + 1);
			swap(depth, i);
		}

	}

	private static void swap(int depth, int i) {
		int[] tmp = map.get(depth);
		map.put(depth, map.get(i));
		map.put(i, tmp);
	}

	private static void cal() {
		cnt = 0;
		int x = officeI;
		int y = officeJ;
		for (int i = 0; i < N; i++) {
			cnt += Math.abs(x - map.get(i)[0]) + Math.abs(y - map.get(i)[1]);
			x = map.get(i)[0];
			y = map.get(i)[1];
		}
		cnt += Math.abs(x - houseI) + Math.abs(y - houseJ);
		min = Math.min(min, cnt);
	}

}
