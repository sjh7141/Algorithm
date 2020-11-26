package silver5;

import java.util.Arrays;
import java.util.Scanner;

public class Main_1037_약수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(arr[0] * arr[N-1]);
	}
}
