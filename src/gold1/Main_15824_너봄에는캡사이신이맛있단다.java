package gold1;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
import java.util.StringTokenizer; 
 
 
public class Main_15824_너봄에는캡사이신이맛있단다 { 
	static int MOD = 1000000007; 
	static int N; 
	static long ans; 
	static int[] menu; 
	static long[] pow; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		menu = new int[N+1]; 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		for(int i=1; i<N+1; i++) { 
			menu[i] = Integer.parseInt(tk.nextToken()); 
		} 
		pow = new long[300001]; 
		pow[0] = 1; 
		for(int i=1; i<300001; i++) { 
			pow[i] = (pow[i-1] * 2) % MOD;  
		} 
		 
		Arrays.sort(menu); 
		for(int i=1; i<N+1; i++) { 
			ans += (menu[i] % MOD) * pow[i-1] % MOD;  
			ans -= (menu[i] % MOD) * pow[N-i] % MOD;
            ans = ((ans%MOD) + MOD) % MOD;
		} 
		 
		System.out.println(ans); 
	} 
 
}