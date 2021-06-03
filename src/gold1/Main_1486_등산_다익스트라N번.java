package gold1;

import java.io.BufferedReader;  
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays;  
import java.util.PriorityQueue;  
import java.util.StringTokenizer; 
// 452ms
// 문제 접근 할 때 처음 생각했던 방법 와샬과 다르게 시간초과는 넘기지 않을 것이라고 생각했다.
// 다익스트라를 N*M번(모든 정점에서) 돌려서 모든 최단거리를 구해둔 뒤 조건만족하는 값을 구한다.
public class Main_1486_등산_다익스트라N번 { 
	static class Node implements Comparable<Node>{ 
		int num,cost; 
		 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
	} 
	static int N,M,T,D;  
	static int[][] map;  
	static int[][] height;  
	static ArrayList<Node>[] list; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};  
	public static void main(String[] args) throws Exception {  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		StringTokenizer tk = new StringTokenizer(br.readLine());  
		N = Integer.parseInt(tk.nextToken());  
		M = Integer.parseInt(tk.nextToken());  
		T = Integer.parseInt(tk.nextToken());  
		D = Integer.parseInt(tk.nextToken());  
		map = new int[N*M][N*M];  
		height = new int[N][M]; 
		list = new ArrayList[N*M]; 
		for(int i=0; i<N; i++) {  
			String str = br.readLine();  
			for(int j=0; j<M; j++) {  
				char cur = str.charAt(j);  
				if(cur >= 'A' && cur <= 'Z') height[i][j] = (cur - 'A');  
				else height[i][j] = (cur - 'a') + 26;  
			}  
		}  
		  
		for(int i=0; i<N*M; i++) {  
			Arrays.fill(map[i], Integer.MAX_VALUE);  
			map[i][i] = 0; 
			list[i] = new ArrayList<>(); 
		}  
		 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++){ 
				for(int k=0; k<4; k++) {  
					int tr = i + dir[k][0];  
					int tc = j + dir[k][1];  
					if(tr<0 || tc<0 || tr>N-1 || tc>M-1 || Math.abs(height[i][j] - height[tr][tc]) > T) continue;  
					int from = i*M+j, to = tr*M+tc, gap = Math.abs(height[i][j] - height[tr][tc]); 
					if (height[i][j] >= height[tr][tc]) list[from].add(new Node(to,1)); 
                    else list[from].add(new Node(to, gap*gap)); 
				}  
			} 
		}		 
		
		for(int i=0; i<N*M; i++) { 
			dijk(i); 
		} 
		 
		int max = 0; 
		for(int i=0; i<N*M; i++) { 
			if(map[0][i] != Integer.MAX_VALUE && map[i][0] != Integer.MAX_VALUE && map[0][i] + map[i][0] <= D) { 
				max = Math.max(max, height[i/M][i%M]); 
			} 
		} 
		 
		System.out.println(max);  
		  
	} 
	private static void dijk(int num) { 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		pq.offer(new Node(num, 0)); 
		while(!pq.isEmpty()) { 
			int cur = pq.peek().num; 
			int cost = pq.peek().cost; 
			pq.poll(); 
			if(map[cur][num] < cost) continue; 
			for(Node temp : list[cur]) { 
				int next = temp.num; 
				int weight = temp.cost; 
				if(map[num][next] > cost + weight) { 
					map[num][next] = cost + weight; 
					pq.offer(new Node(next, map[num][next])); 
				} 
			} 
		} 
		 
	}  
}