import java.util.Arrays;
import java.util.Scanner;

public class ProductOfDigitsNowBananas {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int dividend = s.nextInt();
        if (dividend < 2) System.out.println((dividend == 1)? 1 : 10);
        else {
            int [] divs = new int[30];
            int count = 0;
            String str = Integer.toString(dividend);
            int milt = 10;
            for (int i = 9; i > 1; i--){
                int div = Integer.parseInt(str, (i + 1));
                if (div == 1) break;
                milt = i;
                str = Integer.toString(div, i);
                while (str.charAt(str.length() - 1) == '0'){
                    str = String.copyValueOf(str.toCharArray(), 0, str.length() - 1);
                    divs[count++] = i;
                }
            }
            dividend = Integer.parseInt(str, milt);
            if (dividend > 1) {
                System.out.println(-1);
            } else {
                Arrays.sort(divs);
                for (int i = 30 - count; i < 30; i++){
                    System.out.print(divs[i]);
                }
                System.out.println();
            }
        }
    }
}

