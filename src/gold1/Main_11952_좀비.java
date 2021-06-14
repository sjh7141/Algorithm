package gold1;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
//다익스트라, BFS
public class Main_11952_좀비 { 
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
	static int N,M,K,S,P,Q; 
	static boolean[] jombi; 
	static ArrayList<Integer>[] edge; 
	static long[] dist; 
	static long[] pay; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
		S = Integer.parseInt(tk.nextToken()); 
		tk = new StringTokenizer(br.readLine()); 
		P = Integer.parseInt(tk.nextToken()); 
		Q = Integer.parseInt(tk.nextToken()); 
		jombi = new boolean[N+1]; 
		for(int i=0; i<K; i++) { 
			jombi[Integer.parseInt(br.readLine())] = true; 
		} 
		edge = new ArrayList[N+1]; 
		dist = new long[N+1]; 
		pay = new long[N+1]; 
		for(int i=0; i<N+1; i++) { 
			edge[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int U = Integer.parseInt(tk.nextToken()); 
			int V = Integer.parseInt(tk.nextToken()); 
			edge[U].add(V); 
			edge[V].add(U); 
		} 
		 
		bfs(); 
		dijk(); 
		 
		System.out.println(dist[N]); 
 
	} 
	private static void bfs() { 
		boolean[] visit = new boolean[N+1]; 
		int[] len = new int[N+1]; 
		ArrayDeque<Integer> deq = new ArrayDeque<>(); 
		for(int i=1; i<N+1; i++) { 
			if(jombi[i]) { 
				deq.add(i); 
				visit[i] = true; 
			} 
		} 
		int cnt = 0; 
		while(!deq.isEmpty()) { 
			int size = deq.size(); 
			for(int i=0; i<size; i++) { 
				int cur = deq.poll(); 
				for(Integer next : edge[cur]) { 
					if(visit[next]) continue; 
					visit[next] = true; 
					len[next] = cnt+1; 
					deq.add(next); 
				} 
			} 
			cnt++;			 
		} 
		pay[1] = pay[N] = 0; 
		for(int i=2; i<N; i++) { 
			if(len[i] > 0 && len[i] <= S) { 
				pay[i] = Q;  
			}else if(len[i] > S) { 
				pay[i] = P; 
			}else { 
				pay[i] = -1; 
			} 
		} 
 
	} 
	 
	private static void dijk() { 
		Arrays.fill(dist, Long.MAX_VALUE); 
		boolean[] visit = new boolean[N+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		pq.add(new Node(1, 0)); 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Long.MAX_VALUE) break; 
			if(visit[cur.num]) continue; 
			visit[cur.num] = true; 
			for(Integer next : edge[cur.num]) { 
				if(!visit[next] && pay[next] >= 0 && dist[next] > cur.cost + pay[next]) { 
					dist[next] = cur.cost + pay[next]; 
					pq.add(new Node(next, dist[next])); 
				} 
			} 
		} 
				 
	} 
 
}