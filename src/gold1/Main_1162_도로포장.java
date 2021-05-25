package gold1;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
 
public class Main_1162_도로포장 { 
	static class Node implements Comparable<Node>{ 
		int num, cnt; 
		long cost; 
		 
		public Node(int num, int cnt, long cost) { 
			this.num = num; 
			this.cnt = cnt; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return Long.compare(cost, o.cost); 
		} 
	} 
	static ArrayList<Node>[] map; 
	static int N,M,K; 
	static long dist[][]; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[N+1];
		dist = new long[N+1][K+1];
		
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>();
			Arrays.fill(dist[i], Long.MAX_VALUE);
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(tk.nextToken()); 
			int b = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[a].add(new Node(b, 0, l)); 
			map[b].add(new Node(a, 0, l)); 
		} 
		 
		long ans = dijk(); 
		 
		System.out.println(ans); 
		
	}  
	private static long dijk() { 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
				 
		dist[1][0] = 0; 
		pq.offer(new Node(1,0,0)); 
		
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Long.MAX_VALUE) break;
			if(cur.cost > dist[cur.num][cur.cnt]) continue;
			
			for(int i=0, size=map[cur.num].size(); i<size; i++) { 
				Node plus = map[cur.num].get(i); 
				if(dist[plus.num][cur.cnt] > dist[cur.num][cur.cnt] + plus.cost) { 
					dist[plus.num][cur.cnt] = dist[cur.num][cur.cnt] + plus.cost;
					pq.offer(new Node(plus.num, cur.cnt, dist[plus.num][cur.cnt]));
				} 
				if(cur.cnt < K && dist[plus.num][cur.cnt+1] > dist[cur.num][cur.cnt]) { 
					dist[plus.num][cur.cnt+1] = dist[cur.num][cur.cnt]; 
					pq.offer(new Node(plus.num, cur.cnt+1, dist[plus.num][cur.cnt+1])); 
				} 
			} 
		} 
		 
		long min = Long.MAX_VALUE; 
		for(int i=0; i<K+1; i++) { 
			min = Math.min(min, dist[N][i]); 
		} 
		 
		return min; 
		
	}
	
//	잘못된풀이 - 재귀로 구현시 시간복잡도가 넘어갈 것으로 생각해서 dp를 적용했으나
//	노드를 방문 할 때  현재 까지의 방문, 도로포장 상태가 고려되지 않았다.
//
//	dp = new long[N+1][K+1]; 
//	for(int i=0; i<N+1; i++) { 
//		Arrays.fill(dp[i], (i==N)? 0 : Long.MAX_VALUE); 
//	} 
//	visit[1] = true; 
//	long ans = solve(1, K, 0); 
//	 
//	System.out.println(ans); 
//}
//	private static long solve(int num, int cnt, long time) { 
//	if(dp[num][cnt] != Long.MAX_VALUE) { 
//		return dp[num][cnt]; 
//	} 
//	long min = Long.MAX_VALUE; 
//	for(int i=0, size=map[num].size(); i<size; i++) { 
//		Node next = map[num].get(i); 
//		if(visit[next.num]) continue; 
//		visit[next.num] = true; 
//		min = Math.min(min, next.cost + solve(next.num, cnt, time+next.cost)); 
//		if(cnt > 0) { 
//			min = Math.min(min, solve(next.num, cnt-1, time)); 
//		} 
//		visit[next.num] = false; 
//	} 
//	 
//	return dp[num][cnt] = min; 
//} 
// 2번째 풀이
// while문을 아래 코드와 같이 클래스를 넣고 돌려서 만들었다 -> 시간초과
//	while(!pq.isEmpty()) { 
//		Node cur = pq.poll(); 
//		if(cur.cost == Long.MAX_VALUE) break; 
//		for(int i=0, size=map[cur.num].size(); i<size; i++) { 
//			Node plus = map[cur.num].get(i); 
//			if(pq.contains(costs[plus.num][cur.cnt]) && costs[plus.num][cur.cnt].cost > cur.cost + plus.cost) { 
//				pq.remove(costs[plus.num][cur.cnt]); 
//				costs[plus.num][cur.cnt].cost = cur.cost + plus.cost; 
//				pq.offer(costs[plus.num][cur.cnt]); 
//			} 
//			if(cur.cnt > 0 && pq.contains(costs[plus.num][cur.cnt-1]) && costs[plus.num][cur.cnt-1].cost > cur.cost) { 
//				pq.remove(costs[plus.num][cur.cnt-1]); 
//				costs[plus.num][cur.cnt-1].cost = cur.cost; 
//				pq.offer(costs[plus.num][cur.cnt-1]); 
//			} 
//		} 
//	} 
}