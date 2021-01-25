package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_1774_우주신과의교감다시 {
	static class Node implements Comparable<Node>{
		int n;
		double c;
		
		public Node(int n, double c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(c, o.c);
		}		
	}
	static int N,M;
	static int[][] pos;
	static boolean[] visit;
	static List<Node>[] lists;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		pos = new int[N][2];
		lists = new List[N];
		visit = new boolean[N];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(tk.nextToken());
			pos[i][1] = Integer.parseInt(tk.nextToken());
			lists[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				double l = Math.sqrt(Math.pow(pos[i][0]-pos[j][0], 2)+ Math.pow(pos[i][1]-pos[j][1], 2));
				lists[i].add(new Node(j,l));
				lists[j].add(new Node(i,l));
			}
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
			if(++cnt == N) break;
		}
		
		System.out.println(min);
		

	}

}
