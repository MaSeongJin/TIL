import java.util.*;
import java.io.*;

public class Main {
    private static final String[] COLORS = {"R", "B", "Y", "G"};
    private static final int[] cardNumber = new int[5];
    private static final int[] cntColor = new int[4];    // 0:R, 1:B, 2:Y, 3:G
    private static final int[] cntNumber = new int[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] cardColor = new String[5];

        // 입력 및 카운트 초기화
        for (int i = 0; i < 5; i++) {
            cardColor[i] = sc.next();
            cardNumber[i] = sc.nextInt();
            cntNumber[cardNumber[i]]++;
            countColor(cardColor[i]);
        }

        int result = calculateResult();
        System.out.println(result);
    }

    // 색상 카운팅
    private static void countColor(String color) {
        switch (color) {
            case "R": cntColor[0]++; break;
            case "B": cntColor[1]++; break;
            case "Y": cntColor[2]++; break;
            case "G": cntColor[3]++; break;
        }
    }

    // 게임 결과 계산
    private static int calculateResult() {
        if (isFlush() && isStraight()) {
            return 900 + getHighestCard();
        }
        if (hasFourOfAKind()) {
            return 800 + getFourOfAKind();
        }
        if (hasFullHouse()) {
            return 700 + getThreeOfAKind() * 10 + getPair();
        }
        if (isFlush()) {
            return 600 + getHighestCard();
        }
        if (isStraight()) {
            return 500 + getHighestCard();
        }
        if (hasThreeOfAKind()) {
            return 400 + getThreeOfAKind();
        }
        if (hasTwoPairs()) {
            return 300 + getHighestPair() * 10 + getLowestPair();
        }
        if (hasPair()) {
            return 200 + getPair();
        }
        return 100 + getHighestCard();
    }

    // 규칙 검사 함수들
    private static boolean isFlush() {
        for (int count : cntColor) {
            if (count == 5) return true;
        }
        return false;
    }

    private static boolean isStraight() {
        int start = findFirstNonZero();
        if (start + 4 > 9) return false; // 스트레이트는 9 이하의 값이어야 함
        for (int i = start; i < start + 5; i++) {
            if (cntNumber[i] != 1) return false;
        }
        return true;
    }

    private static boolean hasFourOfAKind() {
        for (int count : cntNumber) {
            if (count == 4) return true;
        }
        return false;
    }

    private static boolean hasFullHouse() {
        return hasThreeOfAKind() && hasPair();
    }

    private static boolean hasThreeOfAKind() {
        for (int count : cntNumber) {
            if (count == 3) return true;
        }
        return false;
    }

    private static boolean hasTwoPairs() {
        int pairCount = 0;
        for (int count : cntNumber) {
            if (count == 2) pairCount++;
        }
        return pairCount == 2;
    }

    private static boolean hasPair() {
        for (int count : cntNumber) {
            if (count == 2) return true;
        }
        return false;
    }

    // 유틸리티 함수들
    private static int getHighestCard() {
        for (int i = 9; i >= 1; i--) {
            if (cntNumber[i] > 0) return i;
        }
        return 0;
    }

    private static int getFourOfAKind() {
        for (int i = 1; i <= 9; i++) {
            if (cntNumber[i] == 4) return i;
        }
        return 0;
    }

    private static int getThreeOfAKind() {
        for (int i = 1; i <= 9; i++) {
            if (cntNumber[i] == 3) return i;
        }
        return 0;
    }

    private static int getPair() {
        for (int i = 1; i <= 9; i++) {
            if (cntNumber[i] == 2) return i;
        }
        return 0;
    }

    private static int getHighestPair() {
        for (int i = 9; i >= 1; i--) {
            if (cntNumber[i] == 2) return i;
        }
        return 0;
    }

    private static int getLowestPair() {
        boolean foundHighest = false;
        for (int i = 9; i >= 1; i--) {
            if (cntNumber[i] == 2) {
                if (foundHighest) return i;
                foundHighest = true;
            }
        }
        return 0;
    }

    private static int findFirstNonZero() {
        for (int i = 1; i <= 9; i++) {
            if (cntNumber[i] == 1) return i;
        }
        return 0;
    }
}
