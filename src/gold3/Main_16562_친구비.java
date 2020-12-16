package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16562_친구비 {
	static int N, M, K, ans;
	static int[] parent, A;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		A = new int[N+1];
		parent = new int[N+1];
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<N+1; i++) {
			A[i] = Integer.parseInt(tk.nextToken());
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			if(find(a) != find(b)) {
				int pa = find(a);
				int pb = find(b);
				if(A[pa] > A[pb]) {
					parent[pa] = pb;
				}else {
					parent[pb] = pa;
				}
			}
		}
		for(int i=1; i<N+1; i++) {
			if(parent[i] == i) ans+=A[i];
		}
		if(ans > K)	System.out.println("Oh no");
		else System.out.println(ans);
				
	}
	
	private static int find(int p) {
		if(parent[p] == p)
			return p;
		parent[p] = find(parent[p]);
		return parent[p];
	}

}
