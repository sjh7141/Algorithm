package platinum5;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
//다익스트라 응용, 우선순위큐 
public class Main_1854_K번째최단거리찾기 { 
	static class Node implements Comparable<Node>{ 
		int num,cost; 
		 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
		 
		 
		 
	} 
	static int N,M,K; 
	static ArrayList<Node>[] map; 
	static PriorityQueue<Integer>[] dist; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[N+1]; 
		dist = new PriorityQueue[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
			dist[i] = new PriorityQueue<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(tk.nextToken()); 
			int b = Integer.parseInt(tk.nextToken()); 
			int c = Integer.parseInt(tk.nextToken()); 
			map[a].add(new Node(b, c)); 
		} 
		 
		dijk(); 
		 
		for(int i=1; i<N+1; i++) { 
			System.out.println((dist[i].size() == K) ? (dist[i].poll() * -1) : -1); 
		} 
		 
	} 
	private static void dijk() { 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		pq.add(new Node(1, 0)); 
        dist[1].add(0);
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			for(Node next : map[cur.num]) { 
				int weight = cur.cost + next.cost; 
				if(dist[next.num].size() < K) { 
					dist[next.num].add(weight * -1); 
					pq.offer(new Node(next.num, weight)); 
				}else { 
					if(dist[next.num].peek() * -1 > weight) { 
						dist[next.num].poll(); 
						dist[next.num].add(weight * -1); 
						pq.offer(new Node(next.num, weight)); 
					} 
				} 
			} 
		} 
		 
	} 
 
}