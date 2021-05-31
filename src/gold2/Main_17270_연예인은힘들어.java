package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17270_연예인은힘들어 {
	static int V,M,J,S;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		map = new int[V+1][V+1];
		for(int i=0; i<V+1; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
			map[i][i] = 0;
		}
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(tk.nextToken());
			int n2 = Integer.parseInt(tk.nextToken());
			int l = Integer.parseInt(tk.nextToken());
			if(l < map[n1][n2]) map[n1][n2] = map[n2][n1] = l;
		}
		tk = new StringTokenizer(br.readLine());
		J = Integer.parseInt(tk.nextToken());
		S = Integer.parseInt(tk.nextToken());
		
		floyd();
		
		int sum = Integer.MAX_VALUE;
		ArrayList<Integer> candi = new ArrayList<>(); 
		for(int i=1; i<V+1; i++) {
			if(i==J || i==S) continue;
			if(sum > map[J][i] + map[S][i]) {
				candi.clear();
				sum = map[J][i] + map[S][i];
				candi.add(i);
			}else if(sum == map[J][i] + map[S][i]) {
				candi.add(i);
			}
		}
		int ans = -1;
		int len = Integer.MAX_VALUE;
		for(int temp : candi) {
			if(map[J][temp] > map[S][temp]) continue;
			if(len > map[J][temp]) {
				ans = temp;
				len = map[J][temp];
			}
		}
		
		System.out.println(ans);
	}
	private static void floyd() {
		for(int k=1; k<V+1; k++) {
			for(int i=1; i<V+1; i++) {
				if(k==i) continue;
				for(int j=1; j<V+1; j++) {
					if(j==i || j==k) continue;
					if(map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE && map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
	}

}