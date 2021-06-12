package gold2;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.StringTokenizer; 
 
public class Main_18780_Timeline { 
	static int N,M,C; 
	static int[] S, indegree; 
	static ArrayList<int[]>[] list; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		C = Integer.parseInt(tk.nextToken()); 
		S = new int[N+1]; 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=1; i<N+1; i++) { 
			S[i] = Integer.parseInt(tk.nextToken()); 
		} 
		 
		indegree = new int[N+1]; 
		list = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			list[i] = new ArrayList<>(); 
		} 
		 
		for(int i=0; i<C; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(tk.nextToken()); 
			int b = Integer.parseInt(tk.nextToken()); 
			int x = Integer.parseInt(tk.nextToken()); 
			list[a].add(new int[] {b,x}); 
			indegree[b]++; 
		} 
		 
		topologySort(); 
		 
		for(int i=1; i<N+1; i++) { 
			System.out.println(S[i]); 
		} 
	} 
	private static void topologySort() { 
		ArrayDeque<Integer> deq = new ArrayDeque<>(); 
		for(int i=1; i<N+1; i++) { 
			if(indegree[i] == 0) { 
				deq.add(i); 
			} 
		} 
		 
		while(!deq.isEmpty()) { 
			int cur = deq.poll(); 
			for(int[] next : list[cur]) { 
				indegree[next[0]]--; 
				S[next[0]] = Math.max(S[next[0]], S[cur] + next[1]); 
				if(indegree[next[0]] == 0) { 
					deq.add(next[0]); 
				} 
			} 
		} 
		 
		 
	} 
 
}