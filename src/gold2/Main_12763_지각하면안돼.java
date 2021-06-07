package gold2;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main_12763_지각하면안돼 { 
	static class Node implements Comparable<Node>{ 
		int num, cost, time; 
				 
		public Node(int num, int cost, int time) { 
			this.num = num; 
			this.cost = cost; 
			this.time = time; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return o.cost - cost; 
		} 
		 
	}
	static int N,T,M,L; 
	static ArrayList<Node>[] map;
	//다익스트라, 2차원
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		T = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		L = Integer.parseInt(br.readLine()); 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<L; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int time = Integer.parseInt(tk.nextToken()); 
			int cost = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2, cost, time)); 
			map[n2].add(new Node(n1, cost, time)); 
		} 
		 
		int ans = dijk(); 
		System.out.println(ans); 
	} 
	private static int dijk() {
		//이문제도 마찬가지로 Node 객체 자체를 prioriy queue에 집어넣고 contains 와 remove를 사용하는 식으로
		// 구현하면 시간초과가 난다. 아무래도 visit을 사용한 구현을 기본으로 해야겠다.
		int[][] dist = new int[N+1][T+1]; 
		boolean[][] visit = new boolean[N+1][T+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>();  
		for(int i=1; i<N+1; i++) { 
			for(int j=0; j<T+1; j++) { 
				dist[i][j] = (i==1 && j==T) ? M : -1; 
			} 
		} 
		pq.add(new Node(1,dist[1][T],T)); 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			int num = cur.num; 
			int cost = cur.cost; 
			int time = cur.time; 
			if(cost == -1) break; 
			if(visit[num][time]) continue; 
			visit[num][time] = true; 
			for(Node temp : map[num]) { 
				int next = temp.num; 
				int weight = temp.cost; 
				int taxiTime = temp.time; 
				if(time - taxiTime >= 0 && cost - weight >= 0 && !visit[next][time-taxiTime] && dist[next][time-taxiTime] < cost - weight) { 
					dist[next][time-taxiTime] = cost - weight; 
					pq.add(new Node(next, dist[next][time-taxiTime], time-taxiTime)); 
				} 
			} 
		} 
		int max = -1; 
		for(int i=0; i<T+1; i++) { 
			max = Math.max(max, dist[N][i]); 
		} 
		return (max==-1) ? max : M - max; 
	} 
 
}