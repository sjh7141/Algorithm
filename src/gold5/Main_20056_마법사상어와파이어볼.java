package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20056_마법사상어와파이어볼 {
	static class FireBall {
		int r,c,m,s,d;

		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}		
		
	}
	static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		int K = Integer.parseInt(tk.nextToken());
		ArrayList<FireBall> fire = new ArrayList<FireBall>(); 
		ArrayList<FireBall> [][] map = new ArrayList[N][N];

		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(br.readLine());
			fire.add(new FireBall(Integer.parseInt(tk.nextToken())-1, Integer.parseInt(tk.nextToken())-1, Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())));
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		
		for(int k=0; k<K; k++) {
			for(int i=0,len = fire.size(); i<len; i++) {
				FireBall cur = fire.get(i);
				cur.r = (N*1000+cur.r+dir[cur.d][0]*cur.s)%N;
				cur.c = (N*1000+cur.c+dir[cur.d][1]*cur.s)%N;
				map[cur.r][cur.c].add(cur);
			}
			ArrayList<FireBall> temp = new ArrayList<FireBall>(); 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int len = map[i][j].size();
					if(len > 1) {
						int m = 0, s = 0;
						boolean isOdd = true, isEven = true;
						for(int n=0; n<len; n++) {
							FireBall cur = map[i][j].get(n);
							m+=cur.m;
							s+=cur.s;
							if(cur.d%2 == 0) isOdd = false;
							else isEven = false;
						}
						if(m/5 != 0) {
							for(int n=0; n<4; n++) {
								temp.add(new FireBall(i, j, m/5, s/len, (isOdd || isEven)? n*2: n*2+1));
							}
						}
					}else {
						for(int n=0; n<len; n++) {
							temp.add(map[i][j].get(n));
						}
					}
					map[i][j].clear();
				}
			}
			fire = temp;
		}
		int ans = 0;
		for(int i=0; i<fire.size(); i++) {
			ans+=fire.get(i).m;
		}
		System.out.println(ans);

	}

}
