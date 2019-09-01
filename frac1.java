/*
ID: ...
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;



class frac1 {

    public class Fraction{
        public int nominator;
        public int detomitor;
        public Fraction(int n, int d) {
            this.nominator = n;
            this.detomitor = d;
        }

        public boolean isEqual(Fraction x) {
            return ((x.nominator * this.detomitor) == (x.detomitor * this.nominator));
        }

        public boolean isSmaller(Fraction x) {
            return ((this.nominator * x.detomitor) < (this.detomitor * x.nominator));
        }

    }
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        //BufferedReader f = new BufferedReader(new FileReader("J:\\problems\\UsacoJava\\src\\milk2.in"));
        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
                                                  // input file name goes above
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("J:\\problems\\UsacoJava\\src\\milk2.out")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());


        int n = Integer.parseInt(st.nextToken());    // first integer;

        frac1 fr = new frac1();

        ArrayList<Fraction> list = new ArrayList<Fraction>();
        Fraction ft = fr.new Fraction(0,1);
        list.add(ft);
        Fraction ft1 = fr.new Fraction(1,1);
        list.add(ft1);

        for (int det = 2;det<=n;det++){
            for (int nom=1;nom<det;nom++){
                Fraction ftx = fr.new Fraction(nom,det);
                boolean isPresent = false;
                for (Fraction xx : list) {
                    if (xx.isEqual(ftx)) {
                        isPresent = true;
                        break;
                    }
                }
                
                if (!isPresent) {
                    list.add(ftx);
                }
            }
        }
        
        
        for (int i=1;i<list.size();i++){
            Fraction xx = list.remove(i);
            int j = i - 1;
            while (xx.isSmaller(list.get(j))) {
                j--;
            }
            j++;
            list.add(j, xx);
        }
        for (Fraction xx: list) {
            out.println(xx.nominator+"/"+xx.detomitor);                           // output result    
        }
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
  }
}