package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5974_택배배송 {
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
	static int N,M;
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
			int n1 = Integer.parseInt(tk.nextToken());
			int n2 = Integer.parseInt(tk.nextToken());
			int l = Integer.parseInt(tk.nextToken());
			map[n1].add(new Node(n2, l));
			map[n2].add(new Node(n1, l));
		}
		int ans = dijk();
		System.out.println(ans);
	}
	private static int dijk() {
		int[] dist = new int[N+1];
		boolean[] visit = new boolean[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, dist[1]));
		
		while(!pq.isEmpty()) {
			int cur = pq.peek().num;
			int cost = pq.peek().cost;
			pq.poll();
			if(visit[cur]) continue;
			visit[cur] = true;
			for(Node temp : map[cur]) {
				int next = temp.num;
				int weight = temp.cost;
				if(!visit[next] && dist[next] > cost + weight) {
					dist[next] = cost + weight;
					pq.add(new Node(next, dist[next]));
				}
			}
		}
		return dist[N];
	}

}
