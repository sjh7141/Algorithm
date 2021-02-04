package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2170_선긋기 {
	static class Pair implements Comparable<Pair>{
		int s,e;

		public Pair(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Pair p) {
			if(s==p.s) {
				return e-p.e;
			}
			return s-p.s;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			pq.offer(new Pair(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())));
		}
		
		int ans = 0, s = Integer.MIN_VALUE, e = Integer.MIN_VALUE;
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			if(cur.s>e) {
				ans += e-s;
				s = cur.s;
				e = cur.e;
			}else {
				e = Integer.max(e, cur.e);
			}
		}
		ans += e-s;
		
		System.out.println(ans);
		
		
		
	}

}
