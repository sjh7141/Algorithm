package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1102_발전소 {
	static int N,P,init,state;
	static int min = Integer.MAX_VALUE;
	static boolean flag = false;
	static String str;
	static int[] dp = new int[1<<17];
	static boolean[] visit = new boolean[1<<17];
	static int[][] cost;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				cost[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		str = br.readLine();
		
		for(int i=0; i<N; i++) {
			if(str.charAt(i) == 'Y') {
				init++;
				state |= 1<<i;
			}
		}
		P = Integer.parseInt(br.readLine());
		if(init >= P) {
			System.out.println(0);
			return;
		}
		
		min = Math.min(min, solve(state,init));
		System.out.println((flag)?min:-1);

	}
	private static int solve(int state, int cnt) {
		if(cnt==P) {
			flag= true;
			return 0;
		}
		if(dp[state]!=Integer.MAX_VALUE) return dp[state];
		int cur = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			if((state & 1<<i) != 0) {
				for(int j=0; j<N; j++) {
					if((state & 1<<j) == 0) {
						cur = Math.min(cur, solve(state | 1<<j, cnt+1) + cost[i][j]);
					}
				}
			}
		}
		return dp[state] = cur;
	}

}
