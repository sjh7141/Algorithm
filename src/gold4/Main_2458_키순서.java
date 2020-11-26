package gold4;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2458_키순서 {
	static boolean[][] map; 
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		map = new boolean[N+1][N+1];

		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			int s1 = Integer.parseInt(tk.nextToken());
			int s2 = Integer.parseInt(tk.nextToken());
			map[s1][s2] = true;
		}
		
		for(int k=1; k<N+1; k++) {
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(i==j || i==k || j==k) continue;
					if(map[i][k] && map[k][j]) map[i][j] = true;
				}
			}
		}
		
		int cnt = 0;
		for(int i=1; i<N+1; i++) {
			int cur = 0;
			for(int j=1; j<N+1; j++) {
				if(i==j) continue;
				if(map[i][j]||map[j][i]) cur++;
			}
			if(cur==N-1) cnt++;
		}
				
				
		System.out.println(cnt);
		

	}

}
