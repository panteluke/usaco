/*
ID: panteli1
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {
  private char[][] beforeImage = new char[10][10];
  private char[][] afterImage = new char[10][10];

  private int size = 0;

  private boolean is90() {
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){
            if ((beforeImage[size - j - 1][i]) != (afterImage[i][j])) {
                return false;
            }
        }
    }
    return true;
  }

  private boolean is180() {
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){
            if ((beforeImage[size - i - 1][size - 1 - j]) != (afterImage[i][j])) {
                return false;
            }
        }
    }

    return true;
  }

  private boolean is270() {
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){
            if ((beforeImage[j][size - 1 - i]) != (afterImage[i][j])) {
                return false;
            }
        }
    }

    return true;
  }

  private boolean isReflection() {
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){
            if ((beforeImage[i][size - 1 - j]) != (afterImage[i][j])) {
                return false;
            }
        }
    }

    return true;
  }

  private boolean isSame(){
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){
            if ((beforeImage[i][j]) != (afterImage[i][j])) {
                return false;
            }
        }
    }

    return true;

  }

  private boolean isCombination(){
    boolean comb1 = true;
    boolean comb2 = true;
    boolean comb3 = true;
    is1:
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){

            int trX=  j;
            int trY = i;

            if ((beforeImage[trX][trY]) != (afterImage[i][j])) {
                comb1 = false;
                break is1;
            }
        }
    }

    is2:
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){
            
            
            int trX=  size - i - 1;
            int trY = j;


            if ((beforeImage[trX][trY]) != (afterImage[i][j])) {
                comb2 = false;
                break is2;
            }
        }
    }

    is3:
    for (int i=0;i<size;i++) {
        for (int j=0;j<size;j++){
            
            int trX=  size - 1 - j;
            int trY = size - 1 - i;


            if ((beforeImage[trX][trY]) != (afterImage[i][j])) {
                comb3 = false;
                break is3;
            }
        }
    }

    return comb1 || comb2 || comb3;

  }

  private int GetResemblance(){
      if (is90()) return 1;
      if (is180()) return 2;
      if (is270()) return 3;
      if (isReflection()) return 4;
      if (isCombination()) return 5;
      if (isSame()) return 6;

      return 7;
  }
  
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("transform.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
    
    transform tr = new transform();
    tr.size = Integer.parseInt(st.nextToken());

    for (int i =0;i<tr.size;i++) {
        st = new StringTokenizer(f.readLine());
        String row = st.nextToken();
        for (int j=0;j<tr.size;j++){
            tr.beforeImage[i][j] = row.charAt(j);
        }
    }

    for (int i =0;i<tr.size;i++) {
        st = new StringTokenizer(f.readLine());
        String row = st.nextToken();
        for (int j=0;j<tr.size;j++){
            tr.afterImage[i][j] = row.charAt(j);
        }
    }

    int i = tr.GetResemblance();

    // Get line, break into tokens
    out.println(i);                           // output result
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}