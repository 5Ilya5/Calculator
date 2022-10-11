import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Parser {
    public static String calculate(String s) {
        ArrayList<Double> arr = new ArrayList<Double>();
        ArrayList<String> zn = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(s, "+*/-");
        StringTokenizer st1 = new StringTokenizer(s, "1234567890");
        try {
            while (st.hasMoreTokens()) {
                arr.add(Double.parseDouble(st.nextToken()));
            }
            while (st1.hasMoreTokens()) {
                zn.add(st1.nextToken());
            }
            for (int j = 0; j < zn.size(); j++) {
                if(zn.get(j).length()>1) return "can't calculate";
                if (zn.get(j).equals("*")) {
                    arr.set(j, arr.get(j) * arr.get(j + 1));
                    arr.remove(j + 1);
                    zn.remove(j);
                    j--;
                } else {
                    if (zn.get(j).equals("/")) {
                        arr.set(j, arr.get(j) / arr.get(j + 1));
                        arr.remove(j + 1);
                        zn.remove(j);
                        j--;
                    }
                }
            }
            for (int j = 0; j < zn.size(); j++) {
                if (zn.get(j).equals("+")) {
                    arr.set(j, arr.get(j) + arr.get(j + 1));
                    arr.remove(j + 1);
                    zn.remove(j);
                    j--;
                } else {
                    if (zn.get(j).equals("-")) {
                        arr.set(j, arr.get(j) - arr.get(j + 1));
                        arr.remove(j + 1);
                        zn.remove(j);
                        j--;
                    }
                }
            }
            return arr.get(0).toString();
        } catch (Exception e) {
            return "can’t calculate";
        }
    }
 
    public static void main(String[] args) {
        String s = new String();
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            while((s = br.readLine())!=null){
                pw.println(s+"="+Parser.calculate(s));
            }
            pw.close();
        }catch(Exception e){
            System.out.print(e);
        }
    }
}