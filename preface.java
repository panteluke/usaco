/*
ID: panteli1
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;

class preface {

    static int[] sym = new int[7];

    public static void fix(int a, int level){
        int st = (level -1) * 2;
        switch (a) {
            case 1: sym[st]++;
                    break;
            case 2: sym[st] += 2;
                    break;
            case 3: sym[st] += 3;
                    break;
            case 4: sym[st]++;sym[st+1]++;
                    break;
            case 5: sym[st+1]++;
                    break;
            case 6: sym[st]++;sym[st+1]++;
                    break;
            case 7: sym[st] += 2;sym[st+1]++;
                    break;
            case 8: sym[st] += 3;sym[st+1]++;
                    break;
            case 9: sym[st]++;sym[st+2]++;
                    break;

        }
    }

    public static void main(String [] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("preface.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
        int M = Integer.parseInt(f.readLine());

        Arrays.fill(sym, 0);

        for (int i = 1;i<=M;i++) {

            String xx = Integer.toString(i);
            for (int j = xx.length()-1;j >= 0;j--) {
                int k = xx.length() - j;
                int ll = Integer.parseInt(xx.substring(j,j+1));
                fix(ll,k);
            }
        }

        if (sym[0] > 0) {
            out.println("I " + sym[0]);
        }

        if (sym[1] > 0) {
            out.println("V " + sym[1]);
        }
        if (sym[2] > 0) {
            out.println("X " + sym[2]);
        }
        if (sym[3] > 0) {
            out.println("L " + sym[3]);
        }
        if (sym[4] > 0) {
            out.println("C " + sym[4]);
        }
        if (sym[5] > 0) {
            out.println("D " + sym[5]);
        }
        if (sym[6] > 0) {
            out.println("M " + sym[6]);
        }

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!


    }

}
