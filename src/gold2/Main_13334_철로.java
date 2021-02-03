package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13334_철로 {
	static class People implements Comparable<People>{
		int h,o;

		public People(int h, int o) {
			this.h = h;
			this.o = o;
		}

		@Override
		public int compareTo(People p) {
			if(o == p.o) {
				return h - p.h;
			}else {
				return o - p.o;
			}
		}
		
	}
	static int n,d;
	static People[] peopleList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		peopleList = new People[n];
		PriorityQueue<People> pq = new PriorityQueue<People>();
		ArrayDeque<People> deq = new ArrayDeque<People>();
		for(int i=0; i<n; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			int e1 = Integer.parseInt(tk.nextToken());
			int e2 = Integer.parseInt(tk.nextToken());
			if(e1 > e2) {
				int temp = e2;
				e2 = e1;
				e1 = temp;
			}
			pq.add(new People(e1, e2));
		}
		d = Integer.parseInt(br.readLine());
		
		int ans = 0, s = Integer.MIN_VALUE, e = Integer.MIN_VALUE;
		while(!pq.isEmpty()) {
			People cur = pq.poll();
			if(cur.o-cur.h > d) continue;
			if(cur.h>=s && cur.o<=e) {
				deq.add(cur);
				ans = Math.max(ans, deq.size());
				continue;
			}else {
				if(s==Integer.MIN_VALUE && e==Integer.MIN_VALUE) {
					deq.add(cur);
					s = cur.h;
					e = cur.o;
					ans = Math.max(ans, deq.size());
				}else {
					e = cur.o;
					if(e-s>d){
						while(true) {
							deq.poll();
							if(deq.isEmpty()) break;
							s = deq.peek().h;
							if(e-s<=d) break; 
						}
					}
					deq.add(cur);
					ans = Math.max(ans, deq.size());
					
				}
			}
		}
		
		System.out.println(ans);
	}

}
