/*
ID: ...
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;


public class concom {
    
    
 public static void main (String [] args) throws IOException {



    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("concom.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());


    int N = Integer.parseInt(st.nextToken());
   
    int[][] companies = new int[101][101];
    
    for (int i =0;i<N;i++) {
        st = new StringTokenizer(f.readLine());
        int c1 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        companies[c1][c2] = p;
        if (p > 50) {
            for (int j = 1; j <=100;j++) {
                if ((j == c1) || (j == c2)) continue;
                companies[c1][j] = companies[c1][j] + companies[c2][j];
            }
        }
        for (int j = 1; j <=100;j++) {
            if (companies[j][c1] > 50) {
              if (p <= 50) {
                companies[j][c2] = companies[j][c2] + p ;
              }
              else {
                for (int k = 1; k <=100;k++) {
                    if ((k == c1) || (j == k)) continue;
                    companies[j][k] = companies[j][k] + companies[c1][k];
                }                  
              }
            }
        }
        
    }
    

    for (int i = 1; i<= 100; i++){
        for (int j = 1; j<= 100; j++){
            if (i == j) continue;
            if (companies[i][j] > 50) {
                out.println(i + " " + j);
            }
        }
    }

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }    
    
}
