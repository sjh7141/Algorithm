package gold4;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.StringTokenizer; 
 
public class Main_3980_선발명단 { 
	static int C, ans; 
	static boolean[] used; 
	static ArrayList<int[]>[] stat; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		C = Integer.parseInt(br.readLine()); 
		for(int t=0; t<C; t++) { 
			ans = Integer.MIN_VALUE; 
			used = new boolean[11]; 
			stat = new ArrayList[11]; 
			for(int i=0; i<11; i++) { 
				stat[i] = new ArrayList<>(); 
			} 
			for(int i=0; i<11; i++) { 
				StringTokenizer tk = new StringTokenizer(br.readLine()); 
				for(int j=0; j<11; j++) { 
					int num = Integer.parseInt(tk.nextToken()); 
					if(num == 0) continue; 
					//첫번째 원소 선수번호, 두번째 원소 점수 
					stat[i].add(new int[] {j, num}); 
				} 
			} 
			dfs(0,0);			 
			System.out.println(ans); 
		} 
		 
 
	} 
	private static void dfs(int num, int sum) { 
		if(sum+100*(11-num) <= ans) return; 
		if(num == 11) { 
			ans = Math.max(ans, sum); 
			return; 
		} 
		 
		for(int i=0, size=stat[num].size(); i<size; i++) { 
			int[] cur = stat[num].get(i); 
			if(used[cur[0]]) continue; 
			used[cur[0]] = true; 
			dfs(num+1, sum+cur[1]); 
			used[cur[0]] = false; 
		} 
		 
	} 
 
}