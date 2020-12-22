package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_3190_뱀 {
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static int N,K,L;
	static int[][] map;
	static ArrayDeque<int[]> dirInfo, snake;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int i=0; i<K; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(tk.nextToken())][Integer.parseInt(tk.nextToken())] = 2;
		}
		L = Integer.parseInt(br.readLine());
		dirInfo = new ArrayDeque<int[]>();
		snake = new ArrayDeque<int[]>();
		for(int i=0; i<L; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(tk.nextToken());
			char d = tk.nextToken().charAt(0);
			temp[1] = (d == 'L') ? -1 : 1;
			dirInfo.add(temp);
		}
		
		int ans = 1, d = 0;
		snake.add(new int[] {1,1});
		map[1][1] = 1;
		while(true) {
			int[] head = snake.peek();
			int r = head[0] + dir[d][0];
			int c = head[1] + dir[d][1];
			if(r<1 || r>N || c<1 || c>N || map[r][c] == 1) break;
			if(map[r][c] != 2) {
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = 0;
			}
			snake.offerFirst(new int[] {r,c});
			map[r][c] = 1;
			if(!dirInfo.isEmpty() && ans == dirInfo.peek()[0]) {
				int[] change = dirInfo.poll();
				d = (d + change[1] + 4) % 4;
			}
			ans++;
		}
		
		System.out.println(ans);

	}

}
