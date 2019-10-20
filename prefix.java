/*
ID: ...
LANG: JAVA
PROG: prefix
*/
import java.io.*;
import java.util.*;
class prefix {



  public static int hasBiggest(int l, boolean[] b) {
	  int res = -1;
	  for (int i = l+1; i<b.length;i++) {
		  if (b[i]) {
			  res = i;
			  break;
		  }
	  }
	  return res;
  }


  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    //BufferedReader f = new BufferedReader(new FileReader("j:\\nanSoft\\problems\\usaco\\src\\prefix.in"));
    BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
                                                  // input file name goes above
    //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("j:\\nanSoft\\problems\\usaco\\src\\prefix.out")));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

    String[][] primitives = new String[10][200];
    int[] stopPos = new int[10];
    String s;

    boolean[] high = new boolean[200001];

    String currentLine;
    while (!(currentLine = f.readLine()).equalsIgnoreCase(".")) {
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(currentLine);
        while (st.hasMoreTokens()) {
		String pr = st.nextToken();
		primitives[pr.length()-1][stopPos[pr.length()-1]] = pr;
		stopPos[pr.length()-1]++;
        }

    }

    StringBuilder sb = new StringBuilder();
    while ((currentLine = f.readLine()) != null) {
        sb.append(currentLine);
    }
    s = sb.toString();

    int longest = -1;

    high[0] = true;
    int nLongest;
    while ((nLongest = hasBiggest(longest, high)) != -1) {
	longest = nLongest;

	for (int i = 1; i <= 10; i++) {
		if (i + longest > s.length()) break;
		if (high[i + longest]) continue;
		for (int j = 0; j < stopPos[i-1]; j++) {
			if  (primitives[i-1][j].equalsIgnoreCase(s.substring(longest, longest + i))) {
				high[i + longest] = true;
				break;
			}

		}

	}
    }

    out.println(longest);
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}
