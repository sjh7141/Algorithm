package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_4179_불 {
	static int R, C, JR, JC;
	static char[][] map;
	static boolean[][] visit;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		ArrayDeque<int[]> fqueue = new ArrayDeque<>();
		for(int i=0; i<R; i++) {
			String[] str= br.readLine().split("");
			for(int j=0; j<C; j++) {
				map[i][j] = str[j].charAt(0);
				if(map[i][j] == 'J') {
					JR = i;
					JC = j;
					map[i][j] = '.';
					queue.offer(new int[] {JR,JC});
				}
				if(map[i][j] == 'F') {
					fqueue.offer(new int[] {i,j});
				}
			}
		}
		visit[JR][JC] = true;
		int cnt = 0;
		boolean flag = false;
		OUT:
		while(!queue.isEmpty()) {
			int size = fqueue.size();
			for(int i=0; i<size; i++) {
				int[] temp = fqueue.poll();
				for(int k=0; k<4; k++) {
					int tfr = temp[0] + dir[k][0];
					int tfc = temp[1] + dir[k][1];
					if(tfr<0 || tfc<0 || tfr>R-1 || tfc>C-1 || map[tfr][tfc] != '.') continue;
					map[tfr][tfc] = 'F';
					fqueue.offer(new int[] {tfr,tfc});
				}
			}
			size = queue.size();
			for(int i=0; i<size; i++) {
				int[] temp = queue.poll();
				for(int k=0; k<4; k++) {
					int tjr = temp[0] + dir[k][0];
					int tjc = temp[1] + dir[k][1];
					if(tjr<0 || tjc<0 || tjr>R-1 || tjc>C-1) {
						flag = true;
						cnt++;
						break OUT;
					}
					if(map[tjr][tjc] == '#' || map[tjr][tjc] == 'F'|| visit[tjr][tjc]) continue;
					visit[tjr][tjc] = true;
					queue.offer(new int[] {tjr,tjc});
				} 
			}
			cnt++;
		}
		
		if(!flag) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(cnt);
		}
		

	}

}
