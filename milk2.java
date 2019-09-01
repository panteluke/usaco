/*
ID: ...
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    //BufferedReader f = new BufferedReader(new FileReader("J:\\problems\\UsacoJava\\src\\milk2.in"));
      BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
                                                  // input file name goes above
    //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("J:\\problems\\UsacoJava\\src\\milk2.out")));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
						  // Get line, break into tokens
    int n = Integer.parseInt(st.nextToken());    // first integer

    boolean[] time = new boolean[2000001];
    int maxX2 = 0;
    for (int i = 1; i <= n; i++){
        st = new StringTokenizer(f.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());

        for (int j = x1; j <=x2; j++) {
            time[j * 2] = true;
            if (j!=x2) {
              time[j * 2 + 1] = true;
            }
        }
        

        if (x2 * 2 > maxX2) maxX2 = x2 * 2;


    }

    
    int maxMilk = 0;
    int maxNoMilk = 0;
    int tmpMilk = 0;
    int tmpNoMilk = 0;
    boolean hasStartMilk = false;

    for (int i = 0; i <= maxX2+1; i++) {
        if (time[i]) {
            tmpMilk++;
            hasStartMilk = true;
            if (tmpNoMilk > maxNoMilk) maxNoMilk = tmpNoMilk;
            tmpNoMilk = 0;
        }
        else {
            if (hasStartMilk){
                tmpNoMilk++;
            }
            if (tmpMilk > maxMilk) {maxMilk = tmpMilk;
                //System.out.println(maxMilk);
            }
            tmpMilk = 0;
        }
    }
    
    maxMilk = maxMilk / 2;
    if (maxNoMilk != 0) {
        maxNoMilk = maxNoMilk / 2 + 1;
    }
    
    
    out.println(maxMilk + " " + maxNoMilk);                           // output result
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}
