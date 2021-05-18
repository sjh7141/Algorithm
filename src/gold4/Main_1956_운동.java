package gold4;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
 
public class Main_1956_운동 { 
	static class Edge { 
		int n,c; 
 
		public Edge(int n, int c) { 
			this.n = n; 
			this.c = c; 
		} 
		 
	} 
	static int V,E; 
	static ArrayList<Edge>[] list; 
	static int[][] map; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine(), " "); 
		V = Integer.parseInt(tk.nextToken()); 
		E = Integer.parseInt(tk.nextToken()); 
		list = new ArrayList[V+1]; 
		map = new int[V+1][V+1]; 
		for(int i=1; i<V+1; i++) { 
			list[i] = new ArrayList<Edge>(); 
			Arrays.fill(map[i], Integer.MAX_VALUE); 
		} 
		for(int i=0; i<E; i++) { 
			tk = new StringTokenizer(br.readLine(), " "); 
			map[Integer.parseInt(tk.nextToken())][Integer.parseInt(tk.nextToken())] =  Integer.parseInt(tk.nextToken()); 
		} 
		 
		for(int k=1; k<V+1; k++) { 
			for(int i=1; i<V+1; i++) { 
				for(int j=1; j<V+1; j++) { 
					if(map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) continue; 
					if(map[i][j] > map[i][k] + map[k][j]) { 
						map[i][j] = map[i][k] + map[k][j]; 
					} 
				} 
			} 
		} 
		int min = Integer.MAX_VALUE; 
		for(int i=1; i<V+1; i++) { 
			for(int j=1; j<V+1; j++) { 
				if(map[i][j] == Integer.MAX_VALUE || map[j][i] == Integer.MAX_VALUE) continue; 
				min = Math.min(min, map[i][j] + map[j][i]); 
			} 
		} 
		 
		System.out.println(min == Integer.MAX_VALUE ? -1 : min); 
	} 
 
}