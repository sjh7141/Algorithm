package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2482_색상환 {
	static int N,K;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		dp = new int[N+1][K+1];
		
		for(int i=1; i<N+1; i++) {
			dp[i][0] = 1;
			dp[i][1] = i;
		}
		
		for(int i=2; i<N+1; i++) {
			for(int j=2; j<K+1; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % 1000000003;
			}
		}
		System.out.println((dp[N-3][K-1] + dp[N-1][K]) % 1000000003);
	}

}
