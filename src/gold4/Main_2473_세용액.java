package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473_세용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Long[] num = new Long[N];
		StringTokenizer tk = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Long.parseLong(tk.nextToken()); 
		}
		Arrays.sort(num);
		long ans = Long.MAX_VALUE;
		ArrayList<Long> list = new ArrayList<Long>();
		for(int i=0; i<N; i++) {
			int l = i+1;
			int r = N-1;
			while(l<r) {
				long sum = num[i] + num[l] + num[r];
				if(ans > Math.abs(sum)) {
					list.clear();
					list.add(num[i]);
					list.add(num[l]);
					list.add(num[r]);
					ans = Math.abs(sum);
				}
				if(sum < 0) l++;
				else r--;
			}
			if(ans==0) break;
			
		}
		for(long i : list) {
			System.out.print(i + " ");
		}

	}

}
