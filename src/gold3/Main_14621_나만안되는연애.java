package gold3;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 //최소신장트리
public class Main_14621_나만안되는연애 { 
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
	static int N,M; 
	static ArrayList<int[]>[] map; 
	//0:남자 1:여자 
	static int[] gender, parent; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		gender = new int[N+1]; 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=1; i<N+1; i++) { 
			if(tk.nextToken().equals("W")) { 
				gender[i] = 1; 
			} 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			if(gender[n1] == gender[n2]) continue; 
			map[n1].add(new int[] {n2,l}); 
			map[n2].add(new int[] {n1,l}); 
		} 
		 
		int min = kruskal(); 
		System.out.println(min); 
 
	} 
	private static int kruskal() { 
		parent = new int[N+1]; 
		for(int i=1; i<N+1; i++) { 
			parent[i] = i; 
		} 
		 
		int min = 0; 
		int idx = 0; 
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		for(int i=1; i<N+1; i++) { 
			for(int[] temp : map[i]) { 
				pq.add(new Edge(i, temp[0], temp[1])); 
			} 
		} 
		 
		while(idx < N-1 && !pq.isEmpty()) { 
			Edge cur = pq.poll(); 
			if(find(cur.n1) != find(cur.n2)) { 
				parent[find(cur.n1)] = find(cur.n2); 
				min+=cur.l; 
				idx++; 
			} 
		} 
		return (idx == N-1) ? min : -1; 
	} 
	private static int find(int n) { 
		if(parent[n] == n) return n; 
		return parent[n] = find(parent[n]); 
	} 
 
}