package gold2;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
 
public class Main_2637_장난감조립_dp { 
	static int N,M; 
	static ArrayList<int[]>[] recipe; 
	static boolean[] isCombined, visit; 
	static int[][] count; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		M = Integer.parseInt(br.readLine()); 
		recipe = new ArrayList[N+1]; 
		isCombined = new boolean[N+1]; 
		visit = new boolean[N+1]; 
		count = new int[N+1][N+1]; 
		 
		for(int i=0; i<N+1; i++) { 
			recipe[i] = new ArrayList<>(); 
		} 
		for(int i=0; i<M; i++) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			int X = Integer.parseInt(tk.nextToken()); 
			int Y = Integer.parseInt(tk.nextToken()); 
			int K = Integer.parseInt(tk.nextToken()); 
			recipe[X].add(new int[] {Y,K}); 
			isCombined[X] = true; 
		} 
		 
		 
		for(int[] temp : dfs(N,1)) { 
			System.out.println(temp[0] + " " + temp[1]); 
		} 
 
	} 
	private static ArrayList<int[]> dfs(int num, int cnt) { 
		ArrayList<int[]> cur = new ArrayList<>(); 
		if(visit[num]) { 
			for(int i=1; i<N+1; i++) { 
				if(count[num][i] != 0) { 
					cur.add(new int[] {i,count[num][i]*cnt}); 
				} 
			} 
			return cur; 
		} 
		visit[num] = true; 
		 
		for(int[] temp : recipe[num]) { 
			if(isCombined[temp[0]]) { 
				ArrayList<int[]> bottom = dfs(temp[0],temp[1]); 
				for(int[] bo : bottom) { 
					count[num][bo[0]] += bo[1]; 
				} 
			}else { 
				count[num][temp[0]] += temp[1]; 
			} 
		} 
		 
		for(int i=1; i<N+1; i++) { 
			if(count[num][i] > 0) { 
				cur.add(new int[] {i,count[num][i]*cnt}); 
			} 
		} 
		 
		return cur; 
	} 
 
}