package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1932_정수삼각형 {
	static int N;
	static int[][] tri, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tri = new int[N][N];
		dp = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			for(int j=0; j<=i; j++) {
				tri[i][j] = Integer.parseInt(tk.nextToken());
			}
			Arrays.fill(dp[i], -1);
		}
		
		for(int i=0; i<N; i++) {
			dp[N-1][i] = tri[N-1][i];
		}
		
		System.out.println(solve(0,0));
	}
	private static int solve(int r, int c) {
		if(dp[r][c] != -1) return dp[r][c];
		dp[r][c] = tri[r][c] + Math.max(solve(r+1,c), solve(r+1,c+1));
		return dp[r][c];
	}

}
