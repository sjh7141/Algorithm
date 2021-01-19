package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2056_작업 {
	static int N;
	static int[] time, indegree, temp;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		time = new int[N+1];
		temp = new int[N+1];
		indegree = new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=1; i<N+1; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(tk.nextToken());
			temp[i] = time[i];
			int n = Integer.parseInt(tk.nextToken());
			for(int j=0; j<n; j++) {
				list[Integer.parseInt(tk.nextToken())].add(i);
				indegree[i]++;
			}
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=1; i<N+1; i++) {
			if(indegree[i] == 0) {
				pq.add(i);
			}
		}
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			
			for(int i=0; i<list[cur].size(); i++) {
				int next = list[cur].get(i); 
				if(temp[next] <  temp[cur] + time[next]) {
					temp[next] = temp[cur] + time[next];
				}
				
				if(--indegree[next] == 0) {
					pq.add(next);
				}
			}
		}
		
		int ans = 0;
		for(int i=1; i<N+1; i++) {
			if(ans<temp[i]) {
				ans = temp[i];
			}
		}
		System.out.println(ans);
		
	}

}
