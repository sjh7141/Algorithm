package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13424_비밀모임 {
	static class Node implements Comparable<Node>{
		int num, cost;
		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}

		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
	}
	static int T,N,M,K;
	static int[][] map;
	static ArrayList<Integer> friends;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tk.nextToken());
			M = Integer.parseInt(tk.nextToken());
			map = new int[N+1][N+1];
			friends = new ArrayList<>();
			for(int i=0; i<N+1; i++) {
				Arrays.fill(map[i], Integer.MAX_VALUE);
				map[i][i] = 0;
			}
			for(int i=0; i<M; i++) {
				tk = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(tk.nextToken());
				int n2 = Integer.parseInt(tk.nextToken());
				int l = Integer.parseInt(tk.nextToken());
				map[n1][n2] = l;
				map[n2][n1] = l;
			}

			K = Integer.parseInt(br.readLine());
			tk = new StringTokenizer(br.readLine());
			for(int i=0; i<K; i++) {
				friends.add(Integer.parseInt(tk.nextToken()));
			}
			
			floyd();
			int sum = Integer.MAX_VALUE;
			int ans = 0;
			for(int i=1; i<N+1; i++) {
				int temp = 0;
				for(int num : friends) {
					temp += map[num][i];
				}
				if(sum > temp) {
					ans = i;
					sum = temp;
				}
			}
			System.out.println(ans);
		}
	}
	private static void floyd() {
		for(int k=1; k<N+1; k++) {
			for(int i=1; i<N+1; i++) {
				if(k==i) continue;
				for(int j=1; j<N+1; j++) {
					if(k==j || i==j) continue;
					if(map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE && map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
	}

}
