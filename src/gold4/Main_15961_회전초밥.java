package gold4;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.HashSet; 
import java.util.Set; 
import java.util.StringTokenizer; 
//두포인터
public class Main_15961_회전초밥 { 
	static int N,D,K,C,max; 
	static int[] check; 
	static int[] belt; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		D = Integer.parseInt(tk.nextToken()); 
		K = Integer.parseInt(tk.nextToken()); 
		C = Integer.parseInt(tk.nextToken()); 
		belt = new int[N+K]; 
		for(int i=0; i<N; i++) { 
			belt[i] = Integer.parseInt(br.readLine()); 
		} 
		for(int i=N; i<N+K; i++) { 
			belt[i] = belt[i-N]; 
		} 
		check = new int[3001]; 
		int s = 0, e = 0; 
		Set<Integer> set = new HashSet<Integer>(); 
		set.add(belt[0]); 
		check[belt[0]]++; 
		while(s < N) { 
			if(e-s < K-1) { 
				e++; 
				check[belt[e]]++; 
				set.add(belt[e]); 
			}else if(e-s == K-1) { 
				max = Math.max(max, set.size() + ((set.contains(C)) ? 0 : 1)); 
				check[belt[s]]--; 
				if(check[belt[s]] == 0) set.remove(belt[s]); 
				s++; 
			} 
		} 
		 
		System.out.println(max); 
		 
	} 
 
}