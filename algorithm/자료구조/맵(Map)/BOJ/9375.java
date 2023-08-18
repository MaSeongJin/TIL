import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			Map<String, Integer> map = new HashMap<>(); // <종류, 개수>
			int N = Integer.parseInt(br.readLine()); // 입력받는 옷의 개수

			while (N-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();	// 옷 이름은 필요없으므로 버림
				String kind = st.nextToken();
				/*
				 * 해당 종류의 옷이 해시맵에 있을경우
				 * 해시맵에 저장되어있던 해당 종류의 개수를 +1 증가시킨다.
				 *
				 * 해당 종류의 옷이 해시맵에 없을 경우
				 * 해당 종류와 개수 1을 넣는다.
				 */
				if(map.containsKey(kind)) {
					map.put(kind, map.get(kind) + 1);
				} else {
					map.put(kind, 1);
				}
			}
			int result = 1;
			// 안 입는 경우를 고려하여 각 종류별 옷의 개수에 +1 해준 값을 곱해준다.
			for (int val : map.values()) {
				result *= (val + 1);
			}
			// 알몸인 상태를 제외하기 위해 -1해준다.
			sb.append(result-1).append("\n");
		}
		System.out.println(sb);
	}

}
