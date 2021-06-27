package platinum5;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
//다익스트라
//인접행렬로 경로 구성했다가 시간초과가 난문제, 경로 추적후에 다시 다익스트라를 진행해야하므로 최적화가 빡빡했던 문제
public class Main_5719_거의최단경로 { 
	static class Node implements Comparable<Node> { 
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
	static int N,M,S,D; 
	static boolean[][] possible; 
	static int[] dist; 
	static ArrayList<Node>[] list,re_list; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		while(true) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			N = Integer.parseInt(tk.nextToken()); 
			M = Integer.parseInt(tk.nextToken()); 
			if(N == 0 && M == 0) break; 
			tk = new StringTokenizer(br.readLine()); 
			S = Integer.parseInt(tk.nextToken()); 
			D = Integer.parseInt(tk.nextToken()); 
			list = new ArrayList[N]; 
			re_list = new ArrayList[N]; 
			possible = new boolean[N][N]; 
			for(int i=0; i<N; i++) { 
				list[i] = new ArrayList<>(); 
				re_list[i] = new ArrayList<>(); 
			} 
			for(int i=0; i<M; i++) { 
				tk = new StringTokenizer(br.readLine()); 
				int U = Integer.parseInt(tk.nextToken()); 
				int V = Integer.parseInt(tk.nextToken()); 
				int P = Integer.parseInt(tk.nextToken()); 
				list[U].add(new Node(V, P)); 
				re_list[V].add(new Node(U, P)); 
				possible[U][V] = true; 
			} 
			 
			 
			dijk(true); 
			dijk(false); 
			 
			System.out.println((dist[D] == Integer.MAX_VALUE) ? -1 : dist[D]); 
			 
		} 
	} 
	private static void dijk(boolean flag) { 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		dist = new int[N]; 
		Arrays.fill(dist, Integer.MAX_VALUE); 
		dist[S] = 0; 
		boolean[] visit = new boolean[N]; 
		 
		pq.add(new Node(S, dist[S])); 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(visit[cur.num]) continue; 
			visit[cur.num] = true; 
			for(Node temp : list[cur.num]) { 
				int next = temp.num; 
				int weight = temp.cost; 
				if(possible[cur.num][next] && !visit[next] && dist[next] > cur.cost + weight) { 
					dist[next] = cur.cost + weight; 
					pq.add(new Node(next,dist[next])); 
				} 
			} 
		} 
		if(flag) { 
			backTrace(D); 
		} 
	} 
	private static void backTrace(int num) { 
		if(num == S) { 
			return; 
		} 
		for(Node temp : re_list[num]) { 
			int pre = temp.num; 
			if(possible[pre][num] && dist[num] == dist[pre] + temp.cost) { 
				possible[pre][num] = false; 
				backTrace(pre); 
			} 
		} 
		 
	} 
 
}