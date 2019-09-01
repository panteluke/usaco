/*
ID: ...
LANG: JAVA
TASK: packrec
*/
import java.io.*;
import java.util.*;



class packrec {
  public class Rect {
      public int width;
      public int height;
      public Rect(int w, int h) {
          this.height = h;
          this.width = w;
      }

      public void rotate() {
          int x = this.height;
          this.height = this.width;
          this.width = x;
      }
  }
  public Rect[] r = new Rect[4];
  public int min = Integer.MAX_VALUE;
  public boolean[] sides = new boolean[201];

  public void checkRect(Rect b) {
      if (b.width * b.height < min) {
        min = b.width * b.height;
        Arrays.fill(sides, false);
      }
    if (b.width * b.height == min) {
        sides[Math.min(b.width, b.height)] = true;
    }

  }
  public void check(){
        Rect b1 = new Rect(0, 0);
        for (int i=0;i<4;i++){
            b1.width += r[i].width;
            b1.height = Math.max(b1.height, r[i].height);
        }
        checkRect(b1);

        b1.width = 0;
        b1.height = 0;
        for (int i=0; i<3; i++) {
            b1.width += r[i].width;
            b1.height = Math.max(b1.height, r[i].height);
        }
        b1.height += r[3].height;
        b1.width = Math.max(b1.width, r[3].width);

        checkRect(b1);

        b1.width = 0;
        b1.height = 0;
        for (int i=0; i<2; i++) {
            b1.width += r[i].width;
            b1.height = Math.max(b1.height, r[i].height);
        }
        b1.height += r[2].height;
        b1.width = Math.max(b1.width, r[2].width);

        b1.height = Math.max(b1.height, r[3].height);
        b1.width += r[3].width;

        checkRect(b1);

        b1.width = 0;
        b1.height = 0;
        for (int i=0; i<2; i++) {
            b1.width += r[i].width;
            b1.height = Math.max(b1.height, r[i].height);
        }
        b1.height = Math.max(b1.height, r[2].height + r[3].height) ;
        b1.width += Math.max(r[3].width, r[2].width);

        checkRect(b1);


        b1.height = Math.max(r[0].height+r[2].height, r[1].height+r[3].height);
        b1.width = r[0].width + r[1].width;

        /* do 2 and 1 touch? */
        if(r[0].height < r[1].height)
            b1.width = Math.max(b1.width, r[2].width+r[1].width);
        /* do 2 and 3 touch? */
        if(r[0].height+r[2].height > r[1].height)
            b1.width = Math.max(b1.width, r[2].width+r[3].width);
        /* do 0 and 3 touch? */
        if(r[1].height < r[0].height)
            b1.width = Math.max(b1.width, r[0].width+r[3].width);

    /* maybe 2 or 3 sits by itself */
    b1.width = Math.max(b1.width, r[2].width);
    b1.width = Math.max(b1.width, r[3].width);
    checkRect(b1);

  }

  public void rotate(int n) {
      if (n == 4) {
          check();
      }
 else {
      rotate(n+1);
      r[n].rotate();
      rotate(n+1);
      r[n].rotate();}

  }

  public void permute(int n){
      if (n == 4) {
          rotate(0);
      }
      for (int i=n;i<4;i++){
          Rect t;
          t = r[n];
          r[n] = r[i];
          r[i] = t;
          permute(n+1);
          t = r[n];
          r[n] = r[i];
          r[i] = t;

      }

  }


  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    //BufferedReader f = new BufferedReader(new FileReader("J:\\problems\\UsacoJava\\src\\milk2.in"));
      BufferedReader f = new BufferedReader(new FileReader("packrec.in"));
                                                  // input file name goes above
    //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("J:\\problems\\UsacoJava\\src\\milk2.out")));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster

      packrec p = new packrec();

      for (int i =0;i<4;i++){
        StringTokenizer st = new StringTokenizer(f.readLine());
        int w = Integer.parseInt(st.nextToken());    // first integer;
        int h = Integer.parseInt(st.nextToken());    // first integer;
        p.r[i] = p.new Rect(w, h);
      }
    
    
      p.permute(0);
    
    
    out.println(p.min);                           // output result
    for (int i=1;i<201;i++){
        if (p.sides[i]){
            out.println(i + " " + (p.min / i));
        }
    }
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}