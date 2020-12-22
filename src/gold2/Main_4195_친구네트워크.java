package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_4195_친구네트워크 {
	static ArrayList<Integer>[] list;
	static int N;
	static int[] parent;
	static int[] num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			list = new ArrayList[N*2];
			for(int i=0; i<N*2; i++) {
				list[i] = new ArrayList<>();
			}
			
			HashMap<String, Integer> map = new HashMap<>();
			int idx = 0;
			
			parent = new int[N*2];
			for(int i=0; i<N*2; i++) {
				parent[i] = i;
			}
			num = new int[N*2];
			Arrays.fill(num, 1);
			
			for(int i=0; i<N; i++) {
				StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
				String id1 = tk.nextToken();
				int idx1 = (map.containsKey(id1)? map.get(id1) : -1);
				String id2 = tk.nextToken();
				int idx2 = (map.containsKey(id2)? map.get(id2) : -1);
				if(idx1 == -1) {
					idx1 = idx++;
					map.put(id1, idx1);
				}
				if(idx2 == -1) {
					idx2 = idx++;
					map.put(id2, idx2);
				}
				list[idx1].add(idx2);
				list[idx2].add(idx1);
				
				if(find(idx1) != find(idx2)) {
					num[find(idx2)] += num[find(idx1)];
					parent[find(idx1)] = find(idx2);
				}
				System.out.println(num[find(idx2)]);
				
			}
		}
	}
	private static int find(int p) {
		if(p == parent[p]) {
			return p;
		}
		parent[p] = find(parent[p]);
		return parent[p];
	}

}