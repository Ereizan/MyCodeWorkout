import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
/*
Unit 1.4.26 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/

public class PlayerTest {
    static Scanner s = new Scanner(System.in);
    static Random r = new Random(System.nanoTime());
    static int[] playList;
    
    public static void main(String[] args) {
        int arrRange = s.nextInt();
        int shuffleRange = s.nextInt();
        playList = new int[arrRange];
        int notRowCount = 0;
        
        for (int n = 0; n < shuffleRange; n++){
            for (int i = 0; i < arrRange; i++){
                playList[i] = i;
            }
            shuffle();
            notRowCount++;
            for (int i = 0; i < arrRange - 1; i++){
                if (playList[i] == playList[i + 1] - 1){
                    notRowCount--;
                    break;
                }  
            }
        }
        double probability = (double) notRowCount / (double) shuffleRange;
        System.out.printf("%.10f", probability);
    }
    static void shuffle(){
        int[] arrayCopy = Arrays.copyOf(playList, playList.length);
        for (int i = 0; i < playList.length; i++){
            int rand = r.nextInt(arrayCopy.length);
            playList[i] = arrayCopy[rand];
            int[] newCopy = new int[arrayCopy.length - 1];
            for (int j = 0; j < newCopy.length; j++){
                newCopy[j] = (j < rand)? arrayCopy[j] : arrayCopy[j + 1];
            }
            arrayCopy = Arrays.copyOf(newCopy, newCopy.length);
        }
    }
}