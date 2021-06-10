package gold5;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.StringTokenizer; 
 
public class Main_14497_주난의난 { 
	static int N,M,x1,y1,x2,y2; 
	static int[][] map; 
	static boolean[][] visit; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		tk = new StringTokenizer(br.readLine()); 
		x1 = Integer.parseInt(tk.nextToken())-1; 
		y1 = Integer.parseInt(tk.nextToken())-1; 
		x2 = Integer.parseInt(tk.nextToken())-1; 
		y2 = Integer.parseInt(tk.nextToken())-1; 
		map = new int[N][M]; 
		visit = new boolean[N][M]; 
		for(int i=0; i<N; i++) { 
			String str = br.readLine(); 
			for(int j=0; j<M; j++) { 
				char cur = str.charAt(j); 
				if(cur == '1' || cur == '0') { 
					map[i][j] = cur - '0'; 
				}else { 
					map[i][j] = 1; 
				} 
			} 
		} 
		 
		int ans = bfs(); 
		System.out.println(ans); 
	} 
	private static int bfs() { 
		ArrayDeque<int[]> deq = new ArrayDeque<>(); 
		deq.add(new int[] {x1,y1}); 
		int cnt = 0; 
		visit[x1][y1] = true; 
		OUT: 
		while(!deq.isEmpty()) { 
			int size = deq.size(); 
			for(int i=0; i<size; i++) { 
				int[] cur = deq.poll(); 
				if(cur[0] == x2 && cur[1] == y2) break OUT; 
				for(int k=0; k<4; k++) { 
					int tr = cur[0] + dir[k][0]; 
					int tc = cur[1] + dir[k][1]; 
					if(tr < 0 || tc < 0 || tr > N-1 || tc > M-1 || visit[tr][tc]) continue; 
					visit[tr][tc] = true; 
					if(map[tr][tc] == 0) { 
						deq.addFirst(new int[] {tr,tc}); 
						size++; 
					}else if(map[tr][tc] == 1) { 
						deq.addLast(new int[] {tr,tc}); 
					} 
				} 
			}
            cnt++;
		} 
		return cnt; 
	} 
 
}