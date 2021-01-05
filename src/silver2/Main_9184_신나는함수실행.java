package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9184_신나는함수실행 {
	static int a,b,c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][][] dp = new int[102][102][102];
		for(int i=0; i<=101; i++) {
			for(int j=0; j<=101; j++) {
				for(int k=0; k<=101; k++) {
					if(i<=50 || j<=50 || k<=50) {
						dp[i][j][k] = 1;
						continue;
					}else if(i>70 || j>70 || k>70) {
						continue;
					}else if(i<j && j<k) {
						dp[i][j][k] = dp[i][j][k-1] + dp[i][j-1][k-1] - dp[i][j-1][k]; 
					}else {
						dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-1][k] + dp[i-1][j][k-1] - dp[i-1][j-1][k-1];
					}
					
				}
			}
		}		
		
		while(true) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			a = Integer.parseInt(tk.nextToken());
			b = Integer.parseInt(tk.nextToken());
			c = Integer.parseInt(tk.nextToken());
			if(a==-1 && b==-1 && c==-1) break;
			int ans = dp[a+50][b+50][c+50];
			if(a<=0 || b<=0 || c<=0) {
			}else if(a>20 || b>20 || c>20) {
				ans = dp[70][70][70];
			}
			sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(ans).append("\n");
		}
		System.out.println(sb.toString());

	}

}