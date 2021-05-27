package gold2;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main_2397_도로검문 { 
	static class Node implements Comparable<Node>{ 
		int num, cost; 
		 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
		 
	} 
	static int N,M; 
	static ArrayList<Node>[] map; 
	static ArrayList<int[]> banList; 
	static int[] pre; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[N+1]; 
		banList = new ArrayList<>(); 
		pre = new int[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2, l)); 
			map[n2].add(new Node(n1, l)); 
		} 
		 
		int init = dijk(new int[] {0,0}); 
		int chase = N; 
		while(chase != 1) { 
			banList.add(new int[] {pre[chase],chase}); 
			chase = pre[chase]; 
		} 
		 
 
		int ans = 0; 
		for(int[] temp : banList) { 
			ans = Math.max(ans, dijk(temp)); 
		} 
		 
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans - init); 
		 
		 
	} 
	private static int dijk(int[] ban) { 
		PriorityQueue<Node> pq = new PriorityQueue<>();  
		Node[] dist = new Node[N+1];  
		for(int i=1; i<N+1; i++) {  
			dist[i] = new Node(i, (i==1) ? 0 : Integer.MAX_VALUE);  
			pq.add(dist[i]);  
		}  
		  
		while(!pq.isEmpty()) {  
			int cur = pq.peek().num;  
			int cost = pq.peek().cost;  
			if(cost == Integer.MAX_VALUE) break;  
			pq.poll();  
			for(Node temp : map[cur]) {  
				int next = temp.num;  
				int weight = temp.cost;  
				if(cur == ban[0] && next == ban[1] || cur == ban[1] && next == ban[0]) continue;  
				if(pq.contains(dist[next]) && dist[next].cost > cost + weight) {  
					pq.remove(dist[next]);  
					dist[next].cost = cost + weight; 
					if(ban[0] == 0 && ban[1] == 0) pre[next] = cur; 
					pq.add(dist[next]);  
				}  
			}  
		}  
 		return dist[N].cost;  
	} 
 
}