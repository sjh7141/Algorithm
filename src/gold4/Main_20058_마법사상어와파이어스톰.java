package gold4;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.StringTokenizer; 
 
public class Main_20058_마법사상어와파이어스톰 { 
	static int N,Q,len,max; 
	static int[][] map, melt; 
	static int[] spell; 
	static boolean[][] visit; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		Q = Integer.parseInt(tk.nextToken()); 
		len = (int) Math.pow(2, N); 
		map = new int[len][len]; 
		spell = new int[Q]; 
		for(int i=0; i<len; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			for(int j=0; j<len; j++) { 
				map[i][j] = Integer.parseInt(tk.nextToken()); 
			} 
		} 
		tk = new StringTokenizer(br.readLine()); 
		for(int i=0; i<Q; i++) { 
			spell[i] = Integer.parseInt(tk.nextToken()); 
		} 
		//파이어스톰 시전 
		for(int t=0; t<Q; t++) { 
			int L = (int)Math.pow(2, spell[t]); 
			//격자 시계방향 90도 회전 
			for(int i=0; i<len; i+=L) { 
				for(int j=0; j<len; j+=L) { 
					rotation(i,j,L); 
				} 
			} 
			//얼음 3칸이상 생존 미만은 얼음양 1 다운 
			melt = new int[len][len]; 
			for(int i=0; i<len; i++) { 
				for(int j=0; j<len; j++) { 
					if(map[i][j] > 0) { 
						int cnt = 0; 
						for(int k=0; k<4; k++) { 
							int r = i + dir[k][0]; 
							int c = j + dir[k][1]; 
							if(r<0||c<0||r>len-1||c>len-1||map[r][c]<1)continue; 
							cnt++; 
						} 
						if(cnt<3) { 
							melt[i][j]++; 
						} 
					} 
				} 
			} 
			 
			for(int i=0; i<len; i++) { 
				for(int j=0; j<len; j++) { 
					if(melt[i][j] > 0) { 
						map[i][j] -= melt[i][j]; 
					} 
				} 
			} 
		} 
		//남아있는 얼음 양 출력 
		int sum = 0; 
		for(int i=0; i<len; i++) { 
			for(int j=0; j<len; j++) { 
				if(map[i][j] > 0) { 
					sum += map[i][j]; 
				} 
			} 
			} 
			System.out.println(sum); 
			//가장 큰 덩어리 출력 
			visit = new boolean[len][len]; 
			for(int i=0; i<len; i++) { 
				for(int j=0; j<len; j++) { 
					if(map[i][j] > 0  && !visit[i][j]) { 
						visit[i][j] = true; 
						max = Math.max(max, dfs(i,j)+1); 
					} 
				} 
			} 
			System.out.println(max); 
			 
		} 
		private static int dfs(int r, int c) { 
			int sum = 0; 
			for(int k=0; k<4; k++) { 
				int tr = r + dir[k][0]; 
				int tc = c + dir[k][1]; 
				if(tr<0||tc<0||tr>len-1||tc>len-1||map[tr][tc]<1||visit[tr][tc])continue; 
				visit[tr][tc] = true; 
				sum += dfs(tr,tc) + 1; 
			} 
			return sum; 
		} 
		private static void rotation(int sr, int sc, int l) { 
			int[][] temp = new int[l][l]; 
			for(int i=0; i<l; i++) { 
				for(int j=0; j<l; j++) { 
					temp[j][l-i-1] = map[sr+i][sc+j]; 
				} 
			} 
			 
			for(int i=0; i<l; i++) { 
				for(int j=0; j<l; j++) { 
					map[sr+i][sc+j] = temp[i][j]; 
				} 
			} 
			 
		} 
	 
	}