package silver4;

import java.util.Scanner;
import java.util.Stack;

public class Main_10773_제로 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int ans = 0;
		Stack st = new Stack<Integer>();
		for(int i=0; i<k; i++) {
			int n = sc.nextInt();
			if(n==0) st.pop();
			else st.push(n);
		}
		while(!st.isEmpty()) {
			ans+=(int)st.pop();
		}
		System.out.println(ans);
	}
}
