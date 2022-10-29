package CSlistings;

import tw.StdDraw;
import tw.StdIn;
/*
Unit 1.5.21 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/
public class PolarFlower {
    static int petalsNumber;
    static double angle;


    public static void main(String[] args) {
        petalsNumber = StdIn.readInt();
        petalsNumber = (petalsNumber & 1) == 1? petalsNumber : (petalsNumber << 1);
        StdDraw.setXscale((-1), 1);
        StdDraw.setYscale((-1), 1);
        angle = (Math.PI * 2) / petalsNumber;
        for (int petalsCount = 0; petalsCount < petalsNumber; petalsCount++) {
            double petal = angle * petalsCount;
            double previousX0 = 0.0;
            double previousY0 = 0.0;
            for (double i = 0.0; i < Math.PI; i += 0.0314159265) {
                double range = Math.sin(i);
                double thisAngle = petal + ((i*2)/petalsNumber);
                double x0 = range * Math.cos(thisAngle);
                double y0 = range * Math.sin(thisAngle);
                StdDraw.line(previousX0, previousY0, x0, y0);
                previousX0 = x0;
                previousY0 = y0;
            }
        }
    }



}
