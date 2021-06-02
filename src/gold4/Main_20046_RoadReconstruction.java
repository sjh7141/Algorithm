package gold4;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
// 다익스트라, 2차원
public class Main_20046_RoadReconstruction { 
	static class Node implements Comparable<Node>{ 
		int r,c,cost; 
		 
		public Node(int r, int c, int cost) { 
			this.r = r; 
			this.c = c; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
		 
	} 
	static int N,M; 
	static int[][] map; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	static ArrayList<Node>[][] list;  
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		map = new int[N][M]; 
		for(int i=0; i<N; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			for(int j=0; j<M; j++) { 
				map[i][j] = Integer.parseInt(tk.nextToken()); 
			} 
		} 
		 
		list = new ArrayList[N][M]; 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) { 
				list[i][j] = new ArrayList<>(); 
			} 
		} 
		 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) { 
				for(int k=0; k<4; k++) { 
					int tr = i + dir[k][0]; 
					int tc = j + dir[k][1]; 
					if(tr<0 || tc<0 || tr>N-1 || tc>M-1 || map[tr][tc] == -1) continue; 
					list[i][j].add(new Node(tr,tc,map[tr][tc])); 
				} 
			} 
		} 
		 
		 
		int ans = dijk(); 
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans); 
 
	} 
	private static int dijk() { 
		int[][] dist = new int[N][M]; 
		for(int i=0; i<N; i++) { 
			Arrays.fill(dist[i], Integer.MAX_VALUE); 
		} 
		dist[0][0] = map[0][0]; 
		if(dist[0][0] == -1) return Integer.MAX_VALUE; 
		 
		boolean[][] visit = new boolean[N][M]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		pq.add(new Node(0, 0, dist[0][0])); 
		 
		while(!pq.isEmpty()) { 
			int r = pq.peek().r; 
			int c = pq.peek().c; 
			int cost = pq.peek().cost; 
			pq.poll(); 
			if(cost == Integer.MAX_VALUE) break; 
			if(visit[r][c]) continue; 
			visit[r][c] = true; 
			for(Node temp : list[r][c]) { 
				int nr = temp.r; 
				int nc = temp.c; 
				int weight = temp.cost; 
				if(!visit[nr][nc] && dist[nr][nc] > cost + weight) { 
					dist[nr][nc] = cost + weight; 
					pq.add(new Node(nr, nc, dist[nr][nc])); 
				} 
			} 
		} 
		return dist[N-1][M-1]; 
	} 
 
}