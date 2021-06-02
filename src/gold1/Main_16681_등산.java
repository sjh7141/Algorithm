package gold1;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
// 일반적인 다익스트라로 접근했을 때 도저히 답이 안나왔기에 아이디어를 참고한 문제
//다익스트라, 시작점 분할 
public class Main_16681_등산 { 
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
	static int N,M,D,E; 
	static ArrayList<Node>[] map; 
	static int[] height; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		D = Integer.parseInt(tk.nextToken()); 
		E = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		height = new int[N+1]; 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=1; i<N+1; i++) { 
			height[i] = Integer.parseInt(tk.nextToken()); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int h = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2, h)); 
			map[n2].add(new Node(n1, h)); 
		} 
		long[] part1 = dijk(1); 
		long[] part2 = dijk(N); 
		long ans = Long.MIN_VALUE; 
		for(int i=2; i<N; i++) { 
			if(part1[i] == Long.MAX_VALUE || part2[i] == Long.MAX_VALUE) continue; 
			ans = Math.max(ans, height[i] * E - (part1[i] + part2[i]) * D); 
		} 
		System.out.println((ans == Long.MIN_VALUE) ? "Impossible" : ans); 
	}
	private static long[] dijk(int start) { 
		long[] dist = new long[N+1]; 
		Arrays.fill(dist, Long.MAX_VALUE); 
		dist[start] = 0; 
		boolean[] visit = new boolean[N+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		 
		pq.add(new Node(start, dist[start])); 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Long.MAX_VALUE) break; 
			if(visit[cur.num]) continue; 
			visit[cur.num] = true; 
			for(Node temp : map[cur.num]) { 
				int next = temp.num; 
				if(!visit[next] && height[cur.num] < height[next] && cur.cost + temp.cost < dist[next]) { 
					dist[next] = cur.cost + temp.cost; 
					pq.add(new Node(next, dist[next])); 
				} 
			} 
		} 
		 
		return dist; 
	} 
 
}