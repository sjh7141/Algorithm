package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2668_숫자고르기 {
	static int N, ans;
	static int[] nums;
	static boolean[] visit, backUp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N+1];
		visit = new boolean[N+1];
		backUp = new boolean[N+1];
		for(int i=1; i<N+1; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1; i<N+1; i++) {
			dfs(i,i,0);
		}
		
		System.out.println(ans);
		for(int i=1; i<N+1; i++) {
			if(backUp[i]) System.out.println(i);
		}
	}
	private static void dfs(int n, int s, int cnt) {
		if(backUp[n]) return;
		if(visit[n]) {
			if(n==s) {
				ans += cnt;
				for(int i=1; i<N+1; i++) {
					if(visit[i]) backUp[i] = true;
				}
			}
			return;
		}
		visit[n] = true;
		dfs(nums[n], s, cnt+1);
		visit[n] = false;
		
	}

}
