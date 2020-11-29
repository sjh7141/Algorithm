package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위의로봇 {
	static class Belt {
		int limit;
		boolean isRobot;
		
		public Belt(int limit, boolean isRobot) {
			this.limit = limit;
			this.isRobot = isRobot;
		}
	}
	static int ans, cnt, N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		tk = new StringTokenizer(br.readLine());
		int len = 2*N;
		Belt[] A = new Belt[len];
		for(int i=0; i<len; i++) {
			A[i] = new Belt(Integer.parseInt(tk.nextToken()), false);
		}
		int up = 0, down = N-1; 		
	
		ans = 1;
		
		while(true) {
			up = (up-1+len)%len;
			down = (down-1+len)%len;
			A[down].isRobot = false;
			for(int i=0; i<N; i++) {
				int pos = (down-i+len)%len;
				Belt cur = A[pos];
				if(cur.isRobot) {
					Belt next = A[(pos+1)%len];
					if(!next.isRobot && next.limit > 0) {
						cur.isRobot = false;
						next.isRobot = true;
						next.limit--;
						if(next.limit == 0) cnt++;
						if((pos+1)%len == down)
							next.isRobot = false;
						
					}
				}
			}
			
			Belt cur = A[up];
			if(!cur.isRobot && cur.limit > 0) {
				cur.isRobot = true;
				cur.limit--;
				if(cur.limit == 0) cnt++;
			}
			
			if(cnt >= K) break;
			ans++;
		}
		
		System.out.println(ans);		

	}

}
