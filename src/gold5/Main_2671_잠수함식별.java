package gold5;

import java.util.Scanner;

public class Main_2671_잠수함식별 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		boolean flag = solve(str);
		System.out.println(flag ? "SUBMARINE" : "NOISE");
	}

	private static boolean solve(String str) {
		int s = 0;
		int e = str.length();
		while(s < e) {
			if(str.charAt(s) == '0') {
				if(s+1<=e && str.charAt(s+1) == '1' ) {
					s+=2;
				}else {
					return false;
				}
			}else {
				if(s+3<=e && str.charAt(s+1) == '0' && str.charAt(s+2) == '0') {
					while(s<e && str.charAt(s) == '0') {
						s++;
					}
					if(s>=e) return false;
					while(s<e && str.charAt(s) == '1') {
						s++;
					}
				}else {
					return false;
				}
			}
			
		}
		return true;
	}

}
