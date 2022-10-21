import java.util.Scanner;
/*
https://acm.timus.ru/problem.aspx?space=1&num=1014

*/
public class  ProductOfDigits {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int dividend = s.nextInt();
        if (dividend < 2) System.out.println((dividend == 1)? 1 : 10);
        else {
            long [] divs = new long[30];
            int count = 0;
            label:
            for (int i = 9; i > 1; i--){
                while (true){
                    if (dividend % i == 0){
                        dividend /= i;
                        divs[count++] = i;
                        if (dividend == 1) break label;
                    } else break;
                }
            }
            if (dividend != 1) System.out.println(-1);
            else {
                long sum = 0;
                for (int i = 0; i < count; i++){
                    for (int j = 0; j < i; j++){
                        divs[i] *= 10;
                    }
                    sum += divs[i];
                }
                System.out.println(sum);
            }
        }
    }
}
