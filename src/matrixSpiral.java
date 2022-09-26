
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;
    //Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
//Example 1:
//
//Input:
//[
    //[ 1, 2, 3 ],
    //[ 4, 5, 6 ],
    //[ 7, 8, 9 ]
//]
//Output: [1,2,3,6,9,8,7,4,5]
//Example 2:
//
//Input:
//[
    //[1, 2, 3, 4],
    //[5, 6, 7, 8],
    //[9,10,11,12]
//]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]

public class matrixSpiral {
    public static void main(String[] args) {
        int[] solution;
        int[][] matrix;

        String line;
        ArrayList<String> stringLine = new ArrayList<>();
        System.out.println ("Put a matrix");
        Scanner scan = new Scanner (System.in);
        while (true){
            try {
                line = scan.nextLine();
            } catch (NoSuchElementException e){
                line = "";
            }
            if (line.isEmpty()) break;
            stringLine.add(line);
            scan = new Scanner (System.in);
        }

        matrix = turnToIntArray(stringLine);
        for (int[] matrixLine : matrix){
            System.out.println (Arrays.toString(matrixLine));
        }
        System.out.println ();
        solution = getSolution(matrix);
        System.out.println (Arrays.toString(solution));
    }

        static int[][] turnToIntArray (ArrayList<String> stringLine){
            String[] check = stringLine.get(0).split(" ");
            int[][] intArray = new int[stringLine.size()][check.length];
            int count = 0;
            for (String line : stringLine){
                String[] strings = line.split(" ");
                int[] mod = new int[strings.length];
                int number = 0;
                for (String string : strings){
                    mod[number++] = Integer.parseInt(string);
                }
                intArray[count++] = mod;
            }
            return intArray;
        }
        static int[] getSolution (int[][] matrix){
            int lengthSolution = matrix.length * matrix[0].length;
            int[] solution = new int[lengthSolution];
            int counterX = matrix[0].length - 1;
            int counterY = matrix.length - 1;
            int taperX = 0;
            int taperY = 0;
            int vector = 1;

            for (int i = 0; i < lengthSolution; i++){
                solution[i] = matrix[taperY][taperX];
                switch (vector) {
                    case 1 :
                        if (taperX < counterX){
                            taperX++;
                        } else {
                            counterX--;
                            taperY++;
                            vector++;
                        }
                        break;
                    case 2 :
                        if (taperY < counterY){
                            taperY++;
                        } else {
                            counterY--;
                            taperX--;
                            vector++;
                        }
                        break;
                    case 3 :
                        if (taperX > matrix[0].length - counterX - 2){
                            taperX--;
                        } else {
                            taperY--;
                            vector++;
                        }
                        break;
                    case 4 :
                        if (taperY > matrix.length - counterY - 1){
                            taperY--;
                        } else {
                            taperX++;
                            vector = 1;
                        }
                        break;
                }
            }

            return solution;

        }
    }

