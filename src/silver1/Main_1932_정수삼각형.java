package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		dp[0][0] = tri[0][0];
		int max = 0;
		for(int i=0; i<N; i++) {
			max = Integer.max(max, solve(N-1,i));
		}
		
		System.out.println(max);
	}
	private static int solve(int r, int c) {
		if(dp[r][c] != -1) return dp[r][c];
		int temp = -1;
		if(c!=0) {
			temp = Integer.max(temp, solve(r-1,c-1));
		}
		if(c!=r) {
			temp = Integer.max(temp, solve(r-1,c));
		}
		dp[r][c] = tri[r][c] + temp;
		return dp[r][c];
	}

}
