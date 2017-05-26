/*
ID: panteli1
LANG: JAVA
TASK: clocks
*/
import java.io.*;
import java.util.*;

public class clocks {

    private ArrayList<String> queue = new ArrayList<String>();
    private SortedSet<String> been = new TreeSet<String>();
    private ArrayList<String> queueTrans = new ArrayList<String>();
    private boolean found = false;
    private String res = "";
    private String move(String state, int way) {
        int[] cal = new int[9];
        for (int i =0;i<state.length();i++){
            cal[i] = Integer.parseInt(state.substring(i,i+1));

        }
        switch (way) {
            case 1:
                cal[0] = (cal[0] + 3) % 12;
                cal[1] = (cal[1] + 3) % 12;
                cal[3] = (cal[3] + 3) % 12;
                cal[4] = (cal[4] + 3) % 12;
                break;
            case 2:
                cal[0] = (cal[0] + 3) % 12;
                cal[1] = (cal[1] + 3) % 12;
                cal[2] = (cal[2] + 3) % 12;
                break;
            case 3:
                cal[1] = (cal[1] + 3) % 12;
                cal[2] = (cal[2] + 3) % 12;
                cal[4] = (cal[4] + 3) % 12;
                cal[5] = (cal[5] + 3) % 12;
                break;
            case 4:
                cal[0] = (cal[0] + 3) % 12;
                cal[3] = (cal[3] + 3) % 12;
                cal[6] = (cal[6] + 3) % 12;
                break;
            case 5:
                cal[1] = (cal[1] + 3) % 12;
                cal[3] = (cal[3] + 3) % 12;
                cal[4] = (cal[4] + 3) % 12;
                cal[5] = (cal[5] + 3) % 12;
                cal[7] = (cal[7] + 3) % 12;
                break;
            case 6:
                cal[2] = (cal[2] + 3) % 12;
                cal[5] = (cal[5] + 3) % 12;
                cal[8] = (cal[8] + 3) % 12;
                break;
            case 7:
                cal[3] = (cal[3] + 3) % 12;
                cal[4] = (cal[4] + 3) % 12;
                cal[6] = (cal[6] + 3) % 12;
                cal[7] = (cal[7] + 3) % 12;
                break;
            case 8:
                cal[6] = (cal[6] + 3) % 12;
                cal[7] = (cal[7] + 3) % 12;
                cal[8] = (cal[8] + 3) % 12;
                break;
            case 9:
                cal[4] = (cal[4] + 3) % 12;
                cal[5] = (cal[5] + 3) % 12;
                cal[7] = (cal[7] + 3) % 12;
                cal[8] = (cal[8] + 3) % 12;
                break;
        

        }

        StringBuilder result = new StringBuilder();
        for (int i=0;i<9;i++){
            result.append(Integer.toString(cal[i]));
        }
        return result.toString();
    }
    public void search() {
        
        while ((!queue.isEmpty()) && (!found)) {
            String state = queue.remove(0);
            String trans = queueTrans.remove(0);
            int j = 1;
            if (trans.length() > 0) {
                j = Integer.parseInt(trans.substring(trans.length()-1, trans.length()));
            }
            for (int i=j;i<10;i++) {

                int k = 1;

                if ((state.charAt(0) == '0') && (i==4)) continue;
                if ((state.charAt(0) != '0') && (i > 4)) continue;
                if (i==4) {
                    if (state.charAt(0) == '3') k = 3;
                    if (state.charAt(0) == '6') k = 2;
                }
                if ((state.charAt(1) == '0') && (i==5)) continue;
                if ((state.charAt(1) != '0') && (i>5)) continue;
                if (i==5) {
                    if (state.charAt(1) == '3') k = 3;
                    if (state.charAt(1) == '6') k = 2;
                }

                if ((state.charAt(2) == '0') && (i==6)) continue;
                if ((state.charAt(2) != '0') && (i>6)) continue;
                if (i==6) {
                    if (state.charAt(2) == '3') k = 3;
                    if (state.charAt(2) == '6') k = 2;
                }

                if ((state.charAt(3) == '0') && (i==7)) continue;
                if ((state.charAt(3) != '0') && (i>7)) continue;
                if (i==7) {
                    if (state.charAt(3) == '3') k = 3;
                    if (state.charAt(3) == '6') k = 2;
                }
                
                if ((state.charAt(6) == '0') && (i==8)) continue;
                if ((state.charAt(6) != '0') && (i>8)) continue;
                if (i==8) {
                    if (state.charAt(6) == '3') k = 3;
                    if (state.charAt(6) == '6') k = 2;
                }
                
                if ((state.charAt(4) == '0') && (i==9)) continue;
                if ((state.charAt(5) == '0') && (i==9)) continue;
                if ((state.charAt(7) == '0') && (i==9)) continue;
                if ((state.charAt(8) == '0') && (i==9)) continue;
                if ((i==9) && ((state.charAt(8) != state.charAt(7))) ) continue;
                if ((i==9) && ((state.charAt(8) != state.charAt(5))) ) continue;
                if ((i==9) && ((state.charAt(8) != state.charAt(4))) ) continue;
                if ((i==9) && ((state.charAt(7) != state.charAt(5))) ) continue;
                if ((i==9) && ((state.charAt(7) != state.charAt(4))) ) continue;
                if ((i==9) && ((state.charAt(5) != state.charAt(4))) ) continue;

                String s = state;
                String suffix = "";
                for (int kl=0;kl<k;kl++){
                    s = move(s, i);
                    suffix = suffix + Integer.toString(i);
                }
                if (s.equalsIgnoreCase("000000000")) {
                    found = true;
                    res = trans + suffix;
                    break;
                }
                else {
                    if ((queue.indexOf(s) == -1) && (!been.contains(s) )){
                        queue.add(s);
                        queueTrans.add(trans + suffix);
                        been.add(s);
                    }
                }
            }
            
        }
        
    }

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
                                                  // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
        //st. f.readLine()
        String initial = "";
        for (int j=0;j<3;j++){
            // Use StringTokenizer vs. readLine/split -- lots faster
            StringTokenizer st = new StringTokenizer(f.readLine());
            
            for (int i=0;i<3;i++){
                int a = (Integer.parseInt(st.nextToken()) % 12);
                initial = initial + Integer.toString(a);
            }
        }
        
        
        clocks cl = new clocks();
        cl.queue.add(initial);
        cl.queueTrans.add("");

        cl.search();

        for (int i=0;i<cl.res.length();i++){
            out.print(cl.res.charAt(i));
            if (i<cl.res.length() - 1) out.print(" ");
        }
        out.println();
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
  }


}
