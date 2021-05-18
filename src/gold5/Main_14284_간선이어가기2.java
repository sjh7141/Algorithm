package gold5;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main_14284_간선이어가기2 { 
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
	static int n,m,s,t; 
	static ArrayList<Node>[] map; 
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(tk.nextToken()); 
		m = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[n+1]; 
		for(int i=0; i<n+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<m; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int cost = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2,cost)); 
			map[n2].add(new Node(n1,cost)); 
		} 
		 
		tk = new StringTokenizer(br.readLine()); 
		s = Integer.parseInt(tk.nextToken()); 
		t = Integer.parseInt(tk.nextToken()); 
		 
		System.out.println(dijk()); 
 
	} 
	private static int dijk() { 
		Node[] costs = new Node[n+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<n+1; i++) { 
			costs[i] = new Node(i, (i==s) ? 0 : Integer.MAX_VALUE); 
			pq.offer(costs[i]); 
		} 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Integer.MAX_VALUE) break; 
			for(Node temp : map[cur.num]) {  
				if(pq.contains(costs[temp.num]) && costs[temp.num].cost > cur.cost + temp.cost) { 
					pq.remove(costs[temp.num]); 
					costs[temp.num].cost = cur.cost + temp.cost; 
					pq.add(costs[temp.num]); 
				} 
			} 
		} 
		 
		return costs[t].cost; 
	} 
 
}