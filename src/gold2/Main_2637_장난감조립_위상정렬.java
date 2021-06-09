package gold2;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
 
public class Main_2637_장난감조립_위상정렬 { 
	static int N,M; 
	static ArrayList<int[]>[] recipe; 
	static int[][] count; 
	static int[] degree; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		M = Integer.parseInt(br.readLine()); 
		recipe = new ArrayList[N+1]; 
		count = new int[N+1][N+1]; 
		degree = new int[N+1]; 
		 
		for(int i=0; i<N+1; i++) { 
			recipe[i] = new ArrayList<>(); 
		} 
		 
		for(int i=0; i<M; i++) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			int X = Integer.parseInt(tk.nextToken()); 
			int Y = Integer.parseInt(tk.nextToken()); 
			int K = Integer.parseInt(tk.nextToken()); 
			recipe[Y].add(new int[] {X,K}); 
			degree[X]++; 
		} 
		 
		 
		topologySort(); 
 
		for(int i=1; i<N+1; i++) { 
			if(count[N][i] != 0) { 
				System.out.println(i + " " + count[N][i]); 
			} 
		} 
	} 
	private static void topologySort() { 
		ArrayDeque<Integer> deq = new ArrayDeque<>(); 
		for(int i=1; i<N+1; i++) { 
			if(degree[i] == 0) { 
				deq.add(i); 
				count[i][i] = 1; 
			} 
		} 
		 
		while(!deq.isEmpty()) { 
			int cur = deq.poll(); 
			for(int[] temp : recipe[cur]) { 
				int next = temp[0]; 
				int cnt = temp[1]; 
				for(int i=1; i<N+1; i++) { 
					if(count[cur][i] != 0) { 
						count[next][i] += count[cur][i]*cnt; 
					} 
				} 
				degree[next]--; 
				if(degree[next] == 0) { 
					deq.add(next); 
				} 
			} 
		} 
		 
	} 
	 
 
}