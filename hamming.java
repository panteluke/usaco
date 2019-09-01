/*
ID: ...
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;

class hamming {

    public static int GetDiff(int a, int b) {
        int c = a ^ b;
        int result = 0;
        result = result + ((c & 128) / 128);
        result = result + ((c & 64) / 64);
        result = result + ((c & 32) / 32);
        result = result + ((c & 16) / 16);
        result = result + ((c & 8) / 8);
        result = result + ((c & 4) / 4);
        result = result + ((c & 2) / 2);
        result = result + (c & 1) ;
        return result;

    }

    public static void main(String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] ham = new int[256][256];
        for (int i=0;i<256;i++) {
            for (int j=i;j<256;j++) {
                ham[i][j] = GetDiff(i,j);
            }
        }

        ArrayList<Integer> order = new ArrayList<Integer>();
        order.add(0);
        n--;
        
        int stt = 1;
        for (int i = 1; i < b + 1; i++) {
            stt = stt * 2;
        }
        stt = stt * 2 - 1;
        for (int i = 1; i < stt; i++){
            boolean isOK = true;
            for (Integer xx : order) {
                if (ham[Math.min(i, xx)][Math.max(i, xx)] < d) {
                    isOK = false;
                    break;
                }
            }
            if (isOK) {
                order.add(i);    
                n--;
            }
            if (n ==0) {
                break;
            }
        }

        for (int i = 1; i <= order.size(); i ++) {
            out.print(order.get(i - 1));
            if (i % 10 == 0) {
               out.println();
            }   else {
                if (i != order.size()) {
                out.print(" ");
                } else {
                    out.println();
                }
             }
        }
        
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!

    }
}
