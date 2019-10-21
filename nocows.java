/*
ID: ...
LANG: JAVA
PROG: nocows
*/
import java.io.*;
import java.util.*;

class nocows {
  private static long[][] sols = new long[200][100];

  private static long countsols(int row, int col) {
    long result = 0;

    for (int i =0; i <= col;i++) {
      result = result + sols[row][i];
    }

    return result;
  }

  private static void buildsols() {
    for (int i = 9; i < 200; i = i + 2) {
      int nodes = i;
      int children = nodes - 1;
      int half = children / 2;

      int start_height = 3;
      while (Math.pow(2, start_height) < i) {
        start_height++;
      }

      for (int height = start_height; height < 100; height++) {
        long total = 0;

        for (int j = 1; j <= half; j = j + 2) {
          long left_exact = sols[children - j][height - 1];
          long right_exact = sols[j][height - 1];
          long left_lower = countsols(children - j, height - 2);
          long right_lower = countsols(j, height - 2);

          if (j != half) {
            total += 2 * left_exact * (right_exact + right_lower);
            total += 2 * left_lower * right_exact;
          } else {
            total += left_exact * right_lower;
            total += left_lower * right_exact;
            total += left_exact * right_exact;
          }
        }

        sols[nodes][height] = total % 9901;
      }
    }
  }

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    for (int i = 0; i < 200; i++){
      for (int j = 0; j < 100; j++){
        sols[i][j] = 0;
      }
    }
    sols[1][1] = 1;
    sols[3][2] = 1;
    sols[5][3] = 2;
    sols[7][3] = 1;
    sols[7][4] = 4;
    buildsols();

    out.println(sols[n][k] % 9901);
    out.close();
    System.exit(0);
  }
}
