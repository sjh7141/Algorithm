package gold1;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
//다익스트라, 2차원
public class Main_13308_주유소 { 
	static class Node implements Comparable<Node>{ 
		int num, min; 
		long cost; 
 
		public Node(int num, long cost, int min) { 
			this.num = num; 
			this.cost = cost; 
			this.min = min; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return Long.compare(cost, o.cost); 
		} 
		 
	} 
	static int N,M; 
	static int[] station; 
	static ArrayList<Node>[] map;  
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		station = new int[N+1]; 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=1; i<N+1; i++) { 
			station[i] = Integer.parseInt(tk.nextToken()); 
		} 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2, l, 0)); 
			map[n2].add(new Node(n1, l, 0)); 
		} 
		 
		long ans = dijk(); 
		System.out.println(ans); 
 
	} 
	private static long dijk() { 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		long[][] dist = new long[N+1][2501]; 
		for(int i=0; i<N+1; i++) { 
			Arrays.fill(dist[i], Long.MAX_VALUE); 
		} 
		dist[1][station[1]] = 0; 
		boolean[][] visit = new boolean[N+1][2501]; 
		 
		pq.add(new Node(1, 0, station[1])); 
		while(!pq.isEmpty()) { 
			int cur = pq.peek().num; 
			int price = pq.peek().min; 
			long cost = pq.peek().cost; 
			pq.poll(); 
			if(visit[cur][price]) continue; 
			visit[cur][price] = true; 
		for(Node temp : map[cur]) { 
				int next = temp.num; 
				long weight = temp.cost; 
				if(!visit[next][price] && dist[next][price] > cost + weight*price) { 
					dist[next][price] = cost + weight*price; 
					pq.offer(new Node(next, dist[next][price], Math.min(price, station[next]))); 
				} 
			} 
		} 
		 
		long ans = Long.MAX_VALUE; 
		for(int i=1; i<2501; i++) { 
			ans = Math.min(ans, dist[N][i]); 
		} 
		return ans; 
	} 
 
}