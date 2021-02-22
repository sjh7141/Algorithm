package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1034_램프 {
	static int N,M,K,ans;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int cnt = 0;
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) cnt++;
			}
			if(K<cnt||(K-cnt)% 2 == 1)continue;
			int num = 0;
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(map[i][k] != map[j][k]) break;
					if(k == M-1) num++;
				}
			}
			ans = Math.max(ans, num);
		}
		System.out.println(ans);

	}

}
