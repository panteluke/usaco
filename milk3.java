/*
ID: ...
LANG: JAVA
PROG: milk3
*/
import java.io.*;
import java.util.*;
class milk3 {
 private  int[] cap = new int[3];
  private int[][] savedStates = new int[8000][3];
  private int counter;
  private class triple {
      public int[] cap = new int[3];
      public triple(int a, int b, int c) {
          this.cap[0] =a; this.cap[1]= b; this.cap[2] = c;
      }
  }
  private boolean containsA(triple x) {
      for (int i=0;i<counter;i++){
          if ((savedStates[i][0] == x.cap[0]) && (savedStates[i][1] == x.cap[1]) && (savedStates[i][2] == x.cap[2])){
              return true;
          }
      }
      return false;
  }
  public triple pour (int colSrc, int colDest ,triple x){
      triple newx =   new triple(x.cap[0],x.cap[1],x.cap[2]);
      if ((x.cap[colDest] != cap[colDest]) && (x.cap[colSrc] != 0)) {
          int t = 0;
          int diff = cap[colDest] - x.cap[colDest];
          if (diff >= x.cap[colSrc]) {
              t = x.cap[colSrc];
          }
           else {
              t= diff;
           }
          newx.cap[colSrc] = newx.cap[colSrc] - t;
          newx.cap[colDest] = newx.cap[colDest] + t;
      }
      return newx;
  }
  public void search(triple x) {
    if (!containsA(x)) {
        savedStates[counter][0] = x.cap[0];
        savedStates[counter][1] = x.cap[1];
        savedStates[counter++][2] = x.cap[2];
        triple x1 = pour(0,1, x);
        search(x1);
        x1= pour(0,2,x);
        search(x1);
        x1= pour(1,2,x);
        search(x1);
        x1= pour(1,0,x);
        search(x1);
        x1= pour(2,1,x);
        search(x1);
        x1=pour(2,0,x);
        search(x1);
    }
  }
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    milk3 mk = new milk3();
    mk.counter = 0;
    mk.cap[0] = a;
    mk.cap[1] = b;
    mk.cap[2] = c;
    triple t =  mk.new triple(0,0,c);
    mk.search(t);
  
    SortedSet<Integer> possible = new TreeSet<Integer>();
    for (int i=0;i<mk.counter;i++){
        if (mk.savedStates[i][0] == 0) {
            possible.add(mk.savedStates[i][2]);
        }
    }
    Iterator<Integer> iterator = possible.iterator();
    while (iterator.hasNext()) {
        out.print(iterator.next());
        if (iterator.hasNext()) {
            out.print(' ');
        }
    }
    out.println();
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}
