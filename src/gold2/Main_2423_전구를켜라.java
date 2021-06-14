package gold2;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
// 다익스트라, 2차원
public class Main_2423_전구를켜라 { 
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
	static char[][] map; 
	static ArrayList<Node>[][] edge; 
	static int[][] dir = {{1,-1},{1,1}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		map = new char[N][M]; 
		edge = new ArrayList[N+1][M+1]; 
		for(int i=0; i<N+1; i++) { 
			for(int j=0; j<M+1; j++) { 
				edge[i][j] = new ArrayList<>(); 
			} 
		} 
		for(int i=0; i<N; i++) { 
			String str = br.readLine(); 
			map[i] = str.toCharArray(); 
			for(int j=0; j<M; j++) { 
				int v1,v2; 
				if(map[i][j] == '/') { 
					v1 = 1; 
					v2 = 0; 
				}else { 
					v1 = 0; 
					v2 = 1; 
				} 
				edge[i][j].add(new Node(i+1, j+1, v1)); 
				edge[i+1][j+1].add(new Node(i, j, v1)); 
				edge[i][j+1].add(new Node(i+1, j, v2)); 
				edge[i+1][j].add(new Node(i, j+1, v2)); 
			} 
		} 
		 
		int ans = dijk(); 
		System.out.println((ans == Integer.MAX_VALUE) ? "NO SOLUTION" : ans); 
		 
	} 
	private static int dijk() { 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		boolean[][] visit = new boolean[N+1][M+1]; 
		int[][] dist = new int[N+1][M+1]; 
		for(int i=0; i<N+1; i++) { 
			Arrays.fill(dist[i], Integer.MAX_VALUE); 
		} 
		dist[0][0] = 0; 
		pq.add(new Node(0, 0, dist[0][0])); 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(visit[cur.r][cur.c]) continue; 
			visit[cur.r][cur.c] = true; 
			for(Node next : edge[cur.r][cur.c]) { 
				if(!visit[next.r][next.c] && dist[next.r][next.c] > cur.cost + next.cost) { 
					dist[next.r][next.c] = cur.cost + next.cost; 
					pq.offer(new Node(next.r, next.c, dist[next.r][next.c])); 
				} 
			} 
		} 
		return dist[N][M]; 
	} 
 
}