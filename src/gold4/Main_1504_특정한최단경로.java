package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1504_특정한최단경로 {
	static int N, E, v1, v2;
	static int[][] cost;
	static class Node implements Comparable<Node>{
		int n, c;

		public Node(int n, int c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return c - o.c;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tk.nextToken());
		E = Integer.parseInt(tk.nextToken());
		cost = new int[N+1][N+1];
		for(int i=0; i<E; i++) {
			tk = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(tk.nextToken());
			int n2 = Integer.parseInt(tk.nextToken());
			int c = Integer.parseInt(tk.nextToken());
			cost[n1][n2] = c;
			cost[n2][n1] = c;
		}
		tk = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(tk.nextToken());
		v2 = Integer.parseInt(tk.nextToken());		
		
		int a1 = dijk(1,v1);
		int a2 = dijk(v1,v2);
		int a3 = dijk(v2,N);
		
		if(a1 == -1 || a2 == -1 || a3 == -1 ) {
			System.out.println(-1);
			return;
		}
		
		int b = dijk(1,v2) + dijk(v2,v1) + dijk(v1,N);
		System.out.println(Math.min(a1+a2+a3, b));
		
				
	}
	private static int dijk(int s, int e) {
		boolean[] visit = new boolean[N+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(s,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visit[cur.n]) continue;
			visit[cur.n] = true;
			if(cur.n == e) return cur.c;

			for(int i=1; i<N+1; i++) {
				if(cost[cur.n][i] == 0) continue;
				if(!visit[i]) {
					pq.add(new Node(i, cur.c + cost[cur.n][i]));
				}
			}
		}

		return -1;
	}

}
