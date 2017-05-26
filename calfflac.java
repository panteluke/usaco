/*
ID: panteli1
LANG: JAVA
TASK: calfflac
*/
import java.io.*;

class calfflac {

  private char[] tt = new char[20000];

  private boolean isRight(int index) {
    int index1 = index + 1;
    while  (!(((tt[index1] >= 'A') && (tt[index1] <= 'Z')) || ((tt[index1] >= 'a') && (tt[index1] <= 'z')))) {
        index1++;
        if (index1 == 20000) return false;
    }


    return Character.toLowerCase(tt[index])== Character.toLowerCase(tt[index1]);
  }

  private boolean isMiddle(int index) {
    if (index == 0) return false;

    int index1 = index + 1;
    while  (!(((tt[index1] >= 'A') && (tt[index1] <= 'Z')) || ((tt[index1] >= 'a') && (tt[index1] <= 'z')))) {
        index1++;
        if (index1 == 20000) return false;
    }

    int index2 = index - 1;
    while  (!(((tt[index2] >= 'A') && (tt[index2] <= 'Z')) || ((tt[index2] >= 'a') && (tt[index2] <= 'z')))) {
        index2--;
        if (index2 < 0) return false;
    }

	  return (Character.toLowerCase(tt[index1]) == Character.toLowerCase(tt[index2]));
  }

  private int FindMax(int index, int offset) {
	  int t = 0;
	  int index1=index+offset;
            while  (!(((tt[index1] >= 'A') && (tt[index1] <= 'Z')) || ((tt[index1] >= 'a') && (tt[index1] <= 'z')))) {
                index1++;
                if (index1 == 20000) return 0;
            }

	  int index2=index;
            while  (!(((tt[index2] >= 'A') && (tt[index2] <= 'Z')) || ((tt[index2] >= 'a') && (tt[index2] <= 'z')))) {
                index2--;
                if (index2 < 0) return 0;
            }

	  while (Character.toLowerCase(tt[index1]) == Character.toLowerCase(tt[index2])) {
		  t++;
                  index1++;
                  index2--;

                  if (index2 < 0) return t;
                  if (index1 == 20000) return t;

                    while  (!(((tt[index1] >= 'A') && (tt[index1] <= 'Z')) || ((tt[index1] >= 'a') && (tt[index1] <= 'z')))) {
                        index1++;
                        if (index1 == 20000) return t;
                    }

                    while  (!(((tt[index2] >= 'A') && (tt[index2] <= 'Z')) || ((tt[index2] >= 'a') && (tt[index2] <= 'z')))) {
                        index2--;
                        if (index2 < 0) return t;
                    }
		  

	  }
	  return t;
  }

  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    String line;// = "Confucius say: Madam, I'm Adam.";
    int counter = 0;
    calfflac cl = new calfflac();

    while ((line = f.readLine()) != null) {
    	for (int i=0;i<line.length();i++) {
    		cl.tt[counter] = line.charAt(i);
    		counter++;
    	}
    	cl.tt[counter] = '\n';
    	counter++;
    }

    int maxSize=0;
    int startPosition = 0;

    for (int i=0;i< counter -1;i++){
    	if  (((cl.tt[i] >= 'A') && (cl.tt[i] <= 'Z')) || ((cl.tt[i] >= 'a') && (cl.tt[i] <= 'z'))) {
            if (cl.isRight(i)) {
                    int t1 = 2 * cl.FindMax(i, 1);
                    if (t1 > maxSize) {
                            maxSize = t1;
                            int str = maxSize / 2;
                            int ind = i;
                            while (str > 0) {
                                while  (!(((cl.tt[ind] >= 'A') && (cl.tt[ind] <= 'Z')) || ((cl.tt[ind] >= 'a') && (cl.tt[ind] <= 'z')))) {
                                    ind--;
                                }
                                str--;
                                ind--;
                            }

                            ind++;
                            startPosition = ind;
                    }
            }

            if (cl.isMiddle(i)){
                    int t1 = 2 * cl.FindMax(i-1, 2) + 1;
                    if (t1 > maxSize) {
                            maxSize = t1;
                            int str = maxSize / 2 + 1;
                            int ind = i;
                            while (str > 0) {
                                while  (!(((cl.tt[ind] >= 'A') && (cl.tt[ind] <= 'Z')) || ((cl.tt[ind] >= 'a') && (cl.tt[ind] <= 'z')))) {
                                    ind--;
                                }
                                str--;
                                ind--;
                            }

                            ind++;
                            startPosition = ind;

                            
                    }

            }
    	}
    }

    int i = startPosition;
    int cll = 0;
    out.println(maxSize);
    while (cll <  maxSize) {
    	out.print(cl.tt[i]);
    	if  (((cl.tt[i] >= 'A') && (cl.tt[i] <= 'Z')) || ((cl.tt[i] >= 'a') && (cl.tt[i] <= 'z'))){cll++;}
    	i++;

    }
    out.println();

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}