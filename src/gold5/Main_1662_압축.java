package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1662_압축 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<String> stack = new Stack<String>();
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == ')') {
				int cnt = 0;
				while(!stack.peek().equals("(")){
					String cur = stack.pop();
					if(cur.equals("*")) {
						cnt += Integer.parseInt(stack.pop());
					}else {
						cnt++;
					}
				}
				stack.pop();
				
				int K = Integer.parseInt(stack.pop());
				cnt *= K;
				
				stack.push(Integer.toString(cnt));
				stack.push("*");
			}else {
				stack.add(str.substring(i, i+1));
			}
		}
		
		
		int ans = 0;
		while(!stack.isEmpty()){
			String cur = stack.pop();
			if(cur.equals("*")) {
				ans += Integer.parseInt(stack.pop());
			}else {
				ans++;
			}
		}
		System.out.println(ans);

	}

}
