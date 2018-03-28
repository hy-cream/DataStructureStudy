import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        String[] str = line.split("");
        boolean flag = false;
        String result = "";
        int max = -9999;

        for (int i = 0; i < line.length();i++){
            if(!flag && str[i].compareTo("0") == 0){
                continue;
            }else if (flag && isNumeric(str[i])){
                result+=str[i];
            }else if(!flag && isNumeric(str[i])){
                result+=str[i];
                flag = true;
            } else {
                if (result != "" && max < Integer.parseInt(result)){
                    max = Integer.parseInt(result);
                }
                result = "";
                flag = false;
            }
        }

        System.out.println(max);
    }
    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }
}
