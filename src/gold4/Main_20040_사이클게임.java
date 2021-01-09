package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20040_사이클게임 {
	static int n,m;
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tk.nextToken());
		m = Integer.parseInt(tk.nextToken());
		parent = new int[n];
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
		
		int ans = 0;
		for(int i=0; i<m; i++) {
			tk = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(tk.nextToken());
			int n2 = Integer.parseInt(tk.nextToken());
			if(find(n1) != find(n2)) {
				parent[find(n1)] = find(n2);
			}else {
				ans = i+1;
				break;
			}
		}
		
		System.out.println(ans);
		
		
	}
	private static int find(int v) {
		if(parent[v] == v){
			return v;
		}
		parent[v] = find(parent[v]);
		return parent[v];
	}
}
