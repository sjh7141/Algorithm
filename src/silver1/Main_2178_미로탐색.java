package silver1;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.StringTokenizer; 
 
public class Main_2178_미로탐색 { 
	static int N,M; 
	static int[][] map; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	static boolean[][] visit; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		map = new int[N][M]; 
		visit = new boolean[N][M]; 
		for(int i=0; i<N; i++) { 
			String str = br.readLine(); 
			for(int j=0; j<M; j++) { 
				map[i][j] = str.charAt(j) - '0'; 
			} 
		} 
 
		ArrayDeque<int[]> deq = new ArrayDeque<>(); 
		 
		deq.offer(new int[] {0,0}); 
		visit[0][0] = true; 
		int cnt = 1; 
		OUT: 
		while(!deq.isEmpty()) { 
			int size = deq.size(); 
			for(int i=0; i<size; i++) { 
				int[] cur = deq.poll(); 
				if(cur[0] == N-1 && cur[1] == M-1) break OUT; 
				for(int k=0; k<4; k++) { 
					int tr = cur[0] + dir[k][0]; 
					int tc = cur[1] + dir[k][1]; 
					if(tr<0||tc<0||tr>N-1||tc>M-1||map[tr][tc] == 0||visit[tr][tc]) continue; 
					visit[tr][tc] = true; 
					deq.offer(new int[] {tr,tc}); 
				} 
				 
			} 
			cnt++; 
		} 
		 
		System.out.println(cnt); 
 
	} 
 
}