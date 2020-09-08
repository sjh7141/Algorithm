

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리_prim {
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
	static int V, E;
	static boolean[] visit;
	static List<Node>[] lists;
	static int[] cost;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(tk.nextToken());
		E = Integer.parseInt(tk.nextToken());
		visit = new boolean[V];
		lists = new List[V];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i=0; i<V; i++) {
			lists[i] = new ArrayList<>();
		}

		
		for(int i=0; i<E; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(tk.nextToken())-1;
			int e = Integer.parseInt(tk.nextToken())-1;
			int l = Integer.parseInt(tk.nextToken());
			lists[s].add(new Node(e,l));
			lists[e].add(new Node(s,l));
		}
		
		int min = 0;
		int cnt = 0;
		pq.offer(new Node(0,0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visit[cur.n]) continue;
			visit[cur.n] = true;
			min += cur.c;
			for(Node n : lists[cur.n]) {
				if(!visit[n.n]) {
					pq.add(n);
				}
			}
			if(++cnt == V) break;
		}
		
		System.out.println(min);
		

	}


	
	

}
