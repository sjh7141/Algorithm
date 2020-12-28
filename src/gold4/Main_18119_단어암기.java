package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18119_단어암기 {
	static int N, M;
	static int words[];
	static int memory = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		words = new int[N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			int cur = 0;
			for(int j=0; j<str.length(); j++) {
				 cur |= 1 << (str.charAt(j) - 'a');
			}
			words[i] = cur;
		}
		
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			boolean flag = tk.nextToken().equals("1") ? true : false;
			int cur = tk.nextToken().charAt(0) - 'a';
			if(flag) {
				memory &= ~(1 << cur);
			}else {
				memory |= 1 << cur;
			}
			int cnt = 0;
			for(int j=0; j<N; j++) {
				if((words[j] & memory) == words[j]) cnt++;
			}
			System.out.println(cnt);
		}
		
	}

}
