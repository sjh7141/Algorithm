package gold2;
 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 
 
public class Main_2211_네트워크복구 { 
	static class Node implements Comparable<Node>{ 
		int num,cost,pre; 
 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return cost - o.cost; 
		} 
		 
	} 
	static int N,M; 
	static ArrayList<Node>[] net; 
	static ArrayList<int[]> ans; 
	static Node[] init; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tk.nextToken()); 
		M = Integer.parseInt(tk.nextToken()); 
		net = new ArrayList[N+1]; 
		ans = new ArrayList<>(); 
		for(int i=0; i<N+1; i++) { 
			net[i] = new ArrayList<>(); 
		} 
		 
		for(int i=0; i<M; i++) { 
			tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int len = Integer.parseInt(tk.nextToken()); 
			net[n1].add(new Node(n2, len)); 
			net[n2].add(new Node(n1, len)); 
		} 
		 
		init = dijk(); 
		System.out.println(ans.size()); 
		for(int[] temp : ans) { 
			System.out.println(temp[0] + " " + temp[1]); 
		} 
 
	} 
	private static Node[] dijk() { 
		Node[] arr = new Node[N+1]; 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		for(int i=1; i<N+1; i++) { 
			arr[i] = new Node(i, (i==1) ? 0 : Integer.MAX_VALUE); 
			pq.add(arr[i]); 
		} 
		 
		while(!pq.isEmpty()) { 
			Node cur = pq.poll(); 
			if(cur.cost == Integer.MAX_VALUE) break;
			if(cur.cost != 0) ans.add(new int[] {cur.pre, cur.num});
			for(int i = 0, size = net[cur.num].size(); i<size; i++) { 
				Node to = net[cur.num].get(i); 
				if(pq.contains(arr[to.num]) && cur.cost + to.cost < arr[to.num].cost) { 
					pq.remove(arr[to.num]); 
					arr[to.num].cost = cur.cost + to.cost;
					arr[to.num].pre = cur.num;
					pq.offer(arr[to.num]);
				}
			}
		}
		return arr;
	}
}