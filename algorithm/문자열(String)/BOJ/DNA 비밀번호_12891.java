import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int answer=0;
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		char[] dna = br.readLine().toCharArray();  //문자열을 문자 배열로 변환
		
		st = new StringTokenizer(br.readLine());
		int[] check = new int[4];  // 각 문자가 몇개 이상 발견 돼야하는지 저장 배열
		for (int i = 0; i < 4; i++) {
			check[i] = Integer.parseInt(st.nextToken());
		}
		
		// 첫 임시 비밀번호 알파벳 카운트
		int[] countAlph = new int[4];
		for (int j = 0; j < P; j++) {
			switch (dna[j]) {
			case 'A': countAlph[0]++; break;
			case 'C': countAlph[1]++; break;
			case 'G': countAlph[2]++; break;
			case 'T': countAlph[3]++; break;
			}
		}
		
		// 비밀번호 후보 
		for (int i = 0; i <= N-P; i++) {
			boolean flag = false;
			// 조건에 부합하는지 확인
			for(int j=0; j<4; j++) {
				if(countAlph[j] < check[j]) {
					flag = true;
					break;
				}
			}
			if (!flag) // 사용 가능한 비밀번호
				answer++;
			if(i==N-P) break;
			
			countAlph[position(dna[i])]--;  //맨 앞 문자 제거
			countAlph[position(dna[i+P])]++;  //뒤 문자 추가
		}
		
		System.out.println(answer);

	}
	
	// 각 문자의 위치를 확인 
	private static int position(char alph) {
		switch(alph) {
		case 'A' : return 0;
		case 'C' : return 1;
		case 'G' : return 2;
		case 'T' : return 3;
		}
		return -1;
	}

}
