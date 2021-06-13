package gold1;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 // 다익스트라, 2차원
public class Main_16118_달빛여우 { 
	static class Node implements Comparable<Node>{ 
		int num, type; 
		double cost; 
		//type : 1 - 여우 
		//		 2 - 늑대 2배속 
		//       3 - 늑대 1/2배속 
 
		@Override 
		public int compareTo(Node o) { 
			return Double.compare(cost, o.cost); 
		}  
		 
		public Node(int num, double cost, int type) { 
			this.num = num; 
			this.cost = cost; 
			this.type = type; 
		} 
		 
	} 
	static int N,M,K; 
	static ArrayList<Node>[] map;  
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(tk.nextToken()); 
			int b = Integer.parseInt(tk.nextToken()); 
			double d = Double.parseDouble(tk.nextToken()); 
			map[a].add(new Node(b, d, 0)); 
			map[b].add(new Node(a, d, 0)); 
		} 
		 
		System.out.println(dijk()); 		 
 
	} 
	private static int dijk() { 
		double[][] dist = new double[N+1][4];  
		boolean[][] visit = new boolean[N+1][4]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<N+1; i++) { 
			Arrays.fill(dist[i], Integer.MAX_VALUE); 
		} 
		dist[1][1] = 0; 
		dist[1][2] = 0; 
		pq.add(new Node(1, 0, 1)); 
		pq.add(new Node(1, 0, 2)); 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Integer.MAX_VALUE) break; 
			if(visit[cur.num][cur.type]) continue; 
			visit[cur.num][cur.type] = true; 
			for(Node temp : map[cur.num]) { 
				int next = temp.num; 
		double time = temp.cost; 
				int type = cur.type; 
				if(type == 2) { 
					type = 3; 
					time /= 2; 
				} 
				else if(type == 3) { 
					type = 2; 
					time *= 2; 
				} 
				if(!visit[temp.num][type] && dist[temp.num][type] > cur.cost + time) { 
					dist[temp.num][type] = cur.cost + time; 
					pq.add(new Node(next, dist[temp.num][type], type)); 
				} 
			} 
		} 
		 
		int ans = 0; 
		for(int i=1; i<N+1; i++) { 
			if(dist[i][1] < dist[i][2] && dist[i][1] < dist[i][3]) { 
				ans++; 
			} 
		} 
		return ans; 
	} 
 
}