package gold3;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Stack; 
import java.util.StringTokenizer; 
 
public class Main_2611_자동차경주 { 
	static class Node implements Comparable<Node>{ 
		int num, cost; 
		 
		public Node(int num, int cost) { 
			this.num = num; 
			this.cost = cost; 
		} 
 
		@Override 
		public int compareTo(Node o) { 
			return o.cost - cost; 
		} 
		 
	}
	//위상정렬
	//처음엔 다익스트라로 접근하였지만 자기자신까지 돌아와야하기에 패스 
	//1번을 거치지않고선 같은 지점으로 돌아올 수 없기 때문에 중간 정점은 1번만 방문이 가능하며 위상정렬이 사용가능 
	static int N,M; 
	static ArrayList<Node>[] map; 
	static int[] max; 
	static int[] degree; 
	static int[] pre; 
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		M = Integer.parseInt(br.readLine()); 
		map = new ArrayList[N+1]; 
		for(int i=0; i<N+1; i++) { 
			map[i] = new ArrayList<>(); 
		} 
		max = new int[N+1]; 
		degree = new int[N+1]; 
		pre = new int[N+1]; 
		for(int i=0; i<M; i++) { 
			StringTokenizer tk = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(tk.nextToken()); 
			int n2 = Integer.parseInt(tk.nextToken()); 
			int l = Integer.parseInt(tk.nextToken()); 
			map[n1].add(new Node(n2,l)); 
			degree[n2]++; 
		} 
		 
		topologySort(); 
		Stack<Integer> order = makeOrder(); 
		System.out.println(max[1]); 
		while(!order.isEmpty()){ 
			System.out.print(order.pop() + " "); 
		} 
		 
		 
		 
	} 
	private static Stack<Integer> makeOrder() { 
		Stack<Integer> stack = new Stack<>(); 
		int num = pre[1]; 
		stack.add(1); 
		stack.add(num); 
		while(num != 1) { 
			stack.add(pre[num]); 
			num = pre[num]; 
		} 
	 
		return stack; 
	} 
	private static void topologySort() { 
		ArrayDeque<Integer> deq = new ArrayDeque<>(); 
		deq.add(1); 
		 
		while(!deq.isEmpty()) { 
			int cur = deq.poll(); 
			if(cur == 1 && degree[1] == 0) break; 
			for(Node next : map[cur]) { 
				degree[next.num]--; 
				if(max[next.num] < max[cur] + next.cost) { 
					max[next.num] = max[cur] + next.cost; 
					pre[next.num] = cur; 
				} 
				if(degree[next.num] == 0) { 
					deq.add(next.num); 
				} 
			} 
		} 
		 
	} 
 
 
}