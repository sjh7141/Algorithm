package gold4;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
 
public class Main_14938_서강그라운드 { 
	static class Node implements Comparable<Node>{ 
		int num,cost; 
		 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
	} 
	static int N,M,R,ans; 
	static int[][] map; 
	static int[] item; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		R = Integer.parseInt(tk.nextToken()); 
		map = new int[N+1][N+1]; 
		item = new int[N+1]; 
		 
		for(int i=0; i<N+1; i++) { 
			Arrays.fill(map[i], Integer.MAX_VALUE); 
			map[i][i] = 0; 
		} 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=1; i<N+1; i++) { 
			item[i] = Integer.parseInt(tk.nextToken()); 
		} 
		 
		for(int i=0; i<R; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(tk.nextToken()); 
			int b = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[a][b] = l; 
			map[b][a] = l; 
		} 
		 
		for(int k=1; k<N+1; k++) { 
			for(int i=1; i<N+1; i++) { 
				if(k==i) continue; 
				for(int j=1; j<N+1; j++) { 
					if(k==j||i==j) continue; 
					if(map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE && map[i][j] > map[i][k] + map[k][j]) { 
						map[i][j] = map[i][k] + map[k][j]; 
					} 
					 
				} 
			} 
		} 
		 
		for(int i=1; i<N+1; i++) { 
			int temp = 0; 
			for(int j=1; j<N+1; j++) { 
				if(map[i][j] != Integer.MAX_VALUE && map[i][j] <= M) temp += item[j]; 
			} 
			ans = Math.max(ans, temp); 
		} 
		 
		System.out.println(ans); 
	} 
 
}