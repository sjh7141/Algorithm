package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2887_행성터널 {
	static class Edge implements Comparable<Edge> {
		int n,c;

		public Edge(int n, int c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return c - o.c;
		}
		
		
	}
	static int N, ans;
	static int[][] planet;
	static boolean[] visit;
	static ArrayList<Edge>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		planet = new int[N][4];
		visit = new boolean[N];
		graph = new ArrayList[N];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			planet[i][0] = Integer.parseInt(tk.nextToken());
			planet[i][1] = Integer.parseInt(tk.nextToken());
			planet[i][2] = Integer.parseInt(tk.nextToken());
			planet[i][3] = i;
			graph[i] = new ArrayList<>();
		}
		
		Arrays.sort(planet, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
			
		});
		for(int i=1; i<N; i++) {
			int len = Math.min(Math.abs(planet[i][0] - planet[i-1][0]), Math.min(Math.abs(planet[i][1] - planet[i-1][1]), Math.abs(planet[i][2] - planet[i-1][2])));
			graph[planet[i][3]].add(new Edge(planet[i-1][3],len));
		}
		
		Arrays.sort(planet, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
		});
		for(int i=1; i<N; i++) {
			int len = Math.min(Math.abs(planet[i][0] - planet[i-1][0]), Math.min(Math.abs(planet[i][1] - planet[i-1][1]), Math.abs(planet[i][2] - planet[i-1][2])));
			graph[planet[i][3]].add(new Edge(planet[i-1][3],len));
		}
		
		Arrays.sort(planet, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
			
		});
		for(int i=1; i<N; i++) {
			int len = Math.min(Math.abs(planet[i][0] - planet[i-1][0]), Math.min(Math.abs(planet[i][1] - planet[i-1][1]), Math.abs(planet[i][2] - planet[i-1][2])));
			graph[planet[i][3]].add(new Edge(planet[i-1][3],len));
		}		
		
		prim();	
		System.out.println(ans);
		
		
		
		

	}
	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(0);
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			visit[cur] = true;
			
			for(Edge e : graph[cur]) {
				if(visit[e.n]) continue;
				pq.add(e);
			}
			
			while(!pq.isEmpty()) {
				Edge e = pq.poll();
				if(visit[e.n]) continue;
				System.out.println(cur + " " + e.n + " " + e.c);
				que.add(e.n);
				visit[e.n] = true;
				ans += e.c;
				break;
			}
		}
	}

}
