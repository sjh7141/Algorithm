package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_15903_카드합체놀이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(tk.nextToken());
		int m = Integer.parseInt(tk.nextToken());
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) {
			pq.offer(Long.parseLong(tk.nextToken()));
		}
		
		for(int i=0; i<m; i++) {
			long a1 = pq.poll();
			long a2 = pq.poll();
			pq.offer(a1+a2);
			pq.offer(a1+a2);
		}
		
		long ans = 0;
		while(!pq.isEmpty()) {
			ans += pq.poll();
		}
		
		System.out.println(ans);

	}

}
