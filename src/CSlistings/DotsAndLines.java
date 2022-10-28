package CSlistings;

import tw.StdDraw;
import tw.StdIn;
/*
Unit 1.5.19 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/

public class DotsAndLines {
    static final double radius = 5000.0;
    static int dotsNumber;
    static double probability;
    static double[][] dots;
    static double angle;

    public static void main(String[] args) {
        dotsNumber = StdIn.readInt();
        probability = StdIn.readDouble();
        StdDraw.setXscale((0 - radius), radius);
        StdDraw.setYscale((0 - radius), radius);
        dots = new double[dotsNumber][2];
        angle = (Math.PI * 2) / dotsNumber;
        for (int i = 0; i < dotsNumber; i++) {
            try {
                Thread.sleep(0x64);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double y = radius * Math.sin(angle * i);
            double x = radius * Math.cos(angle * i);
            dots[i][0] = y;
            dots[i][1] = x;
            StdDraw.point(x, y);
        }
        for (int i = 0; i < dotsNumber; i++) {
            for (int j = i; j < dotsNumber; j++) {
                if (Math.random() < probability) {
                    try {
                        Thread.sleep(0x64);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    StdDraw.line(dots[i][1], dots[i][0], dots[j][1], dots[j][0]);
                }
            }
        }
    }
}
