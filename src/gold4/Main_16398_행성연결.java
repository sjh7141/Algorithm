package gold4;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main_16398_행성연결 { 
	static class Edge implements Comparable<Edge>{ 
		int g1,g2;
        long l; 
		 
		public Edge(int g1, int g2, long l) { 
			this.g1 = g1; 
			this.g2 = g2; 
			this.l = l; 
		} 
 
		@Override 
		public int compareTo(Edge o) { 
			return Long.compare(l,o.l); 
		} 
		 
	} 
	static int N; 
	static int[][] mat; 
	static int[] parent; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		mat = new int[N][N]; 
		for(int i=0; i<N; i++) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			for(int j=0; j<N; j++) { 
				mat[i][j] = Integer.parseInt(tk.nextToken()); 
			} 
		} 
        long ans = kruskal(); 
		System.out.println(ans); 
	} 
	private static long kruskal() { 
        long min = 0; 
		parent = new int[N]; 
		for(int i=0; i<N; i++) { 
			parent[i] = i; 
		} 
		 
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<N; j++) { 
				pq.add(new Edge(i, j, mat[i][j])); 
			} 
		} 
		 
		int idx = 0; 
		while(idx < N-1) { 
			Edge cur = pq.poll(); 
			if(find(cur.g1) != find(cur.g2)) { 
				parent[find(cur.g1)] = find(cur.g2); 
				min += cur.l; 
				idx++; 
			} 
		} 
		return min; 
	} 
	private static int find(int g) { 
		if(parent[g] == g) { 
			return g; 
		} 
		parent[g] = find(parent[g]); 
		return parent[g]; 
	} 
 
}