package gold1;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
// 640ms
// 플로이드 와샬은 N^3M^3 에 걸려서 시간초과가 날 것이라 예상했는데 아슬아슬하게 통과과 되는 것 같아 보인다.
// 평소 문제를 풀 때 플로이드 와샬을 사용하면 굉장히 간단해진다는 인상이 강했는데 그럼에도 불구하고 굉장히 시도를 많이했다. 조건에 신경 써야 할 부분이 많아서라고 생각한다.
public class Main_1486_등산_플로이드와샬 { 
	static int N,M,T,D; 
	static int[][] map; 
	static int[][] height; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		T = Integer.parseInt(tk.nextToken()); 
		D = Integer.parseInt(tk.nextToken()); 
		map = new int[N*M][N*M]; 
		height = new int[N][M]; 
		for(int i=0; i<N; i++) { 
			String str = br.readLine(); 
			for(int j=0; j<M; j++) { 
				char cur = str.charAt(j); 
				if(cur >= 'A' && cur <= 'Z') height[i][j] = (cur - 'A'); 
				else height[i][j] = (cur - 'a') + 26; 
			} 
		} 
		 
		for(int i=0; i<N*M; i++) { 
			Arrays.fill(map[i], Integer.MAX_VALUE); 
			map[i][i] = 0; 
			 
		} 
		 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++){ 
				for(int k=0; k<4; k++) {  
					int tr = i + dir[k][0];  
					int tc = j + dir[k][1];  
					if(tr<0 || tc<0 || tr>N-1 || tc>M-1 || Math.abs(height[i][j] - height[tr][tc]) > T) continue;  
					int from = i*M+j, to = tr*M+tc, gap = Math.abs(height[i][j] - height[tr][tc]); 
					if (height[i][j] >= height[tr][tc]) { 
						map[from][to] = 1; 
					} else { 
						map[from][to] = gap*gap; 
					} 
				}  
			} 
		} 
		 
		for(int k=0; k<N*M; k++) { 
			for(int i=0; i<N*M; i++) { 
				for(int j=0; j<N*M; j++) { 
					if(map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE && map[i][j] > map[i][k] + map[k][j]) { 
						map[i][j] = map[i][k] + map[k][j]; 
					} 
				} 
			} 
		} 
		 
		int ans = 0; 
		 
		for(int i=0; i<N*M; i++) {			 
			if(map[0][i] != Integer.MAX_VALUE && map[i][0] != Integer.MAX_VALUE && map[0][i] + map[i][0] <= D) { 
				ans = Math.max(ans, height[i/M][i%M]); 
			} 
		} 
		System.out.println(ans); 
		 
	} 
}