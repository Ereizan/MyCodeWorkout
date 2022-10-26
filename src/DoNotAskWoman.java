import java.util.Scanner;
/*
https://acm.timus.ru/problem.aspx?space=1&num=1104
*/
public class DoNotAskWoman {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[] digits = s.nextLine().toCharArray();
        int sol = 0;
        label:
        for (int i = 2; i < 37; i++){
            int sum = 0;
            for (char ch : digits){
                int digit = Character.digit(ch, i);
                if (digit == -1) continue label;
                sum += digit;
            }
            if (sum % (i - 1) == 0){
                sol = i;
                break;
            }
        }
        if (sol == 0){
            System.out.println("No solution.");
        } else {
            System.out.println(sol);
        }
    }
}
