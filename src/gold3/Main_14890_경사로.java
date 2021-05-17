package gold3;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.StringTokenizer; 
 
public class Main_14890_경사로 { 
	static int N,L,ans; 
	static int[][] map; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		L = Integer.parseInt(tk.nextToken()); 
		map = new int[N][N]; 
		for(int i=0; i<N; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			for(int j=0; j<N; j++) { 
				map[i][j] = Integer.parseInt(tk.nextToken()); 
			} 
		} 
		 
		for(int i=0; i<N; i++) { 
			solve(i,0,true,new int[] {map[i][0],0}, false); 
			solve(0,i,false,new int[] {map[0][i],0}, false); 
		} 
		 
		System.out.println(ans); 
	} 
	private static void solve(int r, int c, boolean isRow, int[] pre, boolean check) { 
		if(r == N || c == N) { 
			if(!check) { 
				ans++;	 
			} 
			return; 
		} 
		 
		if(pre[0] != map[r][c]) { 
			if(check) return; 
			if(map[r][c] == pre[0]+1 && pre[1] >= L) { 
				pre = new int[] {map[r][c], 1}; 
			}else if(map[r][c] == pre[0]-1) { 
				if(L != 1) check = true; 
				pre = new int[] {map[r][c], (L == 1) ? 0 : 1}; 
			}else { 
				return; 
			} 
			 
		}else { 
			pre[1] += 1; 
			if(check && pre[1] >= L) { 
				check = false; 
				pre[1] -= L; 
			} 
		} 
		 
		 
		if(isRow) { 
			solve(r,c+1,isRow,pre,check); 
		}else { 
			solve(r+1,c,isRow,pre,check);	 
		} 
		 
	} 
 
}