package gold5;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main_13549_숨바꼭질3 { 
	static class Pos implements Comparable<Pos>{ 
		int num, time; 
 
		public Pos(int num, int time) { 
			this.num = num; 
			this.time = time; 
		} 
 
		@Override 
		public int compareTo(Pos o) { 
			return time - o.time; 
		} 
		 
	} 
	static int N,K,ans; 
	static boolean[] visit; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
				 
		//다익스트라 
		dijk(); 
		//0-1 BFS 
		bfs(); 
		 
		System.out.println(ans); 
	} 
	 
	private static void bfs() { 
		visit = new boolean[100001]; 
		ArrayDeque<Pos> deq = new ArrayDeque<>(); 
		deq.offer(new Pos(N,0)); 
		 
		while(!deq.isEmpty()) { 
			Pos cur = deq.poll(); 
			if(visit[cur.num]) continue; 
			visit[cur.num] = true; 
			if(cur.num==K) { 
				ans = cur.time; 
				break; 
			} 
			if(cur.num*2 < 100001 && !visit[cur.num*2]) deq.offerFirst(new Pos(cur.num*2, cur.time)); 
			if(cur.num-1 >= 0 && !visit[cur.num-1]) deq.offerLast(new Pos(cur.num-1, cur.time+1)); 
			if(cur.num+1 < 100001 && !visit[cur.num+1]) deq.offerLast(new Pos(cur.num+1, cur.time+1)); 
		} 
		 
	} 
 
	private static void dijk() { 
		visit = new boolean[100001]; 
		PriorityQueue<Pos> pq = new PriorityQueue<>(); 
		pq.offer(new Pos(N, 0)); 
		 
		while(!pq.isEmpty()) { 
			Pos cur = pq.poll(); 
			if(visit[cur.num]) continue; 
			visit[cur.num] = true; 
			if(cur.num==K) { 
				ans = cur.time; 
				break; 
			} 
			if(cur.num*2 < 100001 && !visit[cur.num*2]) pq.offer(new Pos(cur.num*2, cur.time)); 
			if(cur.num-1 >= 0 && !visit[cur.num-1]) pq.offer(new Pos(cur.num-1, cur.time+1)); 
			if(cur.num+1 < 100001 && !visit[cur.num+1]) pq.offer(new Pos(cur.num+1, cur.time+1));
		}
	}
}