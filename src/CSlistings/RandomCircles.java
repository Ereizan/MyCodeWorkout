package CSlistings;

import tw.StdDraw;

import java.awt.*;
import java.util.Scanner;
/*
Unit 1.5.26 from "Computer Science" R.Sedgewick, K.Wayne, solution.
 */


public class RandomCircles {
    static int numberOfCircles;
    static double colorProbability;
    static double minRadix;
    static double maxRadix;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        numberOfCircles = scanner.nextInt();
        colorProbability = scanner.nextDouble();
        minRadix = scanner.nextDouble();
        maxRadix = scanner.nextDouble();
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        for (int i = 0; i < numberOfCircles; i++) {
            StdDraw.setPenColor(getRandomColor());
            StdDraw.filledCircle(Math.random(), Math.random(), getRandomRadix());
        }

    }
    static Color getRandomColor(){
        double r = Math.random();
        if (r <= colorProbability) return StdDraw.BLACK;
        return StdDraw.WHITE;
    }
    static double getRandomRadix(){
        return (Math.random()*(maxRadix - minRadix)) + minRadix;
    }
}
