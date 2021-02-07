package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10830_행렬제곱 {
	static int N;
	static long B;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		B = Long.parseLong(tk.nextToken());
		int[][] mat = new int[N][N];
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				mat[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		int[][] ans = solve(mat, B);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(ans[i][j]%1000+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int[][] multiply(int[][] mat1, int[][] mat2) {
		int[][] result = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				 int temp = 0;
				 for(int k=0; k<N; k++) {
					 temp += mat1[i][k]*mat2[k][j];
				 }
				 result[i][j] = temp%1000;
			}
		}
		return result;
		
	}
	private static int[][] solve(int[][] a, long b) {
		if(b==1) {
			return a;
		}else if(b%2==0) {
			int[][] temp = solve(a, b/2);
			return multiply(temp, temp);
		}else {
			return multiply(solve(a,b-1), a);
		}
		
	}

}
