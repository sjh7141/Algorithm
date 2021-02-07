package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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

		for(int i=0; i<n; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			int e1 = Integer.parseInt(tk.nextToken());
			int e2 = Integer.parseInt(tk.nextToken());
			if(e1 > e2) {
				int temp = e2;
				e2 = e1;
				e1 = temp;
			}
			peopleList[i] = new People(e1, e2);
		}
		d = Integer.parseInt(br.readLine());
		Arrays.sort(peopleList);
		int ans = 0, cnt = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for(People p : peopleList) {
			while(!pq.isEmpty() && pq.peek() < p.o - d) {
				pq.poll();
				cnt--;
			}
			if(p.h >= p.o - d) {
				cnt++;
				pq.add(p.h);
			}
			
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}

}
