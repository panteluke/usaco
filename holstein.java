/*
ID: ...
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

class holstein {

    public static int n;
    public static int[] doses = new int[25];
    public static int[] copydoses = new int[25];
    public static int feeds;
    public static int[][] crows = new int[15][25];
    public static ArrayList<Integer> order = new ArrayList<Integer>();

    public static void search(int nn, ArrayList<Integer> xorder) {
        if (nn == 0) {
            for (int i=0;i<n;i++){
                doses[i] = copydoses[i];
            }

            for (Integer xx : xorder) {
                for (int i=0;i<n;i++) {
                    doses[i] -= crows[xx][i];
                    if (doses[i] < 0) doses[i] = 0;
                }
            }
            boolean finished =  true;
            for (int i=0;i<n;i++) {
                finished = (finished) && (doses[i] == 0);
            }

            if (finished) {
                order = xorder;
            } else
            {
                order = null;
            }

        } else {
            int k = -1;
            if (!xorder.isEmpty()) {
                k = xorder.get(xorder.size() - 1);
            }

            for (int i=k+1;i<feeds;i++) {
                xorder.add(i);
                int kl = nn -1;
                search(kl,xorder);
                if (order != null) break;
                xorder.remove(xorder.size() - 1);
            }
        }
    }

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        n = Integer.parseInt(st.nextToken());    // first integer;

        st = new StringTokenizer(f.readLine());

        for (int i=0;i<n;i++){
            doses[i] = Integer.parseInt(st.nextToken());
            copydoses[i] = doses[i];
        }
        st = new StringTokenizer(f.readLine());

        feeds = Integer.parseInt(st.nextToken());
        
        for (int i=0;i<feeds;i++){
            st = new StringTokenizer(f.readLine());
            for (int j=0;j<n;j++) {
                crows[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        ArrayList<Integer> order2 = new ArrayList<Integer>();
        for (int i=1;i<=feeds;i++){
            search(i, order2);
            if (order != null) break;
        }

/*
        ArrayList<ArrayList<Integer>> stack = new ArrayList<ArrayList<Integer>>();



        stack.add(order);
        while (!stack.isEmpty()) {
            order = stack.remove(0);

            for (int i=0;i<n;i++){
                doses[i] = copydoses[i];
            }

            for (Integer xx : order) {
                for (int i=0;i<n;i++) {
                    doses[i] -= crows[xx][i];
                    if (doses[i] < 0) doses[i] = 0;
                }
            }
            boolean finished =  true;
            for (int i=0;i<n;i++) {
                finished = (finished) && (doses[i] == 0);
            }

            while (!finished) {
                int min = Integer.MAX_VALUE;
                int k =0;
                for (int i =0;i<feeds;i++) {
                    if (order.contains(i)) continue;
                    int t = 0;
                    for (int j=0;j<n;j++){
                        if (doses[j] >= crows[i][j]) {
                            t += doses[j] - crows[i][j];
                        }
                    }
                
                    
                    if (t < min) {
                        min = t;
                        k = i;
                    }
                }


                if (min > 0) {
                    for (int i =k+1;i<feeds;i++) {
                        if (order.contains(i)) continue;
                        int tl = 0;
                        for (int j=0;j<n;j++){
                            if (doses[j] >= crows[i][j]) {
                                tl += doses[j] - crows[i][j];
                            }
                        }

                        if (tl == min){
                            ArrayList<Integer> xxx = new ArrayList<Integer>();
                            for (Integer xx : order) {
                                xxx.add(xx);


                            }
                            xxx.add(i);
                            stack.add(xxx);

                        }
                    }


                }
                order.add(k);
                for (int i=0;i<n;i++) {
                    doses[i] -= crows[k][i];
                    if (doses[i] < 0) doses[i] = 0;
                }
                finished = true;
                for (int i=0;i<n;i++) {
                    finished = (finished) && (doses[i] == 0);
                }

            }
            
            if (order2 == null) {
                order2 = order;
            } else {
                if (order.size() < order2.size()) {
                    order2 = order;
                }
            }
        }


        Collections.sort(order2);
        out.print(order2.size());                           // output result
        for (Integer xx : order2) {
            out.print(" " + (xx + 1));
        }*/

        out.print(order.size());                           // output result
        for (Integer xx : order) {
            out.print(" " + (xx + 1));
        }

        out.println();
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
  }
}
