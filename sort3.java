 /*
ID: ...
LANG: JAVA
TASK: sort3
*/
import java.io.*;


class sort3 {

    public static int isperfect(int n, int[] a, int[] b, int j) {
        int result = -1;
        for (int i=j+1;i<n;i++){
            if ((b[j]==a[i]) && (a[j] == b[i])) {
                result = i;
                break;
            }
        }

        return result;
    }

    public static int isnear(int n, int[] a, int[] b, int j) {
        int result = -1;
        for (int i=j+1;i<n;i++){
            if ((b[j]==a[i]) && (a[i] != b[i])) {
                result = i;
                break;
            }
        }


        return result;
    }

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        //BufferedReader f = new BufferedReader(new FileReader("J:\\problems\\UsacoJava\\src\\milk2.in"));
        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
                                                  // input file name goes above
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("J:\\problems\\UsacoJava\\src\\milk2.out")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster

        int n = Integer.parseInt(f.readLine());
        int[] a = new int[1000];
        int[] b = new int[1000];

        for (int i=0;i<n;i++){
            a[i] = Integer.parseInt(f.readLine());
        }

        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        for (int i=0;i<n;i++) {
            if (a[i] == 1) {
                x1++;
            }
            if (a[i] == 2) {
                x2++;
            }
            if (a[i] == 3) {
                x3++;
            }
        }
        for (int i = 0;i < x1;i++){
            b[i] = 1;
        }
        for (int i = 0;i < x2;i++){
            b[x1 + i] = 2;
        }
        for (int i = 0;i < x3;i++){
            b[x1 + x2 + i] = 3;
        }


        int swaps = 0;
        for (int i=0;i<2;i++){
            for (int j=0;j<n;j++) {
                if (a[j] != b[j]) {
                    int k = isperfect(n, a, b, j);
                    if (k != -1) {
                        int xx = a[k];
                        a[k] = a[j];
                        a[j] = xx;
                        swaps++;
                    } else {
                      int l = isnear(n, a, b, j);
                        int xx = a[l];
                        a[l] = a[j];
                        a[j] = xx;
                        swaps++;
                    }
                }
            }
        }

        out.println(swaps);                           // output result
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!

    }

}
