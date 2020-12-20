package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1944_복제로봇 {
	static int N,M;
	static char[][] map;
	static int[][] key_idx;
	static int[] parent;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static PriorityQueue<Edge> Edges;
	static class Edge implements Comparable<Edge>{
		int from,to,cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		map = new char[N][N];
		key_idx = new int[N][N];
		ArrayList<int[]> keys = new ArrayList<int[]>();
		Edges = new PriorityQueue<Edge>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S' || map[i][j] == 'K') {
					key_idx[i][j] = keys.size();
					keys.add(new int[] {i,j}); 
				}
			}
		}
		
		for(int i=0; i<keys.size(); i++) {
			int[] key = keys.get(i);
			bfs(key[0], key[1], i);
		}
		
		parent = new int[M+1];
		for(int i=0; i<M+1; i++) {
			parent[i] = i;
		}
		
		System.out.println(kruskal());
		
		

	}
	private static int kruskal() {
		int min = 0;
		int idx = 0;
		while(!Edges.isEmpty()) {
			Edge temp = Edges.poll();
			int from = temp.from;
			int to = temp.to;
			if(find(from) != find(to)) {
				parent[find(from)] = find(to);
				min+=temp.cost;
				idx++;
			}
		}
		
		return idx==M ? min : -1;
	}
	
	private static int find(int idx) {
		if(parent[idx] == idx) {
			return idx;
		}
		parent[idx] = find(parent[idx]);
		return parent[idx];
	}
	
	private static void bfs(int sr, int sc, int idx) {
		boolean[][] visit = new boolean[N][N];
		ArrayDeque<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {sr,sc});
		visit[sr][sc] = true;
		
		int cnt = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i=0; i<size; i++) {
				int[] cur = que.poll();
				for(int k=0; k<4; k++) {
					int tr = cur[0] + dir[k][0];
					int tc = cur[1] + dir[k][1];
					if(tr<0 || tr>N-1 || tc<0 || tc>N-1 || visit[tr][tc] || map[tr][tc] == '1') continue;
					
					visit[tr][tc] = true;
					que.offer(new int[] {tr,tc});
					if(map[tr][tc] == 'S' || map[tr][tc] == 'K') {
						Edges.offer(new Edge(idx, key_idx[tr][tc], cnt + 1));
					}
				}
			}
			cnt++;
		}
		
	}

}
