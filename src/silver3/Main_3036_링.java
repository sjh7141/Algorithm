package silver3;

import java.util.Scanner;

public class Main_3036_링 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int std = sc.nextInt();
		for(int i=1; i<N ;i++) {
			int A = std;
			int B = sc.nextInt();
			int GCD =  getGCD(A,B);
			System.out.println((A/GCD)+"/"+(B/GCD));
		}
	}

	private static int getGCD(int a, int b) {
		int big = (a>=b) ? a : b;
		int small = (a<b) ? a : b;
		if(big%small == 0) {
			return small;
		}else {
			int GCD = getGCD(big-small, small);
			return GCD;
		}
	}

}
