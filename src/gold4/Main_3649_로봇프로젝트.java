package gold4;

import java.io.BufferedReader; 
import java.io.FileInputStream; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
 
public class Main_3649_로봇프로젝트 { 
	static int X,N; 
	static int[] rego; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String input = ""; 
		while((input=br.readLine())!=null) { 
			X = Integer.parseInt(input) * 10000000; 
			N = Integer.parseInt(br.readLine()); 
			rego = new int[N]; 
			for(int i=0; i<N; i++) { 
				rego[i] = Integer.parseInt(br.readLine()); 
			} 
			 
			Arrays.sort(rego); 
			 
			int s = 0, e = N-1; 
			boolean flag = false; 
			while(s<e) { 
				int cur = rego[s] + rego[e]; 
				if(cur == X) { 
					flag = true; 
					break; 
				}else if(cur < X) { 
					s++; 
				}else { 
					e--; 
				} 
				 
			} 
			 
			System.out.println((flag) ? "yes " + rego[s] + " " + rego[e]: "danger"); 
		} 
 
		 
	} 
 
}