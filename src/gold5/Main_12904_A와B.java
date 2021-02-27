package gold5;
import java.util.Scanner;

public class Main_12904_A와B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder S = new StringBuilder(sc.next());
		StringBuilder T = new StringBuilder(sc.next());
		
		for(int k=T.length()-1; k>T.length()-1-(T.length()-S.length()); k--) {
			if(T.charAt(k) == 'A') {
				T.deleteCharAt(k);
			}else {
				T.deleteCharAt(k);
				T.reverse();
			}
		}
		if(S.toString().equals(T.toString())) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		

	}

}