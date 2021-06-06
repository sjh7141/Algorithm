package gold2;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
//다익스트라, 2차원
public class Main_1800_인터넷설치 { 
	static class Node implements Comparable<Node>{ 
		int num, cost, cnt; 
 
		public Node(int num, int cost, int cnt) { 
			this.num = num; 
			this.cost = cost; 
			this.cnt = cnt; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
		 
	} 
	static int N,P,K; 
	static ArrayList<Node>[] map; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		P = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<P; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2, l, 0)); 
			map[n2].add(new Node(n1, l, 0)); 
		} 
		 
		int ans = dijk(); 
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans); 
	} 
	private static int dijk() { 
		Node[][] dist = new Node[N+1][K+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<N+1; i++) { 
			for(int j=0; j<K+1; j++) { 
				dist[i][j] = new Node(i, Integer.MAX_VALUE, j); 
				if(i==1 && j == K) dist[i][j].cost = 0; 
				pq.add(dist[i][j]); 
			} 
		} 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Integer.MAX_VALUE) break; 
			for(Node temp : map[cur.num]) { 
				int next = temp.num; 
				int weight = temp.cost; 
				if(pq.contains(dist[next][cur.cnt]) && dist[next][cur.cnt].cost > Math.max(cur.cost, weight)){ 
					pq.remove(dist[next][cur.cnt]); 
					dist[next][cur.cnt].cost = Math.max(cur.cost, weight); 
					pq.add(dist[next][cur.cnt]); 
				} 
				if(cur.cnt > 0 && cur.cost < weight && pq.contains(dist[next][cur.cnt-1]) && dist[next][cur.cnt-1].cost > cur.cost) { 
					pq.remove(dist[next][cur.cnt-1]); 
					dist[next][cur.cnt-1].cost = cur.cost; 
					pq.add(dist[next][cur.cnt-1]); 
				} 
			} 
			 
		} 
		 
		int ans = Integer.MAX_VALUE; 
		for(int i=0; i<K+1; i++) { 
			ans = Math.min(ans, dist[N][i].cost); 
		} 
		 
		return ans; 
	} 
 
}