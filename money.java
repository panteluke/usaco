/*
ID: panteli1
LANG: JAVA
TASK: money
*/
import java.io.*;
import java.util.*;

class money {

    
    
    
  private static long[][] www = new long[10001][25];
  
 
  public static long getWays(List<Integer> coins, int element, int remain) {
      
      if (remain == 0)
          return 1;

      long result = 0;
      
      for (int j = element; j >=0 ; j -- ) {
          if (coins.get(j) <= remain) {
              if (www[remain][j] == -1)
                  www[remain][j] = getWays(coins, j, remain - coins.get(j)); 
              result = result + www[remain][j];
          
              
          }
      }
      
      return result;
  }  
    
  public static void main (String [] args) throws IOException {



    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("money.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());

    int V = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    
    List<Integer> coins = new ArrayList<Integer>();
    
    for (int i = 0 ; i<=10000;i++) {
        for (int j=0;j<25;j++) {
            www[i][j] = -1;
        }
    }
    
    while (V > 0) {
        st = new StringTokenizer(f.readLine());
        while (st.hasMoreTokens()) {
            /* the solution says
            // nway[0] = 1;
	        
		for(j=(Integer.parseInt(st.nextToken()); j<=n; j++)
	          nway[j] += nway[j-c];
         	fprintf(fout, "%lld\n", nway[n]);
             * 
             */
            
            coins.add(Integer.parseInt(st.nextToken()));
            V--;
        }
    }
    
    
    Collections.sort(coins);
    
    long ways = getWays(coins, coins.size() - 1, N);
    out.println(ways);

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}