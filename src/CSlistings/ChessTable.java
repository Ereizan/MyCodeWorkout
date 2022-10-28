package CSlistings;

import tw.StdDraw;
import tw.StdIn;
/*
Unit 1.5.18 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/

public class ChessTable {
    static final double fieldSize = 10000.0;
    static int number;
    static double radius;

    public static void main(String[] args) {
        number = StdIn.readInt();
        StdDraw.setXscale(0, fieldSize);
        StdDraw.setYscale(0, fieldSize);
        radius = fieldSize / (number << 1);
        double height;
        double weight;
        for (int i = 0; i < number; i++){
            height = radius * ((i << 1) + 1);
            for (int j = 0; j < number; j++){
                weight = radius * ((j << 1) + 1);
                setColor(i, j);
                StdDraw.filledSquare(weight, height, radius);
            }
        }
    }
    static void setColor(int i, int j){
        boolean evenY = (i & 1) == 0;
        boolean evenX = (j & 1) == 0;
        if (evenY){
            if (evenX){
                StdDraw.setPenColor(StdDraw.RED);
            } else {
                StdDraw.setPenColor(StdDraw.BLACK);
            }
        } else {
            if (evenX){
                StdDraw.setPenColor(StdDraw.BLACK);
            } else {
                StdDraw.setPenColor(StdDraw.RED);
            }
        }
    }
}
