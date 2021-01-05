package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1904_01타일 {
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		dp[1] = 1;
		if(N>1) dp[2] = 2;
		for(int i=3; i<N+1; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%15746;
		}
		
		System.out.println(dp[N]);

	}

}
