/*
ID: ...
LANG: JAVA
PROG: palsquare
*/
import java.io.*;
import java.util.*;
class palsquare {
	
  private static boolean isPal(String s) {
	for (int i=0;i < s.length() / 2; i++) {
		if (s.charAt(i) != s.charAt(s.length() -1 - i)) return false;
	}
	return true;
  }
  
  private static char GetChar(int df) {
	  switch (df) {
	  case 0: return '0';
	  case 1: return '1';
	  case 2: return '2';
	  case 3: return '3';
	  case 4: return '4';
	  case 5: return '5';
	  case 6: return '6';
	  case 7: return '7';
	  case 8: return '8';
	  case 9: return '9';
	  case 10: return 'A';
	  case 11: return 'B';
	  case 12: return 'C';
	  case 13: return 'D';
	  case 14: return 'E';
	  case 15: return 'F';
	  case 16: return 'G';
	  case 17: return 'H';
	  case 18: return 'I';
	  case 19: return 'J';
	  }
	 return 'A'; 
  }
  
  private static String GetToBase(int base, int sqr) {
	  int counter = 1;
	  String result = "";
	  while (Math.pow(base, counter) - 1 < sqr) counter++;
	  
	  while (counter > 0) {
		  int rowed = 1;
		  for (int i = 1; i < counter; i++){
			  rowed = rowed * base;
		  }
		  int df = sqr / rowed;
		  result = result + GetChar(df);
		  
		  sqr -= df * rowed;
		  counter--;
	  }
	  return result;
  }
  
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
						  // Get line, break into tokens 
    int base = Integer.parseInt(st.nextToken());    // first integer
    
    for (int i=1;i<301;i++){
    	int sqr = i*i;
    	String s = GetToBase(base, sqr);
    	if (isPal(s)) {
    		String s2 = GetToBase(base, i);
    		out.print(s2 + " ");
    		out.println(s);
    	}
    }
    
    
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}
