package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_16920_확장게임 {
	static int N,M,P,num;
	static int[] S, ans;
	static char[][] map;
	static ArrayDeque<int[]>[] next;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		P = Integer.parseInt(tk.nextToken());
		S = new int[P+1];
		ans = new int[P+1];
		map = new char[N][M];
		next = new ArrayDeque[P+1];
		num = N*M;
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<P+1; i++) {
			S[i] = Integer.parseInt(tk.nextToken());
			next[i] = new ArrayDeque<int[]>();
		}
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				char cur = str.charAt(j);
				map[i][j] = cur;
				if(cur != '.' && cur != '#') {
					ans[cur-'0']++;
					next[cur-'0'].offer(new int[]{i,j});
				}
				if(cur == '#') {
					num--;
				}
			}
		}
		while(sum() < num) {
			boolean check = true;
			for(int i=1; i<P+1; i++) {
				if(!next[i].isEmpty()) check = false;
				bfs(i);
			}
			if(check) break; 
		}
		
		for(int i=1; i<P+1; i++){
			System.out.print(ans[i]+ " ");
		}
		
		
		
	}
	private static void bfs(int p) {
		for(int i=0; i<S[p]; i++) {
			int size = next[p].size();
			if(size == 0) break;
			for(int j=0; j<size; j++) {
				int[] cur = next[p].poll();
				for(int k=0; k<4; k++) {
					int tr = cur[0] + dir[k][0];
					int tc = cur[1] + dir[k][1];
					if(tr<0 || tr>N-1 || tc<0 || tc>M-1 || map[tr][tc] != '.') continue;
					map[tr][tc] = (char)(p+'0');
					ans[p]++;
					next[p].offer(new int[] {tr,tc});
				}
			}
		}		
	}
	private static int sum() {
		int sum = 0;
		for(int i=1; i<P+1; i++) {
			sum += ans[i];
		}
		return sum;
	}

}
