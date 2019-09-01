/*
ID: ...
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;


public class subset {

    static long sols = 0;
    static int sum = 0;       
    static int halfSum = 0;
    
   
    
   

    public static void main(String [] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        int N = Integer.parseInt(f.readLine());
        
        long hhum[] = new long[781];
        for (int i =0; i<=780;i++) {
            hhum[i] = 0;
        }
        hhum[0] = 1;
        hhum[1] = 1;
        
        for (int i=2;i<=N;i++) {
            int endi = i * (i+1)/2;
            for (int j = endi;j>=i;j--) {
                hhum[j] = hhum[j] + hhum[j-i];
            }
            
        }
        
        
        sum = 0;
        for (int i = 1;i<=N;i++) {
            sum += i;
        }
        
        if (sum % 2 == 0) {
            halfSum = sum / 2;
            sols = hhum[halfSum] / 2;                    
            
        }
        out.println(sols);
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!


    }

}
