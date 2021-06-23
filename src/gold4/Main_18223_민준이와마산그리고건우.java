package gold4;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer;
// 다익스트라, 방문 처리
public class Main_18223_민준이와마산그리고건우 {
	static class Node implements Comparable<Node>{ 
		int num, cost; 
		boolean save; 
		public Node(int num, int cost, boolean save) { 
			this.num = num; 
			this.cost = cost; 
			this.save = save; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
		 
	} 
	static int V,E,P; 
	static ArrayList<Node>[] map;  
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		V = Integer.parseInt(tk.nextToken()); 
		E = Integer.parseInt(tk.nextToken()); 
		P = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[V+1]; 
		for(int i=0; i<V+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<E; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2,l,(n2 == P) ? true : false)); 
			map[n2].add(new Node(n1,l,(n1 == P) ? true : false)); 
		} 
		 
		boolean ans = dijk(); 
		System.out.println((ans) ? "SAVE HIM" : "GOOD BYE"); 
 
	} 
	private static boolean dijk() { 
		Node[] dist = new Node[V+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<V+1; i++) { 
			dist[i] = new Node(i, (i==1) ? 0 : Integer.MAX_VALUE, (i==P) ? true : false); 
			pq.add(dist[i]); 
		} 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Integer.MAX_VALUE) break; 
			for(Node temp : map[cur.num]) { 
				int next = temp.num; 
				if(pq.contains(dist[next]) && dist[next].cost > cur.cost + temp.cost) { 
					pq.remove(dist[next]); 
					dist[next].cost = cur.cost + temp.cost; 
					dist[next].save = cur.save | temp.save; 
					pq.add(dist[next]); 
				}else if(pq.contains(dist[next]) && dist[next].cost == cur.cost + temp.cost && !dist[next].save) { 
					if(!cur.save && !temp.save) continue; 
					pq.remove(dist[next]); 
					dist[next].save = cur.save | temp.save; 
					pq.add(dist[next]); 
				} 
			} 
		} 
		 
		return dist[V].save; 
	} 
 
}