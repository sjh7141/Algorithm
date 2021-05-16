package gold3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2263_트리의순회 {
	static StringBuilder sb= new StringBuilder();
	static int[] pre,in,post,pos;
	static int N;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pre = new int[N];
		in = new int[N];
		post = new int[N];
		pos = new int[N+1];
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			int cur = Integer.parseInt(tk.nextToken()); 
			in[i] = cur;
			pos[cur] = i;
		}
		tk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			post[i] = Integer.parseInt(tk.nextToken());
		}
		solve(0,N-1,0,N-1);
		System.out.println(sb.toString());
	}
	private static void solve(int s1, int e1, int s2, int e2) {
		if(s1>e1) {
			return;
		}
		int w = pos[post[e1]];
		sb.append(post[e1]+" ");
		solve(s1,s1+(w-1-s2),s2,w-1);
		solve(e1-1-(e2-w-1),e1-1,w+1,e2);
		
		
	}

}