package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1743_음식물피하기 {
	static int[][] map;
	static boolean[][] visit;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static int N,M,K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<K; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(tk.nextToken()) - 1;
			int c = Integer.parseInt(tk.nextToken()) - 1;
			map[r][c]++;
		}
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0 && !visit[i][j]) {
					visit[i][j] = true;
					max = Math.max(max, dfs(i,j));
				}
			}
		}
		System.out.println(max);

	}
	private static int dfs(int r, int c) {
		int sum = 0;
		for(int k=0; k<4; k++) {
			int tr = r + dir[k][0];
			int tc = c + dir[k][1];
			if(tr<0||tr>N-1||tc<0||tc>M-1||map[tr][tc]==0||visit[tr][tc]) continue;
			visit[tr][tc] = true;
			sum+=dfs(tr,tc);
		}
		return map[r][c] + sum;
		
	}

}
