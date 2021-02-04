package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17404_RGB거리2 {
	static int[][] cost, dp;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N+1][3];
		dp = new int[N+1][3];
		for(int i=1; i<N+1; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==j) dp[1][j] = cost[1][j];
				else dp[1][j] = 1000001;
			}
			
			for(int j=2; j<N+1; j++) {
				for(int k=0; k<3; k++) {
					dp[j][k] = cost[j][k] + Math.min(dp[j-1][(k+1)%3], dp[j-1][(k+2)%3]);
				}
			}
			
			for(int j=0; j<3; j++) {
				if(j != i) ans = Math.min(ans, dp[N][j]);
			}
		}		
		
		
		System.out.println(ans);
		
		

	}

}
