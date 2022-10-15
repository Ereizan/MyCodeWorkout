import java.util.Random;
import java.util.Scanner;
/*
Unit 1.4.32 from "Computer Science" R.Sedgewick, K.Wayne, solution.
 */
public class MinesweeperFieldCreator {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random(System.nanoTime());
    static int verticalLength;
    static int horizontalLength;
    static int probability;
    static boolean[][] field;

    public static void main(String[] args) {
        verticalLength = scanner.nextInt();
        horizontalLength = scanner.nextInt();
        probability = scanner.nextInt();
        field = new boolean[verticalLength][horizontalLength];
        fillField();
        paintField();
        System.out.println();
        paintNumberedField();
    }

    static void fillField(){
        for (int i = 0; i < verticalLength; i++){
            for (int j = 0; j < horizontalLength; j++){
                field[i][j] = random.nextInt(probability) == 0;
            }
        }
    }

    static void paintField(){
        for (boolean[] line : field){
            for (boolean cell : line){
                System.out.print( cell? "*" : ".");
            }
            System.out.println();
        }
    }

    static void paintNumberedField(){
        String[][] numbered = new String[verticalLength][horizontalLength];
        for (int i = 0; i < verticalLength; i++){
            for (int j = 0; j < horizontalLength; j++){
                if (field[i][j]){
                    numbered[i][j] = "*";
                } else {
                    int mineCount = 0;
                    for (int m = Math.max(0, (i - 1)); m <= Math.min((verticalLength - 1), (i + 1)); m++){
                        for (int n = Math.max(0, (j - 1)); n <= Math.min((horizontalLength - 1), (j + 1)); n++){
                            if (field[m][n]) mineCount++;
                        }
                    }
                    numbered[i][j] = Integer.toString(mineCount);
                }
            }
        }
        for (String[] line : numbered){
            for (String cell : line){
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
