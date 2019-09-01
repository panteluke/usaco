/*
ID: ...
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

class crypt1 {

  private static boolean hasUnique(int product, ArrayList<Character> ss, int length) {
      String s = new Integer(product).toString();

      if (s.length() != length) return false;
      for (int i = 0;i<s.length();i++){
          if (ss.indexOf(s.charAt(i)) == -1) return false;
      }
      return true;
  }

  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());

    int a = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(f.readLine());
    int[] digits = new int[a];
    ArrayList<Character> ss = new ArrayList<Character>();
    int counter =0;
    while (st.hasMoreTokens()) {
        digits[counter++] = Integer.parseInt(st.nextToken());

    }
    for (int i=0;i<digits.length;i++) {
        ss.add(new Integer(digits[i]).toString().charAt(0));
    }




    int result=0;

    for (int i=0;i<digits.length;i++){
        for (int j=0;j<digits.length;j++){
            for (int k=0;k<digits.length;k++){

                int firstNum = 100*digits[i] + 10 * digits[j] + digits[k];

                for (int l=0;l<digits.length;l++){
                    if  (!hasUnique(digits[l] * firstNum, ss , 3)) continue;
                    for (int m =0 ;m <digits.length;m++){
                        if (!hasUnique(digits[m] * firstNum, ss, 3)) continue;
                        int secondNum = 10 * digits[l] + digits[m];

                        int thirdNum = firstNum * secondNum;
                        if (hasUnique(thirdNum, ss, 4)) {
                            result++;
                        }

                    }
                }
            }
        }

    }


    out.println(result);
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}
