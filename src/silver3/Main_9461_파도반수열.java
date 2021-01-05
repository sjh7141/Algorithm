package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9461_파도반수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			long[] P = new long[N+1];
			for(int i=1; i<N+1; i++) {
				if(i<4) {
					P[i] = 1; 
					continue;
				}else {
					P[i] = P[i-3] + P[i-2];
				}
			}
			System.out.println(P[N]);
		}
	}

}
