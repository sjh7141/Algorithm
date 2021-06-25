package gold5;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.StringTokenizer; 
 
public class Main_14921_용액합성하기 { 
	static int N; 
	static int[] liq;
	//두 포인터
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		liq = new int[N]; 
		for(int i=0; i<N; i++) { 
			liq[i] = Integer.parseInt(tk.nextToken()); 
		} 
		 
		int temp = Integer.MAX_VALUE; 
		int ans = Integer.MAX_VALUE; 
		int s = 0, e = N-1; 
		while(s<e) { 
			int cur = liq[s] + liq[e]; 
			if(cur < 0) { 
				if(temp > Math.abs(cur)) { 
					temp = Math.abs(cur); 
					ans = cur; 
				} 
				s++; 
			}else if(cur > 0) { 
				if(temp > Math.abs(cur)) { 
					temp = Math.abs(cur); 
					ans = cur; 
				} 
				e--; 
			}else { 
				ans = 0; 
				break; 
			} 
		} 
		 
		System.out.println(ans); 
		 
 
	} 
 
}