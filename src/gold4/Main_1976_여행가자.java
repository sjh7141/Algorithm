package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1976_여행가자 {
	static int N,M;
	static int[][] cities;
	static int[] parent, rank;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N];
		rank = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				int isConnect = Integer.parseInt(tk.nextToken());
				int p1 = find(i);
				int p2 = find(j);
				if(isConnect == 1 && p1 != p2) {
					if(rank[p1] > rank[p2]) {
						parent[p2] = p1;
					}else {
						parent[p1] = p2;
					}
					if(rank[p1] == rank[p2]) rank[p1]++;
				}
			}	
		}

		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		boolean flag = true;
		int temp = 0;
		for(int i=0; i<M; i++) {
			int plan = Integer.parseInt(tk.nextToken()) - 1; 
			if(i!=0 && temp != find(plan)) {
				flag = false;
				break;
			}
			temp = find(plan);
			
		}
		
		System.out.println(flag ? "YES" : "NO");
		

	}
	private static int find(int g) {
		if(g == parent[g]) {
			return g;
		}
		parent[g] = find(parent[g]);
		return parent[g];
	}

}
