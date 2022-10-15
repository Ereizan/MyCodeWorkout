package CSlistings;

import java.util.Random;
import java.util.Scanner;
/*
Unit 1.4.38 from "Computer Science" R.Sedgewick, K.Wayne, solution.
 */
public class SameBirthDays {
    public static void main(String[] args) {
        Random random = new Random(System.nanoTime());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set number of checks");
        int number = scanner.nextInt();
        int[] checks = new int[number];
        for (int i = 0; i < number; i++){
            int count = 0;
            boolean[] personsBD = new boolean[365];
            while (true){
                count++;
                int randomIndex = random.nextInt(365);
                if (personsBD[randomIndex]) break;
                personsBD[randomIndex] = true;
            }
            checks[i] = count;
        }
        int sum = 0;
        for (int check : checks){
            sum += check;
        }
        int solution =  (int) Math.round((double) sum / number);
        System.out.printf("Based on the results of %d checks, on average it takes %d people to get a pair of people " +
                "with the same birthday. \n", number, solution);
    }
}
