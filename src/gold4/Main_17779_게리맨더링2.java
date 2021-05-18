package gold4;
//210324
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.StringTokenizer; 
 
public class Main_17779_게리맨더링2 { 
	//1. 5개의 선거구 
	//2. 선거구는 최소 1개의 구역을 포함 
	//3. 선거구 내부에서는 모두 연결됨 
	//4. 인구 가장 많은 선거구와 적은 선거구 최솟값 
	static int[][] A,map; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	static boolean[][] visit; 
	static int N,x,y,d1,d2; 
	static int min = Integer.MAX_VALUE; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		A = new int[N+1][N+1]; 
		for(int i=1; i<N+1; i++) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			for(int j=1; j<N+1; j++) { 
				A[i][j] = Integer.parseInt(tk.nextToken()); 
			} 
		} 
		for(int i=1; i<N+1; i++) { 
			for(int j=1; j<N+1; j++) { 
				x = i; 
				y = j; 
				findFive(); 
			} 
		} 
		System.out.println(min); 
		 
	} 
	private static void findFive() { 
		int sum = 0; 
		for(int i=1; i<N; i++) { 
			for(int j=1; j<N; j++) { 
				d1 = i; 
				d2 = j; 
				map = new int[N+1][N+1]; 
				if(d1+d2+x <= N && d1+1 <= y && N-d2 >= y) { 
					sum = 0; 
					map[x][y] = 5; 
					sum += A[x][y]; 
					for(int k=1; k<=d1+d2; k++) { 
						int start = (k>d1) ? y+k-2*d1 : y-k; 
						int end = (k>d2) ? y-k+2*d2: y+k; 
						for(int l=start; l<=end; l++) { 
							map[x+k][l] = 5; 
							sum += A[x+k][l]; 
						} 
					} 
					min = Math.min(min, findOthers(sum)); 
				} 
			} 
		} 
 
		 
	} 
	private static int findOthers(int five) { 
		int[] area = new int[6]; 
		area[5] = five; 
		for(int i=1; i<N+1; i++) { 
			for(int j=1; j<N+1; j++) { 
				if(map[i][j] == 0) { 
					if(i<x+d1 && j<=y) { 
						map[i][j] = 1; 
						area[1] += A[i][j]; 
					}else if(i<=x+d2 && j > y) { 
						map[i][j] = 2; 
						area[2] += A[i][j];
					}else if(x+d1 <= i && j < y-d1+d2) { 
						map[i][j] = 3; 
						area[3] += A[i][j]; 
					}else if(x+d2 < i && y-d1+d2 <= j) { 
						map[i][j] = 4; 
						area[4] += A[i][j]; 
					} 
				} 
			} 
		} 
		int min = area[1]; 
		int max = area[1]; 
		for(int i=1; i<6; i++) { 
			min = Math.min(min, area[i]); 
			max = Math.max(max, area[i]); 
		} 
		return max-min; 
		 
	} 
 
}