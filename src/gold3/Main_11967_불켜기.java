package gold3;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.StringTokenizer; 
 
public class Main_11967_불켜기 { 
	static class Pos{ 
		int r,c; 
 
		public Pos(int r, int c) { 
			this.r = r; 
			this.c = c; 
		} 
	} 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	static int N,M,ans; 
	static ArrayList<Pos>[][] map; 
	static ArrayList<Pos> candi; 
	static boolean[][] turnOn; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		map = new ArrayList[N+1][N+1]; 
		for(int i=1; i<N+1; i++) { 
			for(int j=1; j<N+1; j++) { 
				map[i][j] = new ArrayList<>(); 
			} 
		} 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			map[Integer.parseInt(tk.nextToken())][Integer.parseInt(tk.nextToken())].add(new Pos(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()))); 
		} 
		 
		turnOn = new boolean[N+1][N+1]; 
		turnOn[1][1] = true; 
		boolean flag = true; 
		 
		while(flag) { 
			flag = false; 
			//1. 이동가능한위치 찾아두기 
			bfs(); 
			//2. 가능한 위치에서 새로 불켜야하는곳 다 켜기 
			for(Pos cur : candi) { 
				for(Pos temp : map[cur.r][cur.c]) { 
					if(!turnOn[temp.r][temp.c]) { 
						turnOn[temp.r][temp.c] = true; 
						flag = true; 
					} 
				} 
			} 
		} 
		
		for(int i=1; i<N+1; i++) { 
			for(int j=1; j<N+1; j++) { 
				if(turnOn[i][j]) ans++; 
			} 
		} 
		System.out.println(ans);
		
//		//접근1 - 잘못된 접근, 실제로 갈 수 없어도 list에 집어넣는다
//		while(true) { 
//			ArrayList<Pos> backup = new ArrayList<>(); 
//			//1. 불 켤수있는 곳이 이동가능한 경우 다 켜기 
//			for(Pos cur : candi) { 
//				boolean flag = false; 
//				for(int k=0; k<4; k++) { 
//					int tr = cur.r + dir[k][0]; 
//					int tc = cur.c + dir[k][1]; 
//					if(tr<0 || tc<0 || tr>N || tc> N) continue; 
//					if(candi.contains(light[tr][tc])) { 
//						flag = true; 
//						break; 
//					} 
//				} 
//				if(cur.r==1 && cur.c==1) flag = true; 
//				if(flag) { 
//					for(Pos temp : map[cur.r][cur.c]) { 
//						Pos input = light[temp.r][temp.c]; 
//						if(!candi.contains(input) && !backup.contains(input)) backup.add(input); 
//					} 
//				} 
//			} 
//			if(backup.isEmpty()) break; 
//			for(Pos temp : backup) {
//				candi.add(temp);
//			}
	} 
	private static void bfs() { 
		boolean[][] visit = new boolean[N+1][N+1]; 
		candi = new ArrayList<>(); 
		ArrayDeque<Pos> deq = new ArrayDeque<>(); 
		 
		deq.add(new Pos(1,1)); 
		visit[1][1] = true; 
		candi.add(new Pos(1,1)); 
		 
		while(!deq.isEmpty()) { 
			int size = deq.size(); 
			for(int i=0; i<size; i++) { 
				Pos cur = deq.poll(); 
				for(int k=0; k<4; k++) { 
					int tr = cur.r + dir[k][0]; 
					int tc = cur.c + dir[k][1]; 
					if(tr<1 || tc<1 || tr>N || tc>N || !turnOn[tr][tc] || visit[tr][tc]) continue; 
					visit[tr][tc] = true; 
					deq.add(new Pos(tr,tc)); 
					candi.add(new Pos(tr,tc)); 
				} 
			} 
		} 
		 
		 
	} 
 
}