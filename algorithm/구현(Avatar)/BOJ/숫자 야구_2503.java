import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class BaseBallData {
        int data;
        int strike;
        int ball;

        public BaseBallData(int data, int strike, int ball) {
            this.data = data;
            this.strike = strike;
            this.ball = ball;
        }
    }

    static int N;
    static List<BaseBallData> inputData = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        N = Integer.parseInt(str);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int data = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            BaseBallData baseBallData = new BaseBallData(data, strike, ball);
            inputData.add(baseBallData);
        }

        System.out.println(calBaseBall());
    }

    static int calBaseBall() {
        int result = 0;

        for (int i = 123; i <= 987; i++) {
            if (!checkSameNum(i)) continue; // 중복숫자

            int allTestPass = 0;

            for (int j = 0; j < N; j++) {
                int strikeCount = 0;
                int ballCount = 0;

                BaseBallData current = inputData.get(j);
                String currentDataString = Integer.toString(current.data);
                String myDataString = Integer.toString(i);

                for (int k = 0; k < 3; k++) { // strike
                    if (currentDataString.charAt(k) == myDataString.charAt(k)) {
                        strikeCount++;
                    }
                }

                for (int h = 0; h < 3; h++) {
                    for (int u = 0; u < 3; u++) {
                        if (myDataString.charAt(h) == currentDataString.charAt(u)) { // ball
                            if (h != u)
                                ballCount++;
                        }
                    }
                }

                if (current.strike == strikeCount && current.ball == ballCount) {
                    allTestPass++;
                }
            }
            if (allTestPass == N) {
                result++;
            }
        }
        return result;
    }

    static boolean checkSameNum(int num) {
        String numString = Integer.toString(num);

        if (numString.charAt(0) == numString.charAt(1)) {
            return false;
        }

        if (numString.charAt(1) == numString.charAt(2)) {
            return false;
        }

        if (numString.charAt(0) == numString.charAt(2)) {
            return false;
        }

        return numString.charAt(0) != '0' && numString.charAt(1) != '0' && numString.charAt(2) != '0';
    }

}
