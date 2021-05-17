package gold5;
 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.StringTokenizer; 
 
 
public class Main_14503_로봇청소기 { 
	static int N,M,r,c,d,ans; 
	static int[][] map; 
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; 
	static boolean[][] isClean; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		tk = new StringTokenizer(br.readLine()); 
		r = Integer.parseInt(tk.nextToken()); 
		c = Integer.parseInt(tk.nextToken()); 
		d = Integer.parseInt(tk.nextToken()); 
		map = new int[N][M]; 
		isClean = new boolean[N][M]; 
		for(int i=0; i<N; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			for(int j=0; j<M; j++) { 
				int temp = Integer.parseInt(tk.nextToken()); 
				map[i][j] = temp; 
			} 
		} 
		 
		boolean flag = true; 
		OUT: 
		while(true) { 
			//1번 
			if(flag) { 
				isClean[r][c] = true; 
				ans++; 
			} 
			//2번 
			for(int i=0; i<4; i++) { 
				int tr = r + dir[(d+3)%4][0]; 
				int tc = c + dir[(d+3)%4][1]; 
				if(tr < 0 || tc < 0 || tr > N-1 || tc > M-1 || map[tr][tc] == 1 || isClean[tr][tc]) { 
					d = (d+3)%4; 
					continue; 
				}else { 
					r = tr; 
					c = tc; 
					d = (d+3)%4; 
					flag = true; 
					continue OUT; 
				} 
			} 
			int tr = r + dir[(d+2)%4][0]; 
			int tc = c + dir[(d+2)%4][1]; 
			if(tr < 0 || tc < 0 || tr > N-1 || tc > M-1 || map[tr][tc] == 1) { 
				break; 
			}else { 
				r = tr; 
				c = tc; 
				flag = false; 
			} 
			 
			 
		} 
		 
		System.out.println(ans); 
 
	} 
 
}