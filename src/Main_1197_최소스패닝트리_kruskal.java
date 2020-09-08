

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리_kruskal {
	static class Edge implements Comparable<Edge>{
		int g1,g2,l;
		
		public Edge(int g1, int g2, int l) {
			this.g1 = g1;
			this.g2 = g2;
			this.l = l;
		}

		@Override
		public int compareTo(Edge o) {
			return l-o.l;
		}
		
	}
	static int V, E;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(tk.nextToken());
		E = Integer.parseInt(tk.nextToken());
		
		parent = new int[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i=0; i<V+1; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<E; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(tk.nextToken());
			int e = Integer.parseInt(tk.nextToken());
			int l = Integer.parseInt(tk.nextToken());
			pq.offer(new Edge(s,e,l));
		}
		
		int idx = 0;
		int min = 0;
		while(idx<V-1) {
			Edge temp = pq.poll();
			if(find(temp.g1) != find(temp.g2)) {
				parent[find(temp.g1)] = find(temp.g2);
				idx++;
				min+=temp.l;
			}
		}
		
		System.out.println(min);
		

	}

	private static int find(int n) {
		if(n == parent[n]) {
			return n;
		}
		parent[n] = find(parent[n]);
		return parent[n];
	}
	
	

}
