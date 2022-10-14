import java.util.Scanner;
/*
Unit 1.4.29 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/

public class AdamarsMatrix {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int arrRange = s.nextInt();
        boolean[][] matrix = new boolean[arrRange][arrRange];
        matrix[0][0] = true;
        for (int n = 1; n < arrRange; n *= 2){
            for (int i = n; i < n * 2; i++){
                for (int x = 0; x < n; x++){
                    matrix[x][i] = matrix[x][i-n];
                    matrix[i][x] = matrix[i-n][x];
                }
                for (int m = n; m < n * 2; m++){
                    matrix[i][m] = !matrix[i-n][m-n];
                }
            }    
        }
        for (boolean[] line : matrix){
            System.out.println();
            for (boolean b : line){
                String str = b? "T" : "F" ;
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}