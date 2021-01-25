package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11049_행렬곱셈순서 {
	static int N;
	static int[][] mat;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		mat = new int[N][2];
		dp = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			mat[i][0] = Integer.parseInt(tk.nextToken());
			mat[i][1] = Integer.parseInt(tk.nextToken());
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		System.out.println(solve(0,N-1));
		
		
	}
	private static int solve (int s, int e) {
		if(s==e) {
			return 0;
		}
		if(dp[s][e] != Integer.MAX_VALUE) {
			return dp[s][e];
		}
		for(int i=s; i<e; i++) {
			dp[s][e] = Math.min(dp[s][e], solve(s,i) + solve(i+1,e) + mat[s][0] * mat[i][1] * mat[e][1]);
		}
		return dp[s][e];
	}

}
