/*
ID: ...
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class ariprog {

 
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    int n = Integer.parseInt(f.readLine());
    int m = Integer.parseInt(f.readLine());

    n= n-1;

    Map<Integer, TreeSet<Integer>> solutions = new TreeMap<Integer, TreeSet<Integer>>();
    boolean[] bis = new boolean[125001];
    Arrays.fill(bis, false);
    for (int i=0;i<m + 1;i++) {
        for (int j=0;j<m + 1;j++)
            bis[i*i + j*j] = true;
    }

    int maxm = m * m + m * m;
    
    while (maxm > 0) {
        int b = maxm / n;
        

        for (int k = b; k> 0; k--) {
            int a = maxm  - k * n;
            boolean toP = true;
            for (int j = n -1; j >=0;j--) {
                if (!bis[a + j*k]) {
                    toP = false;
                    break;
                }
            }
            if (toP) {
                TreeSet<Integer> l = solutions.get(k);
                if (l == null) {
                    l = new TreeSet<Integer>();
                    l.add(a);
                    solutions.put(k, l);
                }
                else {
                    l.add(a);
                }
            }
        }

        while (!bis[--maxm]);
    }

    if (solutions.isEmpty()) {
        out.println("NONE");
    }
    else {
        Iterator it = solutions.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry<Integer, TreeSet<Integer>> pairs = (Map.Entry<Integer, TreeSet<Integer>>)it.next();
            TreeSet<Integer> ll = pairs.getValue();
            for (Integer lll : ll) {
                out.println( lll + " " + pairs.getKey());
            }
        }

    }
    
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}

