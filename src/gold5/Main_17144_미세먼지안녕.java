package gold5;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.StringTokenizer; 
 
public class Main_17144_미세먼지안녕 { 
	static int R,C,T,ans; 
	static int[][] map; 
	static int[] car1, car2; 
	static int[][] dir = {{0,1},{-1,0},{0,-1},{1,0}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		R = Integer.parseInt(tk.nextToken()); 
		C = Integer.parseInt(tk.nextToken()); 
		T = Integer.parseInt(tk.nextToken()); 
		map = new int[R][C]; 
		for(int i=0; i<R; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			for(int j=0; j<C; j++) { 
				int cur = Integer.parseInt(tk.nextToken()); 
				map[i][j] = cur; 
				if(cur == -1) { 
					if(car1 == null) { 
						car1 = new int[] {i,j}; 
					}else { 
						car2 = new int[] {i,j}; 
					} 
				} 
			} 
		} 
		while(T-- > 0) { 
			int[][] tempM = new int[R][C]; 
			//1 확산 
			for(int i=0; i<R; i++) { 
				for(int j=0; j<C; j++) { 
					if(map[i][j] > 0) { 
						int cnt = 0; 
						for(int k=0; k<4; k++) { 
							int tr = i + dir[k][0]; 
							int tc = j + dir[k][1]; 
							if(tr<0||tc<0||tr>R-1||tc>C-1||map[tr][tc] == -1) continue; 
							cnt++; 
							tempM[tr][tc] += map[i][j]/5; 
						} 
						map[i][j] -= map[i][j]/5*cnt; 
					} 
				} 
			}
			for(int i=0; i<R; i++) { 
				for(int j=0; j<C; j++) { 
					map[i][j] += tempM[i][j]; 
				} 
			} 
//			System.out.println("+++++++++++++");
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) { 
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			//2 공기청정기 
			//윗부분
			int td = 0; 
			int r = car1[0] + dir[td][0]; 
			int c = car1[1] + dir[td][1]; 
			int pre = 0; 
			while(map[r][c] != -1) {
				int temp = map[r][c]; 
				map[r][c] = pre; 
				pre = temp; 
				int tr = r + dir[td][0]; 
				int tc = c + dir[td][1]; 
				if(tr<0||tc<0||tr>R-1||tc>C-1) { 
					td = (td + 1)%4; 
					tr = r + dir[td][0]; 
					tc = c + dir[td][1]; 
				} 
				r = tr;
				c = tc;
			}
			//아랫부분
			td = 0;
			r = car2[0] + dir[td][0]; 
			c = car2[1] + dir[td][1]; 
			pre = 0; 
			while(map[r][c] != -1) {
				int temp = map[r][c]; 
				map[r][c] = pre; 
				pre = temp; 
				int tr = r + dir[td][0]; 
				int tc = c + dir[td][1]; 
				if(tr<0||tc<0||tr>R-1||tc>C-1) { 
					td = (td + 3)%4; 
					tr = r + dir[td][0]; 
					tc = c + dir[td][1]; 
				} 
				r = tr;
				c = tc;
			}
			
		} 

		for(int i=0; i<R; i++) { 
			for(int j=0; j<C; j++) { 
				if(map[i][j] > 0) {
					ans+= map[i][j];
				}
			}
		}
		System.out.println(ans);
	}
}