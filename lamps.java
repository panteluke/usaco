/*
ID: ...
LANG: JAVA
TASK: lamps
*/
import java.io.*;
import java.util.*;

public class lamps {

    static int n;
    static int c;
    static Boolean[] ff = new Boolean[101];
    static ArrayList<String> sol = new ArrayList<String>();
    static boolean[] temp = new boolean[101];
    
    
    private static void addSol() {
        String s = "";
        for (int i = 1;i<=n;i++) {
            
            if (temp[i]) s = s + "1"; else s = s + "0";
        }
        sol.add(s);
    }
    
    
    private static boolean checkSol() {
        boolean result = true;
        for (int i=1;i<=n;i++){
            if (ff[i] == null) continue;
            if (ff[i].booleanValue() != temp[i]) {
                result = false;
                break;
            }
        }
        
        return result;
        
    }
    
    private static void check(int evenNum) {
        boolean isOK;
        switch(evenNum) {
            case 0:
                Arrays.fill(temp, true);
                isOK = checkSol();
                if (isOK) {
                    addSol();
                }
                break;
            case 1:
                for (int j=1;j<=4;j++) {
                    Arrays.fill(temp, true);
                    isOK = true;
                    for (int i=1;i<=n;i++) {
                        if (j == 1) {
                            temp[i] = !temp[i];
                        }
                        if (j == 2) {
                            if (i % 2 == 1) {
                                temp[i] = !temp[i];
                            }
                        }
                        if (j == 3) {
                            if (i % 2 == 0) {
                                temp[i] = !temp[i];
                            }
                        }

                        if (j == 4) {
                            if (i % 3 == 1) {
                                temp[i] = !temp[i];
                            }
                        }

                    }
                    isOK = checkSol();
                    if (isOK) {
                        addSol();
                    }
                    
                }
                break;
                
            case 2:
                for (int j=1;j<=3;j++) {
                    for (int k = j + 1;k <= 4;k ++) {
                        Arrays.fill(temp, true);
                        isOK = true;
                        for (int i=1;i<=n;i++) {
                            if (j == 1) {
                                temp[i] = !temp[i];
                            }
                            if ((j == 2) || (k == 2)) {
                                if (i % 2 == 1) {
                                    temp[i] = !temp[i];
                                }
                            }
                            if ((j == 3) || (k ==3)) {
                                if (i % 2 == 0) {
                                    temp[i] = !temp[i];
                                }
                            }

                            if (k == 4) {
                                if (i % 3 == 1) {
                                    temp[i] = !temp[i];
                                }
                            }

                        }
                        isOK = checkSol();
                        if (isOK) {
                            addSol();
                        }
                    }
                }
                break;
                
                
            case 3:
                for (int j=1;j<=4;j++) {
                    Arrays.fill(temp, true);
                    isOK = true;
                    for (int i=1;i<=n;i++) {
                        if (j != 1) {
                            temp[i] = !temp[i];
                        }
                        if (j != 2) {
                            if (i % 2 == 1) {
                                temp[i] = !temp[i];
                            }
                        }
                        if (j != 3) {
                            if (i % 2 == 0) {
                                temp[i] = !temp[i];
                            }
                        }

                        if (j != 4) {
                            if (i % 3 == 1) {
                                temp[i] = !temp[i];
                            }
                        }

                    }
                    isOK = checkSol();
                    if (isOK) {
                        addSol();
                    }
                    
                }
                break;
                
            case 4:
                Arrays.fill(temp, true);
                isOK = true;
                for (int i=1;i<=n;i++) {
                    temp[i] = !temp[i];
                    if (i % 2 == 0) {
                        temp[i] = !temp[i];
                    }
                    if (i % 2 == 1) {
                        temp[i] = !temp[i];
                    }
                    
                    if (i % 3 == 1) {
                        temp[i] = !temp[i];
                    }
                    
                }
                isOK = checkSol();
                if (isOK) {
                    addSol();
                }
                break;
                
                
        }
    }

    

    public static void main(String [] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        n = Integer.parseInt(st.nextToken());    // first integer;

        st = new StringTokenizer(f.readLine());
        c = Integer.parseInt(st.nextToken());    // first integer;

        String[] s1 = f.readLine().split(" ");

        for (int i = 0; i<s1.length-1;i++) {
            int xx = Integer.parseInt(s1[i]);
            ff[xx] = true;
        }

        s1 = f.readLine().split(" ");

        for (int i = 0; i<s1.length-1;i++) {
            int xx = Integer.parseInt(s1[i]);
            ff[xx] = false;
        }





        if ((c % 2) == 0) {

            // 0 monoi
            check(0);

            //2 monoi
            if (c > 1)
                check(2);

            //4 monoi
            if (c > 3)
                check(4);
        } else {
            //1 monoi
            check(1);

            if (c > 2) {
                // 3 monoi
                check(3);
            }
        }




        if (sol.isEmpty()) {
            out.println("IMPOSSIBLE");
        } else {
            Collections.sort(sol);
            for (String ss : sol) {
                out.println(ss);
            }
        }

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!

    }
}
