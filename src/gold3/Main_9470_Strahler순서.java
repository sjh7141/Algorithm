package gold3;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.StringTokenizer; 
//위상정렬
public class Main_9470_Strahler순서 { 
	static int T,K,M,P; 
	static int[] degree; 
	static ArrayList<Integer>[] list; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		T = Integer.parseInt(br.readLine()); 
		for(int t=0; t<T; t++) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			K = Integer.parseInt(tk.nextToken()); 
			M = Integer.parseInt(tk.nextToken()); 
			P = Integer.parseInt(tk.nextToken()); 
			degree = new int[M+1]; 
			list = new ArrayList[M+1]; 
			for(int i=0; i<M+1; i++) { 
				list[i] = new ArrayList<>(); 
			} 
			for(int i=0; i<P; i++) { 
				tk = new StringTokenizer(br.readLine()); 
				int A = Integer.parseInt(tk.nextToken()); 
				int B = Integer.parseInt(tk.nextToken()); 
				list[A].add(B); 
				degree[B]++; 
			} 
				 
			int ans = topologySort(); 
			System.out.println(K+ " "+ans); 
		} 
		 
	} 
	private static int topologySort() { 
		ArrayDeque<Integer> deq = new ArrayDeque<>(); 
		int[][] strahler = new int[M+1][2]; 
		for(int i=1; i<M+1; i++) { 
			if(degree[i] == 0) { 
				deq.add(i); 
				strahler[i][1] = strahler[i][0] = 1; 
			} 
		} 
		while(!deq.isEmpty()) { 
			int cur = deq.poll(); 
			if(strahler[cur][1] > 1) { 
				strahler[cur][0]++; 
				strahler[cur][1] = 1; 
			} 
			for(int next : list[cur]) { 
				degree[next]--; 
				if(strahler[next][0] < strahler[cur][0]) { 
					strahler[next][0] = strahler[cur][0]; 
					strahler[next][1] = 1; 
				}else if(strahler[next][0] == strahler[cur][0]) { 
					strahler[next][1]++; 
				} 
				if(degree[next] == 0) { 
					deq.add(next); 
				} 
			} 
		} 
		return strahler[M][0]; 
	} 
 
}