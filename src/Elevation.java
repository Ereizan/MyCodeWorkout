import java.util.Random;
import java.util.Scanner;
/*
Unit 1.4.23 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/

public class Elevation {
    static Scanner s = new Scanner(System.in);
    static Random r = new Random(System.nanoTime());
    
    public static void main(String[] args) {
        
        int randRange = s.nextInt();
        int arrRange = s.nextInt();
        int[] array = new int[arrRange];
        for (int i = 0; i < arrRange; i++){
            array[i] = r.nextInt(randRange) + 1;
        }
        
        //solution
        int[][] checker = new int[randRange + 1][3];
        int ranger = 0;
        int ender = 0;
        boolean tumbler = false;
        for (int i = 0; i < array.length - 1; i++){
            if (array[i] == array[i + 1]){
                ranger++;
                if (!tumbler){
                    tumbler = true;
                    ender = i;
                } 
            } else if (tumbler) {
                ranger++;
                if (checker[array[i]][0] < ranger){
                    checker[array[i]][0] = ranger;
                    checker[array[i]][1] = ender;
                    checker[array[i]][2] = i;
                }
                tumbler = false;
                ranger = 0;
            }
        }
        int max = 0;
        int index = 0;
        for (int i = 0; i < checker.length; i++){
            if (checker[i][0] > max){
                max = checker[i][0];
                index = i;
            } 
        }
        System.out.printf("Maximum elevation of range %d from index %d to index %d by number %d \n",
                max, checker[index][1], checker[index][2], index);
    }
}