/*
ID: ...
LANG: JAVA
TASK: zerosum
*/
import java.io.*;
import java.util.*;

class zerosum {

  private static ArrayList<String> sols = new ArrayList<String>();


  private static boolean isSol(String sol) {
      int currentNumber = 1;
      int sum = 0;
      char saved = '+';
      for (int i=0;i<sol.length();i++) {
          switch (sol.charAt(i))  {
              case '+':
                  if (saved == '+') {
                      sum += currentNumber;
                  } else {
                      sum -= currentNumber;
                  }
                  saved = '+';
                  currentNumber = i+2;
                  break;
              case '-':
                  if (saved == '+') {
                      sum += currentNumber;
                  } else {
                      sum -= currentNumber;
                  }
                  currentNumber = i+2;
                  saved = '-';
                  break;
              case ' ': currentNumber = currentNumber * 10 + (i+2);
                    break;
          }
                  
      }
                if (saved == '+') {
                      sum += currentNumber;
                  } else {
                      sum -= currentNumber;
                  }      
      
      return (sum == 0);
  }
  
  private static void search(String sol, int n) {
      if (n == 0) {
          if (isSol(sol)){
              sols.add(sol);
          }
      } else {
          String sol1 = sol + '+';
          search( sol1, n -1);
          sol1 = sol + '-';
          search( sol1, n -1);
          sol1 = sol + ' ';
          search( sol1, n -1);
      }
  }
  
  public static void main (String [] args) throws IOException {



    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());

    int N = Integer.parseInt(st.nextToken());

    search("", N-1);
    
    Collections.sort(sols);
    

    for (int i=0; i<=sols.size()-1;i++) {
        for (int j=1;j<N;j++){
            out.print(j);
            out.print(sols.get(i).charAt(j-1));
        }
        out.println(N);
    }

    

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}