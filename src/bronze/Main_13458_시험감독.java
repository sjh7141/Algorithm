package bronze;
 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.StringTokenizer; 
 
public class Main_13458_시험감독 { 
	static int N,B,C; 
	static int[] A; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		A = new int[N]; 
		for(int i=0; i<N; i++) { 
			A[i] = Integer.parseInt(tk.nextToken()); 
		} 
		tk = new StringTokenizer(br.readLine()); 
		B = Integer.parseInt(tk.nextToken()); 
		C = Integer.parseInt(tk.nextToken()); 
		long ans = 0; 
		for(int i=0; i<N; i++) { 
			int cur = A[i]; 
			ans++; 
			if(cur > B) { 
				ans += (long)Math.ceil((double)(cur-B)/C); 
			} 
		} 
		System.out.println(ans); 
 
	} 
 
}