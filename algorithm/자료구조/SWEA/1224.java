import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			StringBuilder sb = new StringBuilder();
			Stack<Character> stack = new Stack<>();
			Stack<Integer> postStack = new Stack<>();
			for (int i = 0; i < N; i++) {
				char c = str.charAt(i);
				if (Character.isDigit(c)) {
					sb.append(c);
				}
				switch (c) {
				case '+':
					if (stack.isEmpty()) {
						stack.push(c);
					} else {
						while (!stack.isEmpty()) {
							if (stack.peek() == '(') {
								stack.push(c);
								break;
							} else {
								sb.append(stack.pop());
							}
						}
					}
					break;
				case '*':
					stack.push(c);
					break;
				case '(':
					stack.push(c);
					break;
				case ')':
					while (!stack.isEmpty()) {
						if (stack.peek() == '(') {
							stack.pop();
							break;
						} else {
							sb.append(stack.pop());
						}
					}
					break;
				}

			}

			String str1 = sb.toString();
			for (int i = 0; i < str1.length(); i++) {
				if (Character.isDigit(str1.charAt(i))) {
					postStack.push(str1.charAt(i) - '0');
				} else if (str1.charAt(i) == '+') {
					postStack.push(postStack.pop() + postStack.pop());
				} else if (str1.charAt(i) == '*') {
					postStack.push(postStack.pop() * postStack.pop());
				}
			}
			System.out.println("#" + tc + " " + postStack.peek());

		}

	}

}
