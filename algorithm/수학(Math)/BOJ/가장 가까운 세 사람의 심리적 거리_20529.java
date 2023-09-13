// https://www.acmicpc.net/problem/20529
public class Main {

	public static int T, N;
	public static String[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			int M = N > 32 ? 33 : N;
			arr = new String[M];
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < M; i++) {
				arr[i] = st.nextToken();
			}
			
			if (M == 33) {
				System.out.println(0);
				continue;
			}

			int result = 12;
			loop: for (int i = 0; i < M; i++) {
				for (int j = i + 1; j < M; j++) {
					for (int k = j + 1; k < M; k++) {
						result = Math.min(result, calc(arr[i], arr[j], arr[k]));
						if (result == 0) {
							break loop;
						}
					}
				}
			}
			System.out.println(result);
		}
	}

	private static int calc(String a, String b, String c) {
		int distance = 0;
		for (int i = 0; i < 4; i++) {
			distance += a.charAt(i) != b.charAt(i) ? 1 : 0;
			distance += b.charAt(i) != c.charAt(i) ? 1 : 0;
			distance += c.charAt(i) != a.charAt(i) ? 1 : 0;
		}
		return distance;
	}
}
