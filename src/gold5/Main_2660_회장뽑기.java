package gold5;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
 
public class Main_2660_회장뽑기 { 
	static int N; 
	static int[][] friends; 
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		friends = new int[N+1][N+1]; 
		while(true) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(tk.nextToken()); 
			int b = Integer.parseInt(tk.nextToken()); 
			if (a==-1 && b == -1) break; 
			friends[a][b] = 1; 
			friends[b][a] = 1; 
		} 
		 
		for(int k=1; k<N+1; k++) { 
			for(int i=1; i<N+1; i++) { 
				for(int j=1; j<N+1; j++) { 
					if(i==j) continue; 
					if(friends[i][k] > 0 && friends[k][j] > 0) { 
						if(friends[i][j] == 0) friends[i][j] = friends[i][k] + friends[k][j]; 
						else friends[i][j] = Math.min(friends[i][j], friends[i][k] + friends[k][j]); 
					} 
				} 
			} 
		} 
	 
		int ans = 50; 
		ArrayList<Integer> candi = new ArrayList<Integer>(); 
		for(int i=1; i<N+1; i++) { 
			Arrays.sort(friends[i]); 
			ans = Math.min(ans, friends[i][N]);			 
		} 
		 
		for(int i=1; i<N+1; i++) { 
			if(ans == friends[i][N]) candi.add(i); 
		} 
		 
		System.out.println(ans + " " + candi.size()); 
		for(int i=0, size = candi.size(); i<size; i++) { 
			System.out.print(candi.get(i)+ " "); 
		} 
		 
	} 
 
}