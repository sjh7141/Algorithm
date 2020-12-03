package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
	static int N,M,ans;
	static int[][] map;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visit[i][j] = true;
				find(i,j,1,map[i][j]);
				visit[i][j] = false;
				if(i-1 > -1 && j+2<M) {
					int temp = map[i][j] + map[i][j+1] + map[i][j+2] + map[i-1][j+1];
					ans = Math.max(ans, temp);
				}
				if(i+1 < N && j+2 <M) {
					int temp = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1];
					ans = Math.max(ans, temp);
				}
				if(j-1 > -1 && i+2<N) {
					int temp = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j-1];
					ans = Math.max(ans, temp);
				}
				if(j+1 <M && i+2<N) {
					int temp = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j+1];
					ans = Math.max(ans, temp);
				}
			}
		}
		System.out.println(ans);
	}
	private static void find(int r, int c, int len, int sum) {
		if(len == 4) {
			ans = Math.max(ans,sum);
			return;
		}
		for(int k=0; k<4; k++) {
			int tr = r + dir[k][0];
			int tc = c + dir[k][1];
			if(tr<0||tr>N-1||tc<0||tc>M-1||visit[tr][tc]) continue;
			visit[tr][tc] = true;
			find(tr,tc,len+1,sum+map[tr][tc]);
			visit[tr][tc] = false;
		}
		
	}
}
