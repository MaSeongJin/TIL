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
//////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
 
class pos { // 회사, 고객, 집의 좌표
    int r;
    int c;
 
    pos() {
    }
 
    pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
 
public class Solution {
    static int min; // 정답으로 출력할 값. 모든 곳 방문 후 집 도착하면 갱신 여부 확인
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = sc.nextInt();
            pos[] customer = new pos[n]; // 고객 위치 배열
            pos start, end; // 회사, 집
            min= Integer.MAX_VALUE; // 정답 초기화
            int c = sc.nextInt();
            int r = sc.nextInt();
            start = new pos(r, c); // 이차원 배열이 (x,y)로 주어진대서 x가 c, y가 r이라 생각. 문제 자체로는 순서 바뀌어도 무관할듯?
            c = sc.nextInt();
            r = sc.nextInt();
            end = new pos(r, c);
            for (int i = 0; i < n; i++) {
                c = sc.nextInt();
                r = sc.nextInt();
                customer[i] = new pos(r, c);
            }
            // 구해야할것: start에서부터 customer의 모든 곳을 방문 후 end에 도착할 때 드는 거리
            // 한칸 이동할 때마다 거리를 더해가다가 모든 index를 다 방문 시, 마지막 방문 고객부터 집까지 거리 구한 뒤 min 갱신
            // 처음 시작은 무조건 start로 고정되기 때문에, start에서 어디로 갈지 정해주고 재귀 시작
            for (int i = 0; i < n; i++) {
                int d = Math.abs(customer[i].r - start.r) + Math.abs(customer[i].c - start.c); // 회사에서 첫 방문 집까지 거리
                recursion(i, 1 << i, d, n, customer, end); // 현재 위치, 방문한 곳 체크(비트마스킹), 거리, 고객 수, 배열 및 도착점 정보
            }
            System.out.println("#"+tc+" "+min);
        }
    }
 
    public static void recursion(int idx, int visited, int d, int n, pos[] customer, pos end) {
        if (d > min) return; // 이미 min보다 먼 거리를 이동했으면 더 들어갈 필요 없음 => return시킨다
 
        if (visited + 1 == 1 << n) { // 모든 곳을 방문하면 1이 n개, 거기에 1 더하면 1에다 0 n개 달린거 (1<<n+1 과 동일)
            d += Math.abs(end.r - customer[idx].r) + Math.abs(end.c - customer[idx].c); // 이제 다 방문했으니 집까지 이동
            min = (min > d) ? d : min; // d<min이면 min=d, 아니면 min 유지
            return; // 끝까지 돌았으니 return
        }
        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) > 0) continue; // 이미 방문한 적 있으면 continue
            int move= Math.abs(customer[idx].r - customer[i].r) + Math.abs(customer[idx].c - customer[i].c);
            // 다음 위치로 이동하는데 필요한 거리
            recursion(i, visited | 1 << i, d+move, n, customer, end); // 다음 이동할 위치, 다음 방문 체크, 다음 방문시 총 거리
        }
    }
}
