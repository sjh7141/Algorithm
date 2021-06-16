package gold4;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
//최소신장트리
public class Main_6497_전력난 { 
	static class Edge implements Comparable<Edge>{ 
		int n1,n2,l; 
		 
		public Edge(int n1, int n2, int l) { 
			this.n1 = n1; 
			this.n2 = n2; 
			this.l = l; 
		} 
 
		@Override 
		public int compareTo(Edge o) { 
			return l - o.l; 
		} 
		 
	} 
	static int N,M,total; 
	static ArrayList<int[]>[] map; 
	static int[] parent; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		while(true) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			N = Integer.parseInt(tk.nextToken()); 
			M = Integer.parseInt(tk.nextToken()); 
			if(N == 0 && M == 0) break; 
			map = new ArrayList[N]; 
			total = 0; 
			for(int i=0; i<N; i++) { 
				map[i] = new ArrayList<>(); 
			} 
			for(int i=0; i<M; i++) { 
				tk = new StringTokenizer(br.readLine()); 
				int n1 = Integer.parseInt(tk.nextToken()); 
				int n2 = Integer.parseInt(tk.nextToken()); 
				int l = Integer.parseInt(tk.nextToken()); 
				total += l; 
				map[n1].add(new int[] {n2,l}); 
				map[n2].add(new int[] {n1,l}); 
			} 
			int min = kruskal(); 
			System.out.println(total - min); 
		} 
 
	} 
	private static int kruskal() { 
		parent = new int[N]; 
		for(int i=0; i<N; i++) { 
			parent[i] = i; 
		} 
		int min = 0; 
		int idx = 0; 
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		for(int i=0; i<N; i++) { 
			for(int[] temp : map[i]) { 
				pq.add(new Edge(i, temp[0], temp[1])); 
			} 
		} 
		 
		while(idx < N-1) { 
			Edge cur = pq.poll(); 
			if(find(cur.n1) != find(cur.n2)) { 
				parent[find(cur.n1)] = find(cur.n2); 
				min+=cur.l; 
				idx++; 
			} 
		} 
		return min; 
	} 
	private static int find(int n) { 
		if(parent[n] == n) return n; 
		return parent[n] = find(parent[n]); 
	} 
 
}