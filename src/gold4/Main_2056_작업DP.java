package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2056_작업DP {
	static int N;
	static int[] time, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		time = new int[N+1];
		dp = new int[N+1];
		
		int ans = 0;
		for(int i=1; i<N+1; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			dp[i] = time[i] = Integer.parseInt(tk.nextToken());
			int n = Integer.parseInt(tk.nextToken());
			for(int j=0; j<n; j++) {
				dp[i] = Math.max(dp[i], dp[Integer.parseInt(tk.nextToken())] + time[i]);
			}
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
		
	}

}
