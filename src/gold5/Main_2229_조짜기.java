package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2229_조짜기 {
	static int N;
	static int[] score;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer tk = new StringTokenizer(br.readLine());
		score = new int[N];
		dp = new int[N];
		Arrays.fill(dp, -1);
		for(int i=0; i<N; i++) {
			score[i] = Integer.parseInt(tk.nextToken());
		} 
		
		System.out.println(solve(0));
	}
	private static int solve(int n) {
		if(n==N) return 0;
		if(dp[n] != -1) return dp[n];
		int max = 0, min = 10000;
		for(int i=n; i<N; i++) {
			max = Math.max(max, score[i]);
			min = Math.min(min, score[i]);
			dp[n] = Math.max(dp[n], solve(i+1) + max-min);
		}
		return dp[n];
	}

}
