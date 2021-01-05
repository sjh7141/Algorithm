package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11052_카드구매하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] price = new int[N+1];
		int[] dp = new int[N+1];
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<N+1; i++) {
			price[i] = Integer.parseInt(tk.nextToken());
			dp[i] = price[i];
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<=i; j++) {
				if(i+j>=N+1) continue;
				price[i+j] = Integer.max(price[i+j], price[i] + price[j]);
			}
		}
		
		System.out.println(price[N]);

	}

}
