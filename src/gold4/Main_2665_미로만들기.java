package gold4;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.PriorityQueue; 
 
public class Main_2665_미로만들기 { 
	static int N,ans; 
	static int[][] map; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	static boolean[][] visit; 
	static class Maze implements Comparable<Maze>{ 
		int r,c,cnt; 
		public Maze(int r, int c, int cnt) { 
			this.r = r; 
			this.c = c; 
			this.cnt = cnt; 
		} 
		@Override 
		public int compareTo(Maze o) { 
			return cnt - o.cnt; 
		} 
		 
	} 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		map = new int[N][N]; 
		visit = new boolean[N][N]; 
		for(int i=0; i<N; i++) { 
			String str = br.readLine(); 
			for(int j=0; j<N; j++) { 
				map[i][j] = str.charAt(j) -'0'; 
			} 
		} 
		 
		PriorityQueue<Maze> pq = new PriorityQueue(); 
		pq.add(new Maze(0,0,0)); 
		 
		while(!pq.isEmpty()) { 
			Maze cur = pq.poll(); 
			visit[cur.r][cur.c] = true; 
			if(cur.r == N-1 && cur.c == N-1) { 
				ans = cur.cnt; 
				break; 
			} 
			for(int k=0; k<4; k++) { 
				int tr = cur.r + dir[k][0]; 
				int tc = cur.c + dir[k][1]; 
				if(tr<0 || tc<0 || tr>N-1 || tc>N-1 || visit[tr][tc]) continue; 
				pq.offer(new Maze(tr, tc, (map[tr][tc] == 0) ? cur.cnt+1 : cur.cnt));			 
			} 
		} 
		 
		System.out.println(ans); 
		 
 
	} 
 
}