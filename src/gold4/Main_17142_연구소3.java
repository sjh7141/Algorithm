package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_17142_연구소3 {
	static class Pos{
		int r,c,cnt;
		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int N,M,min,idx;
	static int[][] map;
	static Pos[] vir;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[] combi = new int[10];
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(bf.readLine(), " ");
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new int[N][N];
		vir = new Pos[10];
		min = N*N+1;
		//입력값, 바이러스 위치
		for(int i =0; i<N; i++)	{
			token = new StringTokenizer(bf.readLine(), " ");
			for(int j =0; j<N; j++) {
				int a = Integer.parseInt(token.nextToken());
				map[i][j] = a;
				if(a == 2)
					vir[idx++] = new Pos(i,j,0);
			}
		}
		//조합
		for(int i =0,size = 1<<idx; i<size; i++) {
			int k = 0;
			for(int j =0; j<idx; j++) {
				if((i&1<<j) != 0){
					combi[k++] = j; 
				}
			}
			if(k==M) {
				int temp = bfs();
				if(temp != -1 && temp < min)
					min = temp;
			}
		}
			
		if(min > N*N) min = -1;
		System.out.println(min);

	}
	
	public static int bfs() {
		ArrayDeque<Pos> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		for(int i=0; i<M; i++) {
			queue.offer(vir[combi[i]]);
			visited[vir[combi[i]].r][vir[combi[i]].c] = true;
		}
		int max = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			Pos temp = queue.poll();
				
			for(int i =0; i<4; i++) {
				int tr = temp.r + dir[i][0];
				int tc = temp.c + dir[i][1];
				if(tr < 0 || tr >N-1 || tc < 0 || tc > N-1 || visited[tr][tc]) 
					continue;
				
				if(map[tr][tc] == 0) {
					visited[tr][tc] = true; 
					queue.add(new Pos(tr,tc, temp.cnt+1));
					if(temp.cnt+1 >max)
						max = temp.cnt+1;
				}
				else if(map[tr][tc] == 2) {
					visited[tr][tc] = true;
					queue.add(new Pos(tr,tc, temp.cnt+1));
				}
				
			}
			
			
		}
		//탐색못한곳있는지
		OUT:
		for(int i =0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0 && visited[i][j] == false) {
					max = -1;
					break OUT;
				}
			}
		}
		return max;
		
	}
}