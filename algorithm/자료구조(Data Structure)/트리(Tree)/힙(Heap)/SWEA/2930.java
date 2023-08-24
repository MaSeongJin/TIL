// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV-Tj7ya3jYDFAXr
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static int[] heap;
	public static int heapSize;
	public static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());

			heap = new int[N + 1];
			pq = new PriorityQueue<>();
			heapSize = 0;
			int tmp = 0;
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				if (Integer.parseInt(st.nextToken()) == 1) {
					push(Integer.parseInt(st.nextToken()));
//					pq.add(Integer.parseInt(st.nextToken()) * -1);
				} else {
					sb.append(pop()).append(" ");
//					if (pq.peek() == null) {
//						tmp = -1;
//					} else {
//						tmp = pq.poll() * -1;
//					}
//					sb.append(tmp).append(" ");
				}
			}

			System.out.println("#" + tc + " " + sb);
		}
	}

	public static void push(int item) {
		heap[++heapSize] = item;

		int child = heapSize;
		int parent = child / 2;

		while (parent > 0 && heap[child] > heap[parent]) {
			int tmp = heap[parent];
			heap[parent] = heap[child];
			heap[child] = tmp;

			child = parent;
			parent = child / 2;
		}
	}

	public static int pop() {
		if (heapSize <= 0) {
			return -1;
		}

		int item = heap[1];
		heap[1] = heap[heapSize--];

		int parent = 1;
		int child = parent * 2;

		if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
			child += 1;
		}

		while (child <= heapSize && heap[parent] < heap[child]) {
			int tmp = heap[parent];
			heap[parent] = heap[child];
			heap[child] = tmp;

			parent = child;
			child = parent * 2;

			if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
				child += 1;
			}
		}

		return item;
	}

}
