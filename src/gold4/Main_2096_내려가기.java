package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2096_내려가기 {
	static int N;
	static int[][] nums, max, min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N][3];
		max = new int[N][3];
		min = new int[N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++){
				nums[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		for(int i=0; i<3; i++) {
			max[0][i] = min[0][i] = nums[0][i];
		}
		for(int i=1; i<N; i++) {
			for(int j=0; j<3; j++) {
				find(i,j);
			}
		}
		
		int ansMin = Integer.MAX_VALUE;
		int ansMax = Integer.MIN_VALUE;
		for(int i=0; i<3; i++) {
			ansMin = Math.min(ansMin, min[N-1][i]);
			ansMax = Math.max(ansMax, max[N-1][i]);
		}
		
		System.out.println(ansMax + " " + ansMin);

	}
	private static void find(int r, int c) {
		for(int i=0; i<3; i++) {
			if(c==0&&i==2 || c==2&i==0)continue;
			if(max[r][c] == 0) {
				max[r][c] = max[r-1][i] + nums[r][c];
				min[r][c] = min[r-1][i] + nums[r][c];
			}else {
				max[r][c] = Math.max(max[r][c], max[r-1][i] + nums[r][c]);
				min[r][c] = Math.min(min[r][c], min[r-1][i] + nums[r][c]);
			}
		}
		
	}

}
