package gold5;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
	static class Node implements Comparable<Node>{
		int n,c;
		
		public Node(int n, int c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return c-o.c;
		}
		
	}
	static List<Node>[] lists;
	static int V,E,K;
	static Node[] costs;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(tk.nextToken());
		E = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(br.readLine());
		
		lists = new List[V+1];
		for(int i=0; i<V+1; i++) {
			lists[i] = new ArrayList<>();
		}
		for(int i=0; i<E; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(tk.nextToken());
			int v = Integer.parseInt(tk.nextToken());
			int w = Integer.parseInt(tk.nextToken());
			lists[u].add(new Node(v, w));
		}
		costs = new Node[V+1];
		for(int i=0; i<V+1; i++) {
			costs[i] = new Node(i, Integer.MAX_VALUE);
		}
		costs[K] = new Node(K, 0);
		
		dijk();
		
		for(int i=1; i<V+1; i++) {
			int cur = costs[i].c;
			System.out.println(cur==Integer.MAX_VALUE?"INF":cur);
		}
	}
	private static void dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i=1; i<V+1; i++) {
			pq.offer(costs[i]);
		}
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(cur.c == Integer.MAX_VALUE) {
				break;
			}
			for(Node temp : lists[cur.n]) {
				if(pq.contains(costs[temp.n]) && cur.c + temp.c < costs[temp.n].c) {
					pq.remove(costs[temp.n]);
					costs[temp.n] = new Node(temp.n, cur.c+temp.c);
					pq.offer(costs[temp.n]);
				}
			}
		}
		
		
		
	}

}
