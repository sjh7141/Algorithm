package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리조작 {
	static int N, M, H, ans;
	static boolean[][] ladd;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		H = Integer.parseInt(tk.nextToken());
		ans = 4;
		
		
		ladd = new boolean[H+1][N];
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			ladd[Integer.parseInt(tk.nextToken())][Integer.parseInt(tk.nextToken())] = true;
		}
		
		solve(1,1,0);
		if(ans>3) ans = -1; 
		System.out.println(ans);

	}
	private static void solve(int r, int c, int cnt) {
		if(cnt >= ans) {
			return;
		}else if(cnt == 3 || r > H) {
			if(check()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		int tr = (c+1>N-1) ? r+1 : r;
		int tc = (c+1>N-1) ? 1 : c+1;
		//고르지 않을 때
		solve(tr,tc,cnt);
		
		if(!ladd[r][c]) {
			//고를 때
			boolean flag = true;
			if((c-1>0 && ladd[r][c-1]) || (c+1<N && ladd[r][c+1])) flag = false;
			if(flag) {
				ladd[r][c] = true;
				solve(tr,tc,cnt+1);
				ladd[r][c] = false;
			}
		}		
		
	}
	private static boolean check() {
		for(int c=1; c<N+1; c++) {
			int cur = c;
			for(int r=1; r<H+1; r++) {
				if(cur>1 && ladd[r][cur-1]) {
					cur--;
				}else if(cur<N && ladd[r][cur]) {
					cur++;
				}
			}
			if(cur!=c) return false;
		}

		return true;
	}

}
