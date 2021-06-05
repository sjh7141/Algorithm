import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main { 
	static class Node implements Comparable<Node>{ 
		int r,c,cnt,cost; 
 
		public Node(int r, int c, int cnt, int cost) { 
			this.r = r; 
			this.c = c; 
			this.cnt = cnt; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
	} 
	static int N,T; 
	static int[][] map; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		T = Integer.parseInt(tk.nextToken()); 
		map = new int[N][N]; 
		for(int i=0; i<N; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			for(int j=0; j<N; j++) { 
				map[i][j] = Integer.parseInt(tk.nextToken()); 
			} 
		} 
		System.out.println(dijk()); 
		 
		 
 
	} 
	private static int dijk() { 
		int[][][] dist = new int[N][N][3]; 
		boolean[][][] visit = new boolean[N][N][3]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<N; j++) { 
				Arrays.fill(dist[i][j], Integer.MAX_VALUE); 
			} 
		} 
		dist[0][0][0] = 0; 
		pq.offer(new Node(0,0,0,0)); 
		while(!pq.isEmpty()) { 
			int r = pq.peek().r; 
			int c = pq.peek().c; 
			int cnt = pq.peek().cnt; 
			int cost = pq.peek().cost; 
			pq.poll(); 
			if(visit[r][c][cnt]) continue; 
			visit[r][c][cnt] = true; 
			for(int k=0; k<4; k++) { 
				int tr = r + dir[k][0]; 
				int tc = c + dir[k][1]; 
				if(tr < 0 || tc < 0 || tr > N-1 || tc > N-1) continue; 
				int tcnt = (cnt+1)%3; 
				int weight = (tcnt == 0) ? map[tr][tc] + T : T; 
				if(!visit[tr][tc][tcnt] && dist[tr][tc][tcnt] > cost + weight) { 
					dist[tr][tc][tcnt] = cost + weight; 
					pq.offer(new Node(tr, tc, tcnt, dist[tr][tc][tcnt])); 
				} 
			} 
		} 
		int min = Integer.MAX_VALUE; 
		for(int i=0;i<3; i++) { 
			min = Math.min(min, dist[N-1][N-1][i]); 
		} 
		return min; 
	} 
 
}