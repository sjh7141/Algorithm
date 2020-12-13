package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_19237_어른상어 {
	static class Shark {
		int r, c, d;
		int [][] prio;
		public Shark(int r, int c, int d, int[][] prio) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.prio = prio;
		}
		
	}
	static int N,M,K;
	static int[][][] map;
	static ArrayList[][] colli;
	static Shark[] shark;
	static int[][] dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		map = new int[N][N][2];
		colli = new ArrayList[N][N];
		shark = new Shark[M+1];
				
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(tk.nextToken());
				map[i][j][0] = n;
				map[i][j][1] = 0;
				if(n != 0) {
					shark[n] = new Shark(i,j,0,new int[5][4]);
					map[i][j][1] = K;
				}
				
				colli[i][j] = new ArrayList<Integer>();
			}
		}
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<M+1; i++) {
			shark[i].d = Integer.parseInt(tk.nextToken());
		}
		for(int i=1; i<M+1; i++) {			
			for(int j=1; j<5; j++) {
				tk = new StringTokenizer(br.readLine(), " ");
				for(int k=0; k<4; k++) {
					shark[i].prio[j][k] = Integer.parseInt(tk.nextToken());
				}
			}
		}
		int num = M;
		int ans = 0;
		
		while(ans < 1001 && num > 1) {
			//상어 이동
			for(int i=1; i<M+1; i++) {
				Shark cur = shark[i];
				if(cur.d == 0) continue;
				ArrayList<Integer> empty = new ArrayList<Integer>();
				ArrayList<Integer> mine = new ArrayList<Integer>();
				for(int k=1; k<5; k++) {
					int tr = cur.r + dir[k][0];
					int tc = cur.c + dir[k][1];
					if(tr<0 || tr>N-1 || tc<0 || tc>N-1) continue;
					if(map[tr][tc][0] == 0) { 
						empty.add(k);
					}else if(map[tr][tc][0] == i) {
						mine.add(k);
					}										
				}
				
				if(!empty.isEmpty()) {
					for(int j=0; j<4; j++) {
						int go = cur.prio[cur.d][j];
						if(empty.contains(go)){
							cur.r += dir[go][0];
							cur.c += dir[go][1];
							colli[cur.r][cur.c].add(i);
							cur.d = go;
							break;
						}
					}
				}else if(!mine.isEmpty()) {
					for(int j=0; j<4; j++) {
						int go = cur.prio[cur.d][j];
						if(mine.contains(go)){
							cur.r += dir[go][0];
							cur.c += dir[go][1];
							colli[cur.r][cur.c].add(i);
							cur.d = go;
							break;
						}
					}
				}
			}
			//충돌+냄새처리
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j][0] != 0) {
						map[i][j][1] --;
						if(map[i][j][1] == 0) {
							map[i][j][0] = 0;
						}
					}
					
					if(!colli[i][j].isEmpty()) {
						Collections.sort(colli[i][j]);
						int alive = (int)colli[i][j].get(0);
						map[i][j][0] = alive;
						map[i][j][1] = K;
						for(int k=1; k<colli[i][j].size(); k++) {
							shark[(int)colli[i][j].get(k)].d = 0;
							num--;
						}
						colli[i][j].clear();
					}
				}
			}
			
			ans++;
		}
		
		if(ans > 1000) ans = -1;
		System.out.println(ans);
		
	}

}
