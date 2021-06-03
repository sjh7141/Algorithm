package gold1;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 //다익스트라, 2차원
public class Main_10217_KCMtravel { 
	static class Node implements Comparable<Node>{ 
		int num, money, time; 
 
		public Node(int num, int money, int time) { 
			this.num = num; 
			this.money = money; 
			this.time = time; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return time - o.time; 
		} 
		 
	} 
	static int N,M,K; 
	static ArrayList<Node>[] map;  
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int T = Integer.parseInt(br.readLine()); 
		for(int t=0; t<T; t++) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			N = Integer.parseInt(tk.nextToken()); 
			M = Integer.parseInt(tk.nextToken()); 
			K = Integer.parseInt(tk.nextToken()); 
			map = new ArrayList[N+1]; 
			for(int i=0; i<N+1; i++) { 
				map[i] = new ArrayList<>(); 
			} 
			for(int i=0; i<K; i++) { 
				tk = new StringTokenizer(br.readLine()); 
				int u = Integer.parseInt(tk.nextToken()); 
				int v = Integer.parseInt(tk.nextToken()); 
				int c = Integer.parseInt(tk.nextToken()); 
				int d = Integer.parseInt(tk.nextToken()); 
				map[u].add(new Node(v, c, d)); 
			} 
			 
			int ans = dijk(); 
			System.out.println((ans == Integer.MAX_VALUE) ? "Poor KCM" : ans); 
		} 
		 
 
	} 
	private static int dijk() { 
		int[][] dist = new int[N+1][10001];  
		boolean[][] visit = new boolean[N+1][10001]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<N+1; i++) { 
			Arrays.fill(dist[i], Integer.MAX_VALUE); 
		} 
		dist[1][M] = 0; 
		pq.add(new Node(1, M, dist[1][M])); 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.time == Integer.MAX_VALUE) break; 
			if(visit[cur.num][cur.money]) continue; 
			visit[cur.num][cur.money] = true; 
			for(Node temp : map[cur.num]) { 
				int next = temp.num; 
				int limit = cur.money - temp.money; 
				if(limit >=0 && !visit[temp.num][limit] && dist[temp.num][limit] > cur.time + temp.time) { 
					dist[temp.num][limit] = cur.time + temp.time; 
					pq.add(new Node(next, limit, dist[temp.num][limit])); 
				} 
			} 
		} 
		 
		int ans = Integer.MAX_VALUE; 
		for(int i=0; i<10001; i++) { 
			ans = Math.min(ans, dist[N][i]); 
		} 
		return ans; 
	} 
 
}