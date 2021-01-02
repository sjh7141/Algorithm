package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4386_별자리만들기 {
	static class Edge implements Comparable<Edge>{
		double len;
		int n1, n2;
		public Edge(double len, int n1, int n2) {
			this.len = len;
			this.n1 = n1;
			this.n2 = n2;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.len, o.len);
		}
		
	}
	static double[][] stars;
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		stars = new double[n][2];
		parent = new int[n];
		for(int i=0; i<n; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			stars[i][0] = Double.parseDouble(tk.nextToken());
			stars[i][1] = Double.parseDouble(tk.nextToken());
			parent[i] = i;
		}
		
		double ans = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				double len = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
				pq.add(new Edge(len, i, j));
			}
		}
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(find(cur.n1) != find(cur.n2)) {
				ans += cur.len;
				parent[find(cur.n1)] = find(cur.n2);
			}
		}
		System.out.println(String.format("%.2f", ans));
	}
	public static int find(int n) {
		if(parent[n] == n) return n;
		parent[n] = find(parent[n]);
		return parent[n];
	}

}
