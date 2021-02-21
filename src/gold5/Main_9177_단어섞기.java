package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_9177_단어섞기 {
	static int N;
	static String str1, str2, str3;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i=1; i<N+1; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			str1 = tk.nextToken();
			str2 = tk.nextToken();
			str3 = tk.nextToken();
			visit = new boolean[str1.length()+1][str2.length()+1];
			System.out.println("Data set "+i+": "+((solve())?"yes":"no"));
		}
		

	}
	private static boolean solve() {
		ArrayDeque<int[]> deq = new ArrayDeque<>();
		visit[0][0] = true;
		deq.add(new int[] {0,0,0});
		OUT:
		while(!deq.isEmpty()) {
			int size = deq.size();
			for(int i=0; i<size; i++) {
				int[] temp = deq.poll();
				if(temp[2] == str3.length()) return true;
				if(temp[0] < str1.length() && !visit[temp[0]+1][temp[1]] && str1.charAt(temp[0]) == str3.charAt(temp[2])) {
					visit[temp[0]+1][temp[1]] = true;
					deq.add(new int[] {temp[0]+1,temp[1],temp[2]+1});
				}
				if(temp[1] < str2.length() && !visit[temp[0]][temp[1]+1] && str2.charAt(temp[1]) == str3.charAt(temp[2])) {
					visit[temp[0]][temp[1]+1] = true;
					deq.add(new int[] {temp[0],temp[1]+1,temp[2]+1});
				}
			}
		}
		return false;
	}

}