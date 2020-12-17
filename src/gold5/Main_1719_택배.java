package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1719_택배 {
	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
	}
	static int n,m;
	static int[][] map, ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(tk.nextToken());
		m = Integer.parseInt(tk.nextToken());
		map = new int[n+1][n+1];
		ans = new int[n+1][n+1];
		for(int i=0; i<n+1; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		
		for(int i=0; i<m; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(tk.nextToken());
			int n2 = Integer.parseInt(tk.nextToken());
			int cost = Integer.parseInt(tk.nextToken());
			map[n1][n2] = cost;
			map[n2][n1] = cost;
			ans[n1][n2] = n2;
			ans[n2][n1] = n1;
		}
		
		
		for(int k=1; k<n+1; k++) {
			for(int i=1; i<n+1; i++) {
				for(int j=1; j<n+1; j++) {
					if(map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE) {
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
							ans[i][j] = (map[i][j]==Integer.MAX_VALUE) ? k : ans[i][k];
						}
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i != j) sb.append(ans[i][j]).append(" ");
				else sb.append("- ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		
	}

}
