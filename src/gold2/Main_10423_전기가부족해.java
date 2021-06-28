package gold2;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 // 최소신장트리
public class Main_10423_전기가부족해 { 
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
	static int N,M,K; 
	static ArrayList<Integer> elec; 
	static ArrayList<int[]>[] cables; 
	static int[] parents; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
		tk = new StringTokenizer(br.readLine()); 
		elec = new ArrayList<>(); 
		for(int i=0; i<K; i++) { 
			elec.add(Integer.parseInt(tk.nextToken())); 
		} 
		 
		cables = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			cables[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int cost = Integer.parseInt(tk.nextToken()); 
			cables[n1].add(new int[] {n2,cost}); 
			cables[n2].add(new int[] {n1,cost}); 
		} 
		 
		for(Integer next : elec) { 
			cables[0].add(new int[] {next,0}); 
			cables[next].add(new int[] {0,0}); 
		} 
		 
		int ans = kruskal(); 
		System.out.println(ans); 
	} 
	private static int kruskal() { 
		parents = new int[N+1]; 
		for(int i=0; i<N+1; i++) { 
			parents[i] = i; 
		} 
		int min = 0; 
		int idx = 0; 
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		for(int i=0; i<N+1; i++) { 
			for(int[] temp : cables[i]) { 
				pq.add(new Edge(i, temp[0], temp[1])); 
			} 
		} 
		while(idx<N){ 
			Edge cur = pq.poll(); 
			if(find(cur.n1) != find(cur.n2)) { 
				parents[find(cur.n1)] = find(cur.n2); 
				min += cur.l; 
				idx++; 
			} 
		} 
		return min; 
	} 
	private static int find(int n) { 
		if(parents[n] == n) { 
			return n; 
		} 
		return parents[n] = find(parents[n]); 
	} 
 
}