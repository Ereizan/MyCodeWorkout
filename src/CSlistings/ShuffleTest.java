package CSlistings;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
/*
Unit 1.4.24 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/

public class ShuffleTest {
    static Scanner s = new Scanner(System.in);
    static Random r = new Random(System.nanoTime());
    static int[] array;
    
    public static void main(String[] args) {
        int arrRange = s.nextInt();
        int shuffleRange = s.nextInt();
        int[][] result = new int[arrRange][arrRange];
        array = new int[arrRange];
        for (int n = 0; n < shuffleRange; n++){
            for (int i = 0; i < arrRange; i++){
                array[i] = i;
            }
            shuffle();
            for (int i = 0; i < arrRange; i++){
                result[i][array[i]]++;
            }
        }
        
        for (int[] line : result){
            for (int x : line){
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Check value = " + (double) (shuffleRange / arrRange));
    }
    static void shuffle(){
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++){
            int rand = r.nextInt(arrayCopy.length);
            array[i] = arrayCopy[rand];
            int[] newCopy = new int[arrayCopy.length - 1];
            for (int j = 0; j < newCopy.length; j++){
                newCopy[j] = (j < rand)? arrayCopy[j] : arrayCopy[j + 1];
            }
            arrayCopy = Arrays.copyOf(newCopy, newCopy.length);
        }
    }
}