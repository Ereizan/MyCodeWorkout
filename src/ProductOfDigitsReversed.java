import java.util.Arrays;
import java.util.Scanner;
/*
Trying to optimize speed. -0.016 sec. compared to original code. Yoo-hoo!(Sarcasm)
 */
public class ProductOfDigitsReversed {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int dividend = s.nextInt();
        if (dividend < 2) System.out.println((dividend == 1)? 1 : 10);
        else {
            int [] divs = new int[30];
            int count = 0;
            int dualCount = 0;
            while ((dividend & 1) == 0){
                dualCount++;
                dividend = dividend >> 1;
                if (dualCount == 3){
                    divs[count++] = 8;
                    dualCount = 0;
                }
            }
            String line = Integer.toString(dividend);
            char[] chars = line.toCharArray();
            while (chars[chars.length - 1] == '5'){
                divs[count++] = 5;
                dividend /= 5;
                chars = Integer.toString(dividend).toCharArray();
            }
            boolean containsThree = false;
            while (true){
                int sum = 0;
                for (char ch : chars){
                    sum += Character.digit(ch, 10);
                }
                char[] charsSum = Integer.toString(sum).toCharArray();
                sum = 0;
                for (char ch : charsSum){
                    sum += Character.digit(ch, 10);
                }
                if (sum == 9){
                    divs[count++] = 9;
                    dividend /= 9;
                    chars = Integer.toString(dividend).toCharArray();
                } else if (sum == 3){
                    containsThree = true;
                    dividend /= 3;
                    chars = Integer.toString(dividend).toCharArray();
                    break;
                } else break;
            }
            while (true){
                while (chars.length > 1){
                    int first = Character.digit(chars[0], 10);
                    for ( int i = 1; i < chars.length; i++){
                        first *= 3;
                        first += Character.digit(chars[i], 10);
                    }
                    chars = Integer.toString(first).toCharArray();
                }
                int sum = Character.digit(chars[0], 10);
                if (sum == 7){
                    divs[count++] = 7;
                    dividend /= 7;
                    chars = Integer.toString(dividend).toCharArray();
                } else break;
            }
            if (containsThree){
                if (dualCount > 0){
                    divs[count++] = 6;
                    dualCount--;
                } else {
                    divs[count++] = 3;
                }
            }
            if (dualCount == 2) {
                divs[count++] = 4;
                dualCount = 0;
            }
            if (dualCount == 1){
                divs[count++] = 2;
            }

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
