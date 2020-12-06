package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19236_청소년상어 {
   static int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
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
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PriorityQueue<Fish> pq = new PriorityQueue<>();
      
      int r = 0, c = 0;
      for(int i=0; i<4; i++) {
         StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
         for(int j=0; j<4; j++) {
            pq.add(new Fish(r,c,Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()) -1));
            c++;
         }
         r++;
         c = 0;
      }
      
      while(!pq.isEmpty()) {
         Fish temp = pq.poll();
         System.out.println(temp.a);
         System.out.println(temp.r + " " + temp.c);
      }
   }

}