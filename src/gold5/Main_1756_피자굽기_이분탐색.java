package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1756_피자굽기_이분탐색 {
	static int D,N,ans;
	static int[] oven, pizza;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		D = Integer.parseInt(tk.nextToken());
		N = Integer.parseInt(tk.nextToken());
		oven = new int[D+1];
		pizza = new int[N];
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<D+1; i++) {
			oven[i] = Integer.parseInt(tk.nextToken());
		}
		
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			pizza[i] = Integer.parseInt(tk.nextToken());
		}
		
		for(int i=2; i<D+1; i++) {
			if(oven[i-1]<oven[i]) oven[i] = oven[i-1];
		}
		
		int bottom = D+1;
		for(int i=0; i<N; i++) {
			bottom = lowerBound(bottom,pizza[i]) - 1;
			if(bottom<=0) break;
		}
		System.out.println(bottom < 0 ? 0 : bottom);
		
	}
	
	public static int lowerBound(int b, int n) {
		int s = 0, e = b;
		while(s<e) {
			int m = (s+e)/2;
			if(oven[m] < n) {
				e = m;
			}else {
				s = m+1;
			}
		}
		return e;
	}

}
