package gold4;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main_10282_해킹 { 
	static class Node implements Comparable<Node>{ 
		int num,cost; 
		 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
	} 
	static int n,d,c; 
	static ArrayList<Node>[] map; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int T = Integer.parseInt(br.readLine()); 
		for(int t=0; t<T; t++){ 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			n = Integer.parseInt(tk.nextToken()); 
			d = Integer.parseInt(tk.nextToken()); 
			c = Integer.parseInt(tk.nextToken()); 
			map = new ArrayList[n+1]; 
			 
			for(int i=0; i<n+1; i++) { 
				map[i] = new ArrayList<>(); 
			} 
			 
			for(int i=0; i<d; i++) { 
				tk = new StringTokenizer(br.readLine()); 
				int a = Integer.parseInt(tk.nextToken()); 
				int b = Integer.parseInt(tk.nextToken()); 
				int s = Integer.parseInt(tk.nextToken()); 
				map[b].add(new Node(a, s)); 
			} 
			 
			int[] ans = dijk(); 
			System.out.println(ans[0] + " " + ans[1]); 
		} 
		 
 
	} 
	private static int[] dijk() { 
		Node[] arr = new Node[n+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<n+1; i++) { 
			arr[i] = new Node(i, (i==c) ? 0 : Integer.MAX_VALUE); 
			pq.offer(arr[i]); 
		} 
		int min = 0; 
		int cnt = 0; 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Integer.MAX_VALUE) break; 
			else { 
				min = cur.cost; 
				cnt++; 
			} 
			 
			for(int i=0, size=map[cur.num].size(); i<size; i++) { 
				Node plus = map[cur.num].get(i); 
				if(pq.contains(arr[plus.num]) && arr[plus.num].cost > cur.cost + plus.cost) { 
					pq.remove(arr[plus.num]);
					arr[plus.num].cost = cur.cost + plus.cost;
					pq.add(arr[plus.num]);
				}
			}
		}
		return new int[] {cnt,min};
	}
}