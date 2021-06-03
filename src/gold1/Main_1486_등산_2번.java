package gold1;

import java.io.BufferedReader;  
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays;  
import java.util.PriorityQueue;  
import java.util.StringTokenizer;
// 140ms
// 문제풀이 구상당시 바로 떠올리긴 했지만 덕분에 틀렸습니다의 구렁텅이로 빠지게 된 아이디어
// 16681 등산 문제와 같이 다익스트라 2번으로 해결 할 수 있을 것이라고 생각하여 아이디어를 구상하였지만
// 조건에 따른 간선 만들기와 멘붕에 따른 각종 조건문 실수로 가장 나중에 완성하게 된 코드
// 정방향 역방향에 각각 조건별로 다른 list를 세팅해두고 다익스트라 2번이면 해결. 2번밖에 안되기에 속도가 가장 빠르다.
public class Main_1486_등산_2번 {  
	static class Node implements Comparable<Node>{  
		int num, cost;  
		  
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
	static int[][] height; 
	static ArrayList<Node>[] list, revList;  
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};  
	public static void main(String[] args) throws Exception {  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		StringTokenizer tk = new StringTokenizer(br.readLine());  
		N = Integer.parseInt(tk.nextToken());  
		M = Integer.parseInt(tk.nextToken());  
		T = Integer.parseInt(tk.nextToken());  
		D = Integer.parseInt(tk.nextToken());  
		height = new int[N][M];  
		for(int i=0; i<N; i++) {  
			String str = br.readLine();  
			for(int j=0; j<M; j++) {  
				char cur = str.charAt(j);  
				if(cur >= 'A' && cur <= 'Z') height[i][j] = (cur - 'A');  
				else height[i][j] = (cur - 'a') + 26;  
			}  
		}  
		  
		list = new ArrayList[N*M]; 
		revList = new ArrayList[N*M]; 
		 
		for(int i=0; i<N*M; i++) { 
			list[i] = new ArrayList<>(); 
			revList[i] = new ArrayList<>(); 
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
					 
					if(height[i][j] > height[tr][tc]) revList[from].add(new Node(to,gap*gap)); 
					else revList[from].add(new Node(to,1)); 
				}  
			} 
		}		 
		 
		int max = height[0][0];  
  
		int[] a = dijk(list);  
		int[] b = dijk(revList);  
		  
		for(int i=0; i<N*M; i++) { 	  
			if(a[i] != Integer.MAX_VALUE && b[i] != Integer.MAX_VALUE && a[i] + b[i] <= D) {  
				max = Math.max(max, height[i/M][i%M]);  
			}  
		}  
		System.out.println(max);  
		  
	}  
	private static int[] dijk(ArrayList<Node>[] curlist) {  
		int[] dist = new int[N*M];  
		Arrays.fill(dist, Integer.MAX_VALUE);  
		 
		dist[0] = 0;  
		PriorityQueue<Node> pq = new PriorityQueue<>();  
		pq.offer(new Node(0,0));  
		 
		while(!pq.isEmpty()) {  
			int cur = pq.peek().num; 
			int cost = pq.peek().cost; 
			pq.poll();  
			if(cost == Integer.MAX_VALUE) break;  
			if(dist[cur] < cost) continue; 
			 
			for(Node temp : curlist[cur]) { 
				int next = temp.num; 
				int weight = temp.cost; 
				if(dist[next] > cost + weight) {  
					dist[next] = cost + weight;  
					pq.offer(new Node(next, dist[next]));  
				}				  
			}  
		}  
		return dist;  
	}  
}