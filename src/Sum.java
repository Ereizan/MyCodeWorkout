import java.util.Scanner;
/*
https://acm.timus.ru/problem.aspx?space=1&num=1068
*/
public class Sum {
    static int n;
    static int sum;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        n = s.nextInt();
        sum = Math.abs(n);
        sum = (sum * (sum + 1)) >> 1;
        sum = n > 0? sum : 1 - sum;
        System.out.println(sum);
    }
}
