package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
	static int[][] dir = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};
	static int[] row1 = {1,4,6,3};
	static int[] row2 = {2,1,5,6};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		int x = Integer.parseInt(tk.nextToken());
		int y = Integer.parseInt(tk.nextToken());
		int K = Integer.parseInt(tk.nextToken());
		int[][] map = new int[N][M];
		int[] dice = {0,0,0,0,0,0,0};
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<K; i++) {
			int cmd = Integer.parseInt(tk.nextToken());
			int tx = x + dir[cmd][0];
			int ty = y + dir[cmd][1];
			if(tx<0 || tx>N-1 || ty<0 || ty>M-1) continue;
			x = tx;
			y = ty;
			
			int[] temp = dice.clone();
			for(int j=0; j<4; j++) {
				switch(cmd) {
					case 1:
						dice[row1[j]] = temp[row1[(j+3)%4]];
						break;
					case 2:
						dice[row1[j]] = temp[row1[(j+1)%4]];
						break;
					case 3:
						dice[row2[j]] = temp[row2[(j+1)%4]];
						break;
					case 4:
						dice[row2[j]] = temp[row2[(j+3)%4]];
						break;
				}
			}
			
			if(map[x][y] == 0) {
				map[x][y] = dice[6];
			}else {
				dice[6] = map[x][y];
				map[x][y] = 0;
			}
			System.out.println(dice[1]);
		}		
		
		
		
	}

}
