package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1167_트리의지름 {
	static class Edge{
		int n,c;

		public Edge(int n, int c) {
			this.n = n;
			this.c = c;
		}
		
	}
	static int N, maxNode, max;
	static boolean[] visit;
	static ArrayList<Edge>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		visit = new boolean[N+1];
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=1; i<N+1; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(tk.nextToken());
			while(true) {
				int to = Integer.parseInt(tk.nextToken());
				if(to == -1) break;
				int cost = Integer.parseInt(tk.nextToken());
				list[from].add(new Edge(to,cost));
			}
		}
		visit[1] = true;
		dfs(1,0);
		visit = new boolean[N+1]; visit[maxNode] = true; max = 0;
		dfs(maxNode, 0);
		System.out.println(max);

	}
	private static void dfs(int n, int c) {
		for(Edge e: list[n]) {
			if(visit[e.n]) continue;
			visit[e.n] = true;
			dfs(e.n, c+e.c);
			if(max < c+e.c) {
				max = c+e.c;
				maxNode = e.n;
			}
		}
		
	}

}
