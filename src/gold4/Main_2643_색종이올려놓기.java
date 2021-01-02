package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2643_색종이올려놓기 {
	static int[][] paper;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		paper = new int[N][2];
		dp = new int[N];
		for(int i=0; i<N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(tk.nextToken());
			int h = Integer.parseInt(tk.nextToken());
			int temp = w;
			if(w < h) {
				temp = h;
				h = w;
				w = temp;
			}
			paper[i][0] = w;
			paper[i][1] = h;
		}
		
		Arrays.sort(paper, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] != o2[0]) {
					return o2[0] - o1[0];
				}else {
					return o2[1] - o1[1];
				}
			}
		});
		
		for(int i=0; i<N; i++) {
			if(dp[i] == 0) dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(paper[i][0]<=paper[j][0] && paper[i][1]<= paper[j][1]) {
					dp[i] = Integer.max(dp[i], dp[j]+1);
				}
			}
		}
		
		Arrays.sort(dp);
		System.out.println(dp[N-1]);

	}

}
