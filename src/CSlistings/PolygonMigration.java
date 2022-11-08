package CSlistings;

import tw.StdDraw;

import java.util.Arrays;
import java.util.Scanner;
/*
Polygons migrates!
 */

public class PolygonMigration {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StdDraw.setXscale(-10,10);
        StdDraw.setYscale(-10,10);
        StdDraw.enableDoubleBuffering();
        double[] x0 = fillIt();
        double[] y0 = fillIt();
        double[] x1 = fillIt();
        double[] y1 = fillIt();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledPolygon(x0, y0);
        StdDraw.show();
        StdDraw.pause(1000);
        migrate(x0, y0, x1, y1, 3000);
        StdDraw.clear();
        StdDraw.filledPolygon(x1, y1);
        StdDraw.show();

    }
    static double[] fillIt(){
        double[] array = new double[0];
        while (true){
            String s = scanner.nextLine();
            if ((!Character.isDigit(s.charAt(0))) && s.charAt(0) != '-') break;
            double n = Double.parseDouble(s);
            array = Arrays.copyOf(array, array.length + 1);
            array[array.length - 1] = n;
        }
        return array;
    }
    static void migrate(double[] x0, double[] y0, double[] x1, double[] y1, int speedMS){
        double[] speedX = new double[x0.length];
        for (int i = 0; i < speedX.length; i++) {
            speedX[i] = Math.abs(x0[i] - x1[i]) / speedMS;
            if (x0[i] > x1[i]) speedX[i] = -speedX[i];
        }
        double[] speedY = new double[y0.length];
        for (int i = 0; i < speedY.length; i++) {
            speedY[i] = Math.abs(y0[i] - y1[i]) / speedMS;
            if (y0[i] > y1[i]) speedY[i] = -speedY[i];
        }
        for (int i = 0; i < speedMS; i++) {
            for (int j = 0; j < x0.length; j++) {
                x0[j] += speedX[j];
            }
            for (int j = 0; j < y0.length; j++) {
                y0[j] += speedY[j];
            }
            StdDraw.clear();
            StdDraw.filledPolygon(x0,y0);
            StdDraw.show();
            StdDraw.pause(1);
        }
    }
}
