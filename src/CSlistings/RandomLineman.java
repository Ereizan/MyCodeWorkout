package CSlistings;

import java.util.Random;
import java.util.Scanner;
/*
Unit 1.4.36 from "Computer Science" R.Sedgewick, K.Wayne, solution.
 */
public class RandomLineman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random =new Random(System.nanoTime());

        System.out.println("Put a number, which will be length and height of field, and also be number of linemen.");
        int number = scanner.nextInt();
        boolean[][] field = new boolean[number][number];
        int stepsCount = 0;
        int[][] linemen = new int[number][2];
        for (int i = 0; i < number; i++){
            linemen[i][0] = number / 2;
            linemen[i][1] = number / 2;
        }
        while (fieldIsNotEnd(field)){
            for (int i = 0; i < number; i++){
                field[linemen[i][0]][linemen[i][1]] = true;
                while (true){
                    if (random.nextBoolean()){
                        if (random.nextBoolean()){
                            if (linemen[i][0] == number - 1) continue;
                            linemen[i][0]++;
                        } else {
                            if (linemen[i][0] == 0) continue;
                            linemen[i][0]--;
                        }
                    } else {
                        if (random.nextBoolean()){
                            if (linemen[i][1] == number - 1) continue;
                            linemen[i][1]++;
                        } else {
                            if (linemen[i][1] == 0) continue;
                            linemen[i][1]--;
                        }
                    }
                    break;
                }
            }
            stepsCount++;
        }
        System.out.println("The number of steps it took to go around the field: " + stepsCount);
    }
    static boolean fieldIsNotEnd(boolean[][] field){
        for (boolean[] line : field){
            for (boolean b : line){
                if (!b) return true;
            }
        }
        return false;
    }
}
