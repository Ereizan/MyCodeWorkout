package CSlistings;

import java.util.Arrays;
import java.util.Scanner;
/*
Unit 1.4.16 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/


public class SieveEratosthenes {
    public static void main(String[] args) {
      int[][] x= {{10, 5}, {3}};
      int[][] y= {{10, 5}, {3}};
      System.out.println(isEqArrays(x, y));
      for (int[] r : twoDimCopy(y)){
            System.out.println(Arrays.toString(r));         
      }
      Scanner s = new Scanner(System.in);
      int n = s.nextInt();
      boolean[][] a = new boolean[n+1][n+1];
      for (int i = 1; i <= n; i++){
          for (int j = 1; j <= n; j++){
              a[i][j] = true;
          }
      }
      for (int i = 1; i <= n; i++){
          for (int j = 1; j <= n; j++){
              if (a[i][j]){
                  for (int m = 2; m <= n/Math.max(i, j); m++){
                      a[i * m][j * m] = false;
                  }
                  
              }
          }
      }

      System.out.println();
      for (boolean[] line : a){
          for (boolean b : line){
              if (b) System.out.print("S");
              else System.out.print("o");
          }
          System.out.println();
      }
    }
    
    static int[][] twoDimCopy(int[][] m){
        int[][] z = new int[m.length][];
        int it = 0;
        for (int[] ar : m){
            z[it++] = Arrays.copyOf(ar, ar.length);
        }
        return z;
    }
    
    static boolean isEqArrays(int[][] x, int[][] y){
        if (x.length != y.length) return false;
        for (int i = 0; i < x.length; i++){
          if (!Arrays.equals(x[i], y[i])) return false;
        }
        return true;
    }
    
    // unit 1.4.19
    static boolean[][] logicMultiplication(boolean[][] a, boolean[][] b){
        boolean outOfFormat = a.length != b.length;
        for (boolean[] line : a){
            outOfFormat = outOfFormat || line.length != a.length;
        }
        for (boolean[] line : b){
            outOfFormat = outOfFormat || line.length != b.length;
        }
        if (outOfFormat){
            System.out.println("Error! Arrays ain't equals!");
            return a;
        }
        boolean[][] sol = new boolean[a.length][a.length];
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a.length; j++){
                sol[i][j] = a[i][j] && b[i][j];
            }
        }
        return sol;
    }
    
}










