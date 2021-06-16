package gold4;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
//최소신장트리
public class Main_1647_도시분할계획 { 
	static class Edge implements Comparable<Edge>{ 
		int g1,g2,l; 
 
		public Edge(int g1, int g2, int l) { 
			this.g1 = g1; 
			this.g2 = g2; 
			this.l = l; 
		} 
 
		@Override 
		public int compareTo(Edge e) { 
			return l - e.l; 
		} 
		 
	} 
	static int N,M; 
	static int[] parent; 
	static ArrayList<int[]>[] list;  
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		parent = new int[N+1]; 
		list = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			list[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int g1 = Integer.parseInt(tk.nextToken()); 
			int g2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			list[g1].add(new int[] {g2,l}); 
			list[g2].add(new int[] {g1,l}); 
		} 
		 
		int ans = kruskal(); 
		System.out.println(ans); 
	} 
	private static int kruskal() { 
        parent = new int[N+1]; 
		for(int i=1; i<N+1; i++) { 
			parent[i] = i; 
		} 
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		int min = 0; 
		for(int i=1; i<N+1; i++) { 
			for(int[] temp : list[i]) { 
				pq.add(new Edge(i,temp[0],temp[1])); 
			} 
			 
		} 
		int idx = 0; 
		while(idx<N-2) { 
			Edge temp = pq.poll(); 
			int from = temp.g1; 
			int to = temp.g2; 
			if(find(from) != find(to)) { 
				parent[find(from)] = find(to); 
				idx++; 
				min+=temp.l; 
			} 
		} 
		 
		return min; 
		 
	} 
	private static int find(int g) {  
		if(parent[g] == g) 
			return g; 
		parent[g] = find(parent[g]); 
		return parent[g]; 
	} 
}