package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1149_RGB거리 {
	static int N;
	static int[][] cost,dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		dp = new int[N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(tk.nextToken());
				if(i==0) {
					dp[i][j] = cost[i][j];
				}
				solve(i,j);
			}
		}
		
		
		int min = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			min = Integer.min(min, dp[N-1][i]);
		}
		System.out.println(min);

	}
	private static int solve(int n, int c) {
		if(dp[n][c] != 0) return dp[n][c];
		
		dp[n][c] = 1000001;
		for(int i=0; i<3; i++) {
			if(c==i) continue;
			dp[n][c] = Integer.min(dp[n][c], solve(n-1,i) + cost[n][c]);
		} 
		
		return dp[n][c];
		
	}

}
