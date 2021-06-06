package gold2;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 //BFS
public class Main_2917_늑대사냥꾼 { 
	static class Node implements Comparable<Node>{ 
		int r,c,cost,min; 
 
		public Node(int r, int c, int cost) { 
			this.r = r; 
			this.c = c; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return o.cost - cost; 
		} 
	} 
	static int N,M,sr,sc,er,ec; 
	static char[][] map; 
	static int[][] treeDist; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		map = new char[N][M]; 
		treeDist = new int[N][M]; 
		 
		ArrayDeque<int[]> deq = new ArrayDeque<>(); 
		boolean[][] visit = new boolean[N][M]; 
		for(int i=0; i<N; i++) { 
			String str = br.readLine(); 
			for(int j=0; j<M; j++) { 
				map[i][j] = str.charAt(j); 
				if(map[i][j] == '+') { 
					deq.add(new int[] {i,j}); 
					visit[i][j] = true; 
				}else if(map[i][j] == 'V') { 
					sr = i; 
					sc = j; 
				}else if(map[i][j] == 'J') { 
					er = i; 
					ec = j; 
				} 
			} 
		} 
		//bfs: 나무거리 저장 
		int cnt = 0; 
		while(!deq.isEmpty()) { 
			int size = deq.size(); 
			for(int i=0; i<size; i++) { 
				int[] cur = deq.poll(); 
				for(int k=0; k<4; k++) { 
					int tr = cur[0] + dir[k][0]; 
					int tc = cur[1] + dir[k][1]; 
					if(tr < 0 || tr> N-1 || tc < 0 || tc > M-1 || visit[tr][tc]) continue; 
					visit[tr][tc] = true; 
					deq.add(new int[] {tr,tc}); 
					treeDist[tr][tc] = cnt+1; 
				} 
			} 
			cnt++; 
		} 
		int ans = pqbfs(); 
		System.out.println(ans); 
	} 
	private static int pqbfs() { 
		boolean[][] visit = new boolean[N][M]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		pq.add(new Node(sr,sc,treeDist[sr][sc])); 
		 
		int ans = treeDist[sr][sc]; 
		while(!pq.isEmpty()) { 
			int r = pq.peek().r; 
			int c = pq.peek().c; 
			int cost = pq.peek().cost; 
			pq.poll(); 
			ans = Math.min(ans, cost); 
			if(r==er && c==ec) { 
				break; 
			} 
			for(int k=0; k<4; k++) { 
				int tr = r + dir[k][0]; 
				int tc = c + dir[k][1]; 
				if(tr < 0 || tr> N-1 || tc < 0 || tc > M-1) continue; 
				if(!visit[tr][tc]) { 
					visit[tr][tc] = true; 
					pq.offer(new Node(tr,tc,treeDist[tr][tc])); 
				} 
			} 
		} 
		return ans; 
	} 
 
}