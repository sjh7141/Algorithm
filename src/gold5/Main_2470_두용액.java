package gold5;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
 
public class Main_2470_두용액 { 
	static int N; 
	static int[] liq; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		liq = new int[N]; 
		StringTokenizer tk = new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) { 
			liq[i] = Integer.parseInt(tk.nextToken()); 
		} 
		Arrays.sort(liq); 
 
		int s = 0, e = N-1, temp = Integer.MAX_VALUE; 
		int[] ans = new int[2]; 
		while(s<e) { 
			if(liq[s] + liq[e] == 0) { 
				ans[0] = liq[s]; 
				ans[1] = liq[e]; 
				break; 
			}else{ 
				if(Math.abs(liq[s]+liq[e]) < temp) { 
					temp = Math.abs(liq[s]+liq[e]); 
					ans[0] = liq[s]; 
					ans[1] = liq[e]; 
				} 
 
				if(liq[s] + liq[e] < 0) s++; 
				else e--; 
			} 
		} 
		System.out.println(ans[0]+ " "+ans[1]); 
	} 
 
}