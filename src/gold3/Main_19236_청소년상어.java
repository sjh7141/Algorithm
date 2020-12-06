package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19236_청소년상어 {
   static class Fish implements Comparable<Fish>{
      int r,c,a,b;

      public Fish(int r, int c, int a, int b) {
         this.r = r;
         this.c = c;
         this.a = a;
         this.b = b;
      }

      @Override
      public int compareTo(Fish o) {
         return this.a - o.a;
      }
      
   }
   static int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
   static int max;
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      Fish shark = new Fish(0,0,0,0);
      Fish[][] map = new Fish[4][4];
      for(int i=0; i<4; i++) {
         StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
         for(int j=0; j<4; j++) {
        	Fish fish = new Fish(i,j,Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()) -1);
        	if(i==0 && j==0) {
        		max = fish.a;
        		shark.b = fish.b;
        		fish = shark;
        	}
            map[i][j] = fish;
         }
      }
      solve(map, max);
      System.out.println(max);
   }
private static void solve(Fish[][] pre_map, int sum) {
    PriorityQueue<Fish> pq = new PriorityQueue<>();
    Fish[][] map = new Fish[4][4];
    Fish shark = null;
    
    for(int i=0; i<4; i++) {
    	for(int j=0; j<4; j++) {
    		Fish fish = pre_map[i][j];
    		map[i][j] = new Fish(fish.r, fish.c, fish.a, fish.b);
    		if(fish.a > 0) {
    			pq.add(map[i][j]);
    		}else if(fish.a == 0) {
    			shark = fish;
    		}
    	}
    }
    while(!pq.isEmpty()) {
        Fish temp = pq.poll();
        for(int k=0; k<8; k++) {
        	int cur_d = (temp.b+k)%8;
        	int tr = temp.r + dir[cur_d][0];
        	int tc = temp.c + dir[cur_d][1];
        	if(tr<0||tr>3||tc<0||tc>3||map[tr][tc].a == 0) continue;
        	temp.b = cur_d;
        	map[tr][tc].r = temp.r;
        	map[tr][tc].c = temp.c;
        	map[temp.r][temp.c] = map[tr][tc];
        	temp.r = tr;
        	temp.c = tc;
        	map[tr][tc] = temp;
        	break;
        }
    }
    boolean starve = true;
    int iter = 0;
    while(true) {
    	int tr = shark.r + dir[shark.b][0] * ++iter;
    	int tc = shark.c + dir[shark.b][1] * iter;
    	if(tr<0||tr>3||tc<0||tc>3) break;
    	if(map[tr][tc].a < 0) continue;
    	starve = false;
    	Fish eat = map[tr][tc];
    	Fish t_shark = new Fish(tr,tc,0,eat.b);
    	map[tr][tc] = t_shark;
    	map[shark.r][shark.c].a = -1;
    	solve(map, sum+eat.a);
    	map[tr][tc] = eat;
    	map[shark.r][shark.c].a = 0;
    }
    if(starve) {
    	max = Integer.max(max, sum);
    }
	
}

}