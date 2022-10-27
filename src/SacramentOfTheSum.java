import java.util.Scanner;
import java.util.Arrays;
/*
https://acm.timus.ru/problem.aspx?space=1&num=1021
*/
public class SacramentOfTheSum {
    static int n;
    static int m;
    static int[] first;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        n = Integer.parseInt(s.nextLine());
        first = new int[n];
        for (int i = 0; i < n; i++){
            first[i] = Integer.parseInt(s.nextLine());
        }
        m = Integer.parseInt(s.nextLine());
        boolean haveSolution = false;
        for (int i = 0; i < m; i++){
            int newOne = Integer.parseInt(s.nextLine());
            int complete = 10000 - newOne;
            int index = Arrays.binarySearch(first, complete);
            haveSolution = haveSolution || index >= 0;
        }
        if (haveSolution){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
