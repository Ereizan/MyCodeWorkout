import java.util.Scanner;
/*
Highest standard 2022
A
*/

public class AllBills {
    static Scanner s = new Scanner(System.in);
    static int price;

    public static void main(String[] args) {
        price = s.nextInt();
        System.out.println(getSolution());
    }

    static int getSolution(){
        int trace = price / 50;
        if (trace * 50 != price) return 0;
        price = trace;
        int sum = 0;
        int border = (price >> 2) + 1;
        for (int i = 0; i < border; i++){
            sum += (price >> 1) + 1;
            price -= 4;
        }
        return sum;
    }
}
