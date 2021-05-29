package gold3;
 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Scanner; 
 
 
public class Main_1644_소수의연속합 { 
	static int N,ans; 
	static boolean[] check; 
	static ArrayList<Integer> prime = new ArrayList<>(); 
	public static void main(String[] args) { 
		Scanner sc = new Scanner(new InputStreamReader(System.in)); 
		N = sc.nextInt(); 
		check = new boolean[N+1]; 
		check[0] = check[1] = true; 
		//에라토스테네스의 체 
		for(int i=2; i*i<N+1; i++) { 
			if(!check[i]) { 
				for(int j=i*i; j<N+1; j+=i) { 
					check[j] = true; 
				} 
			} 
		} 
		 
		for(int i=2; i<N+1; i++) { 
			if(!check[i]) prime.add(i); 
		} 
		 
		int sum = 0, left = 0, right = 0; 
		 
		for(int i=0, len=prime.size(); i<len; i++) { 
			while(sum<N && right <len) { 
				sum += prime.get(right++); 
			} 
			if(sum == N) ans++; 
			sum -= prime.get(left++); 
		} 
		 
		 
		System.out.println(ans); 
	} 
 
}