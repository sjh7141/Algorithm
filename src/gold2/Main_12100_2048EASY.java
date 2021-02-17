package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main_12100_2048EASY {
	static int N;
	static int[][] map;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};//RDLU
	static int[] arr = new int[5];
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		//비트마스킹
		for(int k=0, size=1<<10;k<size; k++) {
			int temp = k;
			for(int i=0;i<5; i++) {
				arr[i] = (temp&3);
				temp >>= 2;
			}
			int cur = simulate();
			max = Math.max(max, cur);
		}
		
		System.out.println(max);
	}
	private static int simulate() {
		int[][] temp = new int[N][N];
		for(int i=0; i<N; i++) {//복사
			for(int j=0; j<N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		for(int k=0; k<5; k++) {//5번 시뮬 RDLU
			boolean[][] isconcat =new boolean[N][N];
			if(arr[k]==0) {
				for(int i=N-1; i>=0; i--) {
					for(int j=0; j<N; j++) {
						if(temp[j][i] == 0)
							continue;
						int r = j;
						int c = i;
						while(true) {
							int tr = r + dir[arr[k]][0];
							int tc = c + dir[arr[k]][1];
							if(tr<0|| tc<0 || tr>N-1 || tc>N-1){
								break;
							}
							if(temp[tr][tc] == 0) {
								temp[tr][tc] = temp[r][c];
								temp[r][c] = 0;
								r = tr;
								c = tc;
							}else if(temp[tr][tc] == temp[r][c] && !isconcat[tr][tc]) {
								temp[tr][tc] *= 2;
								temp[r][c] = 0;
								isconcat[tr][tc] = true;
								break;
							}else {
								break;
							}
						}
					}
				}
			}else if(arr[k]==1) {
				for(int i=N-1; i>=0; i--) {
					for(int j=0; j<N; j++) {
						if(temp[i][j] == 0)
							continue;
						int r = i;
						int c = j;
						while(true) {
							int tr = r + dir[arr[k]][0];
							int tc = c + dir[arr[k]][1];
							if(tr<0|| tc<0 || tr>N-1 || tc>N-1){
								break;
							}
							if(temp[tr][tc] == 0) {
								temp[tr][tc] = temp[r][c];
								temp[r][c] = 0;
								r = tr;
								c = tc;
							}else if(temp[tr][tc] == temp[r][c] && !isconcat[tr][tc]) {
								temp[tr][tc] *= 2;
								temp[r][c] = 0;
								isconcat[tr][tc] = true;
								break;
							}else {
								break;
							}
						}
						
					}
				}
			}else if(arr[k]==2) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(temp[j][i] == 0)
							continue;
						int r = j;
						int c = i;
						while(true) {
							int tr = r + dir[arr[k]][0];
							int tc = c + dir[arr[k]][1];
							if(tr<0|| tc<0 || tr>N-1 || tc>N-1){
								break;
							}
							if(temp[tr][tc] == 0) {
								temp[tr][tc] = temp[r][c];
								temp[r][c] = 0;
								r = tr;
								c = tc;
							}else if(temp[tr][tc] == temp[r][c] && !isconcat[tr][tc]) {
								temp[tr][tc] *= 2;
								temp[r][c] = 0;
								isconcat[tr][tc] = true;
								break;
							}else {
								break;
							}
						}
						
					}
				}
			}else {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(temp[i][j] == 0)
							continue;
						int r = i;
						int c = j;
						while(true) {
							int tr = r + dir[arr[k]][0];
							int tc = c + dir[arr[k]][1];
							if(tr<0|| tc<0 || tr>N-1 || tc>N-1){
								break;
							}
							if(temp[tr][tc] == 0) {
								temp[tr][tc] = temp[r][c];
								temp[r][c] = 0;
								r = tr;
								c = tc;
							}else if(temp[tr][tc] == temp[r][c] && !isconcat[tr][tc]) {
								temp[tr][tc] *= 2;
								temp[r][c] = 0;
								isconcat[tr][tc] = true;
								break;
							}else {
								break;
							}
						}
						
						
					}
				}
			}
			
		}
		int tmax = 0;
		for(int i=0;i<N;i++) {
			for(int j=0; j<N; j++) {
				tmax = Math.max(tmax, temp[i][j]);
			}
		}
		return tmax;
	}

}