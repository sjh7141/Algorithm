package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20057_마법사상어와토네이도 {
	static int[][] dir = {{0,-1},{1,0},{0,1},{-1,0}};
	static int N, ans;
	static int[][] A;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				A[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		int r = N/2, c = N/2, d = 0;
		int len = 1, cur = 0;
		boolean inc = false;
		while(!(r == 0 && c == 0)) {
			//회전
			if(cur == len) {
				cur = 0;
				d = (d+1)%4;
				if(inc) {
					inc = false;
					len++;
				}else {
					inc = true;
				}
			}
			cur++;
			r += dir[d][0];
			c += dir[d][1];
			//모래처리
			if(A[r][c] != 0) {
				send(r,c,d);
			}
		}
		System.out.println(ans);

	}
	private static void send(int r, int c, int d) {
		int cur = A[r][c], sub = 0;
		double[] percentage = {0.02,0.07,0.1,0.01};		
		
		int tr = r + dir[d][0] * 2;
		int tc = c + dir[d][1] * 2;
		if(check(tr,tc)) {
			A[tr][tc] += (int)(cur*0.05);
		}else {
			ans += (int)(cur*0.05);
		}
		sub += (int)(cur*0.05);
		for(int i=0; i<4; i++) {
			int td = d;
			for(int j=0; j<2; j++) {
				td = (j==0) ?(d+1)%4:(d+3)%4;
			
				switch(i) {
					case 0:
						tr = r + dir[td][0] * 2;
						tc = c + dir[td][1] * 2;
						break;
					case 1:
						tr = r + dir[td][0];
						tc = c + dir[td][1];
						break;
					case 2:
						tr = r + dir[td][0] + dir[d][0];
						tc = c + dir[td][1] + dir[d][1]; 
						break;
					case 3:
						tr = r + dir[td][0] + dir[(d+2)%4][0];
						tc = c + dir[td][1] + dir[(d+2)%4][1]; 
						break;
						
				}
				if(check(tr,tc)) {
					A[tr][tc] += (int)(cur*percentage[i]);
				}else {
					ans += (int)(cur*percentage[i]);
				}
				sub += (int)(cur*percentage[i]);
			}
		}
		
		//a
		tr = r + dir[d][0];
		tc = c + dir[d][1];
		if(check(tr,tc)) {
			A[tr][tc] += cur-sub;
		}else {
			ans += cur-sub ;
		}
		A[r][c] = 0;

	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
