import java.io.*;
import java.util.*;

public class Main {

    private static class Node {
        int number;
        Node left, right;

        public Node(int number) {
            this.number = number;
        }

        void insert(int input) {
            if (this.number > input) {
                if (this.left == null) {
                    this.left = new Node(input);
                } else {
                    this.left.insert(input);
                }
            }
            else {
                if (this.right == null) {
                    this.right = new Node(input);
                } else {
                    this.right.insert(input);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Node node = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String read = br.readLine();
            if (read == null || read.isEmpty()) break;

            int input = Integer.parseInt(read);
            node.insert(input);
        }

        post(node, bw);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void post(Node node, BufferedWriter bw) throws IOException {
        if (node.left != null) post(node.left, bw);
        if (node.right != null) post(node.right, bw);
        bw.write(node.number + System.lineSeparator());
    }
}
