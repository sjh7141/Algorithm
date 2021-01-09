package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1463_1로만들기 {
	static int N;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		for(int i=1; i<N+1; i++) {
			int temp = 1000000;
			if(i%3 == 0) {
				temp = Integer.min(temp, dp[i/3] + 1);
			}
			if(i%2 == 0) {
				temp = Integer.min(temp, dp[i/2] + 1);
			}
			if(i>1) {
				dp[i] = Integer.min(temp, dp[i-1] + 1);
			}
		}
		
		System.out.println(dp[N]);

	}

}
