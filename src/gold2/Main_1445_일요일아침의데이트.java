package gold2;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 //다익스트라, 2차원, 우선순위 2가지
public class Main_1445_일요일아침의데이트 { 
	static class Node implements Comparable<Node>{ 
		int r, c, mainCost, subCost; 
 
		public Node(int r, int c, int mainCost, int subCost) { 
			this.r = r; 
			this.c = c; 
			this.mainCost = mainCost; 
			this.subCost = subCost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			if(mainCost == o.mainCost) { 
				return subCost - o.subCost; 
			} 
			return mainCost - o.mainCost; 
		}		
		 
	} 
	static int N,M,sr,sc,er,ec; 
	static char[][] map; 
	static ArrayList<Node>[][] list; 
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		map = new char[N][M]; 
		list = new ArrayList[N][M]; 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) { 
				list[i][j] = new ArrayList<>(); 
			} 
		} 
		 
		for(int i=0; i<N; i++) { 
			String str = br.readLine();
			for(int j=0; j<M; j++) { 
				map[i][j] = str.charAt(j); 
				if(map[i][j] == 'S') { 
					sr = i; 
					sc = j; 
				} 
				if(map[i][j] == 'F') { 
					er = i; 
					ec = j; 
				} 
			} 
		} 
		 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) { 
				for(int k=0; k<4; k++) { 
					int r = i + dir[k][0]; 
					int c = j + dir[k][1]; 
					if(r < 0 || c < 0 || r > N-1 || c > M-1) continue;
					if(map[r][c] == 'g') { 
						list[i][j].add(new Node(r,c,1,0)); 
					}else { 
						boolean flag = true; 
						for(int l=0; l<4; l++) { 
							int tr = r + dir[l][0]; 
							int tc = c + dir[l][1]; 
							if(tr < 0 || tc < 0 || tr> N-1 || tc > M-1) continue;
							if(map[tr][tc] == 'g') flag = false; 
						} 
						if(flag || r == er && c == ec) { 
							list[i][j].add(new Node(r,c,0,0)); 
						}else {
							list[i][j].add(new Node(r,c,0,1)); 
						}
					}
				}
			} 
		} 
		 
		Node ans = dijk(); 
		System.out.println(ans.mainCost + " " + ans.subCost); 
	} 
	private static Node dijk() { 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		Node[][] dist = new Node[N][M]; 
		 
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) { 
				dist[i][j] = new Node(i,j,Integer.MAX_VALUE,Integer.MAX_VALUE); 
				if(i == sr && j == sc) dist[i][j] = new Node(i,j,0,0); 
				pq.add(dist[i][j]); 
			} 
		} 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll();
			for(Node temp : list[cur.r][cur.c]) { 
				if(pq.contains(dist[temp.r][temp.c]) && compare(dist[temp.r][temp.c], cur, temp)) { 
					pq.remove(dist[temp.r][temp.c]); 
					dist[temp.r][temp.c].mainCost = cur.mainCost + temp.mainCost; 
					dist[temp.r][temp.c].subCost = cur.subCost + temp.subCost; 
					pq.add(dist[temp.r][temp.c]); 
				} 
			} 
		} 
		return dist[er][ec]; 
	} 
	 
	private static boolean compare(Node target, Node cur, Node weight) { 
		if(target.mainCost == cur.mainCost + weight.mainCost) { 
			if(target.subCost > cur.subCost + weight.subCost) { 
				return true; 
			}else { 
				return false; 
			} 
		}else if(target.mainCost > cur.mainCost + weight.mainCost) { 
			return true; 
		}else { 
			return false; 
		} 
		 
	} 
 
}