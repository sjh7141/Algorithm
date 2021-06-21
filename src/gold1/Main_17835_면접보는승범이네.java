package gold1;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 //다익스트라, 가상노드
public class Main_17835_면접보는승범이네 { 
	static class Node implements Comparable<Node>{ 
		int num; 
		long cost; 
 
		public Node(int num, long cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return Long.compare(cost, o.cost); 
		} 
	} 
	static int N,M,K; 
	static ArrayList<Integer> interview; 
	static ArrayList<Node>[] edge; 
	static long[] dist; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
		interview = new ArrayList<>(); 
		edge = new ArrayList[N+1]; 
		dist = new long[N+1]; 
		for(int i=0; i<N+1; i++) { 
			edge[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int U = Integer.parseInt(tk.nextToken()); 
			int V = Integer.parseInt(tk.nextToken()); 
			int C = Integer.parseInt(tk.nextToken()); 
			edge[V].add(new Node(U, C)); 
		} 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=0; i<K; i++) { 
			interview.add(Integer.parseInt(tk.nextToken())); 
		} 
		 
		long[] ans = dijk(); 
		System.out.println(ans[0]); 
		System.out.println(ans[1]); 
 
	} 
	private static long[] dijk() { 
		Arrays.fill(dist, Long.MAX_VALUE); 
		boolean[] visit = new boolean[N+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int num : interview) { 
			dist[num] = 0; 
			pq.add(new Node(num, dist[num])); 
		} 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(visit[cur.num]) continue; 
			visit[cur.num] = true; 
			for(Node next : edge[cur.num]) { 
				if(!visit[next.num] && dist[next.num] > cur.cost + next.cost) { 
					dist[next.num] = cur.cost + next.cost; 
					pq.add(new Node(next.num, dist[next.num])); 
				} 
			} 
		} 
		 
		long[] ans = new long[] {1,-1}; 
		for(int i=1; i<N+1; i++) { 
			if(ans[1] < dist[i]) { 
				ans = new long[] {i,dist[i]}; 
			} 
		} 
		 
		return ans; 
	} 
 
}