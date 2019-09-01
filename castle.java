/*
ID: ...
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;

class castle {
    int M;
    int N;
    int[][] mapp = new int[51][51];
    int rooms;
    int biggest;
    int tobe;
    String wall;
    static final int WW = 1;
    static final int WN = 2;
    static final int WE = 4;
    static final int WS = 8;

    int[][] marked = new int[51][51];

    private class casData {
        int rooms = 0;
        int biggest = 0;
    }


    private int mark(int i, int j, int roomnum) {
        if (marked[i][j] == 0) {
            int bg = 1;
            marked[i][j] = roomnum;

            if ((j > 1) && ((mapp[i][j] & WW) == 0))    {
                bg = bg + mark(i, j - 1, roomnum);
            }

            if ((i > 1) && ((mapp[i][j] & WN) == 0))    {
                bg = bg + mark(i - 1, j, roomnum);
            }

            if ((j < M) && ((mapp[i][j] & WE) == 0))    {
                bg = bg + mark(i, j + 1, roomnum);
            }

            if ((i < N) && ((mapp[i][j] & WS) == 0))    {
                bg = bg + mark(i + 1, j, roomnum);
            }


            return bg;
        } else {
            return 0;
        }
    }

    private casData GetData() {
        for (int i=0;i<=50;i++){
            for (int j=0;j<=50;j++){
                marked[i][j] = 0;
            }
        }
        
        casData cf = new casData();
        for (int i=1; i <= N;i ++){
            for (int j = 1; j <= M; j++) {
                if (marked[i][j] != 0) continue;
                cf.rooms++;
                int t = mark(i,j, cf.rooms);
                if (t>cf.biggest) {
                    cf.biggest = t;
                }
            }
        }

        return cf;
    }

    private int GetTot(int a) {
        int result = 0;
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) {
                if (marked[i][j] == a) {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        castle cas = new castle();
        cas.M = Integer.parseInt(st.nextToken());
        cas.N = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= cas.N; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 1; j <= cas.M; j++) {
                cas.mapp[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        casData cd = cas.GetData();
        cas.rooms = cd.rooms;
        cas.biggest = cd.biggest;

        for (int j=1;j<= cas.M; j++){
        //for (int i = 1; i <= cas.N; i++) {
            for (int i = cas.N; i > 0; i--) {
            //for (int j=cas.M; j > 0 ; j--){
                
                if ((i > 1) && ((cas.mapp[i][j] & WN) == WN))    {
                    if (cas.marked[i-1][j] != cas.marked[i][j]) {
                        int df = cas.GetTot(cas.marked[i-1][j]) + cas.GetTot(cas.marked[i][j]);
                        if (df > cas.tobe) {
                            cas.tobe = df;
                            cas.wall = i + " " + j + " N";
                        }
                    }
                }

                if ((j < cas.M) && ((cas.mapp[i][j] & WE) == WE))    {
                    if (cas.marked[i][j] != cas.marked[i][j + 1]) {
                        int df = cas.GetTot(cas.marked[i][j]) + cas.GetTot(cas.marked[i][j + 1]);
                        if (df > cas.tobe) {
                            cas.tobe = df;
                            cas.wall = i + " " + j + " E";
                        }
                    }
                    
                }
                
            }
        }





        out.println(cas.rooms);
        out.println(cas.biggest);
        out.println(cas.tobe);
        out.println(cas.wall);
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!

    }
}
