// https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AX_Y-4T6-yoDFAVy
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int[] sorted;
	public static int[] arr = new int[1000000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sorted = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		mergeSort(0, arr.length - 1);
		System.out.println(arr[500000]);

	}

	public static void mergeSort(int left, int right) {
		for (int size = 1; size <= right; size += size) {
			for (int l = 0; l <= right - size; l += (2 * size)) {
				int low = l;
				int mid = l + size - 1;
				int high = Math.min(l + 2 * size - 1, right);
				merge(low, mid, high);
			}
		}
//		if (left == right)
//			return;
//		int mid = (left + right) / 2;
//		mergeSort(left, mid);
//		mergeSort(mid + 1, right);
//		merge(left, mid, right);
	}

	public static void merge(int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {
			if (arr[l] <= arr[r]) {
				sorted[idx] = arr[l];
				idx++;
				l++;
			} else {
				sorted[idx] = arr[r];
				idx++;
				r++;
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx] = arr[r];
				idx++;
				r++;
			}
		} else {
			while (l <= mid) {
				sorted[idx] = arr[l];
				idx++;
				l++;
			}
		}

		for (int i = left; i <= right; i++) {
			arr[i] = sorted[i];
		}

	}

}
