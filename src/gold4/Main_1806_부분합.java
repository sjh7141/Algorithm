package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806_부분합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(tk.nextToken());
		int S = Integer.parseInt(tk.nextToken());
		
		int [] num = new int[N];
		tk = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(tk.nextToken());
		}
		
		int l = 0, r  = 0, sum = 0, ans = Integer.MAX_VALUE;
		while(l<=r && r<N) {
			if(sum < S) {
				r++;
				sum += num[r];
			}else if(sum == S) {
				ans = Math.min(ans, r-l+1);
				r++;
				sum += num[r];
			}else {
				ans = Math.min(ans, r-l+1);
				sum -= num[l];
				l++;
			}
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
		
	}

}
