package silver4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main_1347_미로만들기 {
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split("");
		boolean[][] map = new boolean[101][101];
		int curR ,curC, minR, maxR, minC, maxC;
		curR = curC = minR = maxR = minC = maxC = 51;
		map[curR][curC] = true;
		int k = 2;
		for(int i=0; i<N; i++) {
			if(arr[i].equals("R")) {
				k = (k+1)%4;
			}else if(arr[i].equals("L")) {
				k = (k+3)%4;
			}else {
				curR += dir[k][0];
				curC += dir[k][1];
				if(curR < minR) minR = curR;
				if(curR > maxR) maxR = curR;
				if(curC < minC) minC = curC;
				if(curC > maxC) maxC = curC;
				map[curR][curC] = true;
			}
		}
		for(int i=minR; i<maxR+1; i++) {
			for(int j=minC; j<maxC+1; j++) {
				System.out.print(map[i][j]? '.' : '#');
			}
			System.out.println();
		}

		
	}

}
