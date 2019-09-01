/*
ID: ...
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

class namenum {


 private static char GetChar(char a) {
     switch (a) {
         case 'A': case 'B': case 'C': return '2';
         case 'D': case 'E': case 'F': return '3';
         case 'G': case 'H': case 'I': return '4';
         case 'J': case 'K': case 'L': return '5';
         case 'M': case 'N': case 'O': return '6';
         case 'P': case 'R': case 'S': return '7';
         case 'T': case 'U': case 'V': return '8';
         case 'W': case 'X': case 'Y': return '9';
     }
     return '0';
 }


  public static void main (String [] args) throws IOException {



    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());

    String number = st.nextToken();

    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f2 = new BufferedReader(new FileReader("dict.txt"));
    String currentLine;
    int counter = 0;
    while ((currentLine = f2.readLine()) != null) {
        if (currentLine.length() != number.length()) continue;
        boolean match = true;
        for (int i = 0; i < number.length(); i++) {
            if  (GetChar(currentLine.charAt(i)) != number.charAt(i)) { match = false; break;}
        }
        if (match) { out.println(currentLine); counter++;}
     }

    if (counter == 0) out.println("NONE");
    
    

    // Get line, break into tokens

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}
