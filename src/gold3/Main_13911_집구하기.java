package gold3;
 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 //다익스트라, 더미노드
public class Main_13911_집구하기 { 
	static class Node implements Comparable<Node>{ 
		int num, cost; 
		 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
		 
	} 
	static int V,E; 
	static boolean[] isMac, isStar; 
	static ArrayList<Node>[] map; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		V = Integer.parseInt(tk.nextToken()); 
		E = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[V+3]; 
		for(int i=0; i<V+3; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		 
		for(int i=0; i<E; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int v1 = Integer.parseInt(tk.nextToken()); 
			int v2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[v1].add(new Node(v2, l)); 
			map[v2].add(new Node(v1, l)); 
		} 
		 
		isMac = new boolean[V+3]; 
		isStar = new boolean[V+3]; 
		 
		tk = new StringTokenizer(br.readLine()); 
		int macNum = Integer.parseInt(tk.nextToken()); 
		int macLimit = Integer.parseInt(tk.nextToken()); 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=0; i<macNum; i++) { 
			isMac[Integer.parseInt(tk.nextToken())] = true; 
		} 
		isMac[V+1] = true; 
		 
		for(int i=1; i<V+1; i++) { 
			if(isMac[i]) map[V+1].add(new Node(i,0)); 
		} 
		 
		 
		tk = new StringTokenizer(br.readLine()); 
		int starNum = Integer.parseInt(tk.nextToken()); 
		int starLimit = Integer.parseInt(tk.nextToken()); 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=0; i<starNum; i++) { 
			isStar[Integer.parseInt(tk.nextToken())] = true; 
		} 
		isStar[V+2] = true; 
		 
		for(int i=1; i<V+1; i++) { 
			if(isStar[i]) map[V+2].add(new Node(i,0)); 
		} 
		 
		int[] macDist = dijk(V+1); 
		int[] starDist = dijk(V+2); 
		 
		int ans = Integer.MAX_VALUE; 
		for(int i=1; i<V+1; i++) { 
			if(macDist[i] <= macLimit && starDist[i] <= starLimit) { 
				ans = Math.min(ans, macDist[i] + starDist[i]); 
			} 
		} 
		 
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans); 
		 
 
	} 
	private static int[] dijk(int start) { 
		int[] dist = new int[V+3]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<V+3; i++) { 
			dist[i] = (i==start) ? 0 : Integer.MAX_VALUE; 
		} 
		pq.add(new Node(start,0)); 
		while(!pq.isEmpty()) { 
			int num = pq.peek().num; 
			int cost = pq.peek().cost; 
			pq.poll(); 
			if(cost > dist[num]) continue; 
			if(cost == Integer.MAX_VALUE) break; 
			for(Node temp : map[num]) { 
				int next = temp.num; 
				int weight = temp.cost; 
				if(dist[next] > cost + weight) { 
					dist[next] = cost + weight; 
					pq.add(new Node(next, dist[next])); 
				} 
			} 
			 
		} 
		return dist; 
	} 
 
}