/*
 https://acm.timus.ru/problem.aspx?space=1&num=1123
*/
import java.util.Scanner;

public class Salary {
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        String str = s.nextLine();
        String [] strAr = str.split("");
        int len = Integer.valueOf(str.length()).shortValue();
        byte[] num = new byte[len];
        int n = 0;
        for (String x : strAr){
            num[n++] = 	Byte.parseByte(x);
        }

        int m = len - 1;
        int r = 0;
        boolean own = false;
        while (m > r){
            if (num[m] == num[r]){
                m--;
                r++;
                continue;
            }
            own = num[m] > num[r];
            num[m--] = num[r++];
        }
        if (own) {
            while (num[r] == 9){
                num[r++] = 0;
                num[m--] = 0;
            }
            num[r]++;
            if (r != m){
                num[m]++;
            }
        }
        for (byte bup : num){
            System.out.print(bup);
        }
    }
}
