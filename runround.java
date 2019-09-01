/*
ID: ...
LANG: JAVA
TASK: runround
*/
import java.io.*;
import java.util.*;


public class runround {


    public static int nextR(int m) {
        int result = m;

        boolean isOK = false;
        while (!isOK) {
            String s = Integer.toString(result);
            int len = s.length();

            boolean unique = false;
            while (!unique) {
                s = Integer.toString(result);
                len = s.length();
                int[] f = new int[10];
                Arrays.fill(f, 0);
                f[0] = 1;
                boolean changed = false;
                for (int i=0;i<len;i++) {
                    f[Integer.parseInt(s.substring(i, i + 1))]++;
                }
                String xx = "";
                for (int i=0;i<len;i++) {
                    int gg = Integer.parseInt(s.substring(i, i + 1));
                    if (f[gg] > 1) {
                        changed = true;
                        for (int j = gg + 1; gg <= 9; gg++) {
                            if (f[j] == 0) {
                                f[j]++;
                                xx = xx + j;
                                break;
                            }
                        }

                    } else {
                        xx = xx + s.charAt(i);
                    }
                    
                }
                s = xx;
                if (changed) {
                    result = Integer.parseInt(s);
                }
                
                isOK = (!changed);

            }


            if ((s.indexOf("0") > - 1)) {
                String xx = "";
                int counter = 1;
                for (int i=0;i<len;i++) {
                    if (s.charAt(i) == '0') {
                        xx = xx + counter++;
                    } else {
                        xx = xx + s.charAt(i);
                    }

                }
                result = Integer.parseInt(xx);
            } else {
                if (s.indexOf(len) > -1) {
                    result++;
                } else {

                    Set<Integer> positions = new TreeSet<Integer>();

                    int start = 0;
                    while (true) {
                        start = (start + Integer.parseInt(s.substring(start, start+1))) % len;
                        if (positions.contains(new  Integer(start))) {
                            break;
                        } else {
                            positions.add(start);
                        }

                    }

                    isOK = (positions.size() == len);
                    if (!isOK) result++;

                }
            }

        }


        return result;
    }

            

    public static boolean IsRound(int a) {
        String s = Integer.toString(a);
        int len = s.length();
        Set<Integer> positions = new TreeSet<Integer>();

        int start = 0;
        while (true) {
            start = (start + Integer.parseInt(s.substring(start, start+1))) % len;
            if (positions.contains(new  Integer(start))) {
                break;
            } else {
                positions.add(start);
            }

        }
        return (positions.size() == len);
    }
           

    public static int GetFF(int siz) {
        String s = "";
        for (int i =1;i <= siz; i++) {
            s = s + Integer.toString(i);
        }
        return Integer.parseInt(s);
    }

    public static int GetNextUnique(int a,int start) {
        String s = Integer.toString(a);
        int len = s.length();
        if (start == len) {
            return GetFF(len+1);
        } else {
        int[] f = new int[10];
        Arrays.fill(f, 0);
        f[0] = 1;
        for (int i=0;i<len - start;i++) {
            f[Integer.parseInt(s.substring(i, i + 1))]++;
        }

        int pos = len - 1 - start;
        int sst = Integer.parseInt(s.substring(pos,pos + 1));

        int found = -1;
        for (int j = sst + 1;j <=9;j++) {
            if (f[j] == 0) {
                found = j;
                break;
            }
        }

        if (found == -1) {
            return GetNextUnique(a, start + 1);
        } else {
            String xx = "";
            f[sst]--;
            xx = s.substring(0,pos);
            xx = xx + Integer.toString(found);
            f[found]++;
            for (int kl = pos + 1;kl<len;kl++){
                for (int lk=1;lk<10;lk++) {
                    if (f[lk] == 0) {
                        xx = xx + Integer.toString(lk);
                        f[lk]++;
                        break;
                    }
                }
            }
            return Integer.parseInt(xx);
        }
        }


    }

    public static int biggest(int siz) {
        String s = "";
        for (int i=9;siz>0;siz--) {
            s = s  + Integer.toString(i);
            i--;
        }
        return Integer.parseInt(s);
    }


    public static int GetFirstUnique(int a) {
        a++;
        String s = Integer.toString(a);
        int len = s.length();

        if (a > biggest(len)) {
            return GetFF(len+1);
        } else {
            int[] f = new int[10];
            Arrays.fill(f, 0);
            f[0] = 1;
            for (int i=0;i<len;i++) {
                f[Integer.parseInt(s.substring(i, i + 1))]++;
                if (f[Integer.parseInt(s.substring(i, i + 1))]>1) {
                    return GetNextUnique(a, len-1-i);
                }
            }

            return a;
        }


    }


    public static void main(String [] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("runround.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
        int M = Integer.parseInt(f.readLine());

        if (M < 9) {
            out.println((M+1));
        } else {

            M = GetFirstUnique(M);
            while (!IsRound(M)) {
                M = GetNextUnique(M, 0);
            }
            out.println(M);
        }

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!


    }

}
