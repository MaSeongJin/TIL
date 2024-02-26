import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        char[] calculation = br.readLine().toCharArray();

        Stack<Character> op = new Stack<>();

        for (char c : calculation) {
            if (c >= 'A' && c <= 'Z')
                sb.append(c);
            else if (c == '(') {
                op.push(c);
            } else if (c == ')') {
                while (!op.isEmpty() && op.peek() != '(') sb.append(op.pop());
                op.pop();
            } else {
                while (!op.isEmpty() && priority(op.peek()) >= priority(c)) sb.append(op.pop());
                op.push(c);
            }
        }

        while (!op.isEmpty()) sb.append(op.pop());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int priority(char op) {
        if (op == '(' || op == ')') return 0;
        else if (op == '+' || op == '-') return 1;
        else return 2;
    }

}
