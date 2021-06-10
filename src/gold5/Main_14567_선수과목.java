package gold5;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.StringTokenizer; 
 
public class Main_14567_선수과목 { 
	static int N,M; 
	static int[] degree; 
	static int[] ans; 
	static ArrayList<Integer>[] list; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		degree = new int[N+1]; 
		ans = new int[N+1]; 
		list = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			list[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int A = Integer.parseInt(tk.nextToken()); 
			int B = Integer.parseInt(tk.nextToken()); 
			list[A].add(B); 
			degree[B]++; 
		} 
		 
		topologySort(); 
		 
		for(int i=1; i<N+1; i++) { 
			System.out.print(ans[i] + " "); 
		} 
	} 
	private static void topologySort() { 
		ArrayDeque<Integer> deq = new ArrayDeque<>(); 
		for(int i=1; i<N+1; i++) { 
			if(degree[i] == 0) { 
				deq.add(i); 
				ans[i] = 1; 
			} 
		} 
		 
		while(!deq.isEmpty()) { 
			int cur = deq.poll(); 
			for(int next : list[cur]) { 
				degree[next]--; 
				ans[next] = Math.max(ans[next], ans[cur] + 1); 
				if(degree[next] == 0) { 
					deq.add(next); 
				} 
			} 
		} 
	} 
 
}