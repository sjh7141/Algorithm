package gold5;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 //다익스트라
public class Main_17396_백도어 { 
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
	static int N,M; 
	static ArrayList<Node>[] map; 
	static boolean[] see; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		see = new boolean[N]; 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=0; i<N; i++) { 
			if(Integer.parseInt(tk.nextToken()) == 1) see[i] = true; 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			if(n1 != N-1 && see[n1] || n2 != N-1 && see[n2]) continue; 
			map[n1].add(new Node(n2, l)); 
			map[n2].add(new Node(n1, l)); 
		} 
		 
		long ans = dijk(); 
		System.out.println((ans == Long.MAX_VALUE) ? -1 : ans); 
 
	} 
	private static long dijk() { 
		long[] dist = new long[N]; 
		boolean[] visit = new boolean[N]; 
		Arrays.fill(dist, Long.MAX_VALUE); 
		dist[0] = 0; 
		// 접근은 맞으나 pq에 Node 배열 넣어서 contains로 확인하는 작업이 시간초과 걸리는 케이스가 있는듯 하다 
//		Node[] dist = new Node[N]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
//		for(int i=0; i<N; i++) {	 
//			dist[i] = new Node(i, (i==0) ? 0 : Long.MAX_VALUE); 
//			pq.add(dist[i]); 
//		} 
		pq.add(new Node(0, dist[0])); 
		while(!pq.isEmpty()) { 
			int cur = pq.peek().num; 
		long cost = pq.peek().cost; 
			pq.poll(); 
			if(visit[cur]) continue; 
			visit[cur] = true; 
			if(cost == Long.MAX_VALUE) break; 
			for(Node temp : map[cur]) { 
				int next = temp.num; 
				long weight = temp.cost; 
				if(!visit[next] && dist[next] > cost + weight){ 
					dist[next] = cost + weight; 
					pq.add(new Node(next, dist[next])); 
				} 
			} 
		} 
		return dist[N-1]; 
	} 
 
}