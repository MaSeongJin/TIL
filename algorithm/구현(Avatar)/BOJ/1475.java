import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        // 카운팅하기 편하게 배열 길이 1증가
        int[] arr = new int[10];
        
        for (int i = 0; i < s.length(); i++) {
            int num = Character.getNumericValue(s.charAt(i));
            // 6이면 9를 증가시켜 나중에 9의 값만 가지고 계산
            if (num == 6) {
                arr[9]++;
            // 아닐 경우 해당 숫자 증가
            } else {
                arr[num]++;
            }
        }
        int max = 0;
        for (int i = 0; i < 9; i++) {
            // 9를 제외하고 제일 높은 카운팅 수 찾기
            max = Math.max(max,arr[i]);
        }
        // 9와 6 둘다 사용 가능하기에 2로 나누어 저장
        int nine = arr[9]/2;
        // 1이 남는 다면 카운트 1 증가
        if (arr[9]%2==1) nine++;
        // 위에서 비교한 값과 9의 값을 비교
        max = Math.max(max,nine);
        System.out.print(max);
		
	}
}
