package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9944_NXM보드완주하기 {
	static int N,M,No,min,total;
	static char[][] map;
	static boolean[][] visit;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
        while ((str = br.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(str);
        	N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            No++;
			min = Integer.MAX_VALUE;
			total = 0;
            map = new char[N][M];
			visit = new boolean[N][M];
			for(int i=0 ; i<N ; i++) {
				str = br.readLine();
				for(int j=0 ; j<M ; j++) {
					char cur = str.charAt(j);
					if(cur == '.') total++;
					map[i][j] = cur;
				}
			}
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<M ; j++) {
					if(map[i][j] == '.') {
						visit[i][j] = true;
						solve(i,j,0);
						visit[i][j] = false;
					}
				}
			}
			if(min == Integer.MAX_VALUE) min = -1;
			System.out.println("Case "+No+": "+min);
		}
	}
	private static void solve(int r, int c, int step) {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j]) cnt++;
			}
		}
		if(cnt == total) {
			min = Math.min(min, step);
			return;
		}
		for(int k=0; k<4; k++) {
			int iter = 1;
			while(true) {
				int tr = r + dir[k][0] * iter;
				int tc = c + dir[k][1] * iter;
				if(tr<0||tr>N-1||tc<0||tc>M-1||visit[tr][tc]||map[tr][tc]=='*') break;
				visit[tr][tc] = true;
				iter++;
			}

			int nr = r + dir[k][0] * --iter;
			int nc = c + dir[k][1] * iter;

			if(iter != 0) {
				solve(nr,nc,step+1);
				while(iter != 0) {
					nr = r + dir[k][0] * iter;
					nc = c + dir[k][1] * iter--;
					visit[nr][nc] = false;
				}
			}
		}
		
	}

}
