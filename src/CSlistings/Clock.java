package CSlistings;

import tw.StdDraw;

import java.util.Calendar;
import java.util.GregorianCalendar;
/*
Unit 1.5.32 from "Computer Science" R.Sedgewick, K.Wayne, solution.
 */

public class Clock {
    static GregorianCalendar gc;
    static double[] xHour = new double[4];
    static double[] yHour = new double[4];
    static double[] xMinute = new double[4];
    static double[] yMinute = new double[4];
    static double xSeconds = 0.0;
    static double ySeconds = 0.0;
    static int hour;
    static int minute;
    static int second;
    static final double hourArrowRadius = 0.6;
    static final double hourArrowLessRadius = 0.3;
    static final double minuteArrowRadius = 0.8;
    static final double minuteArrowLessRadius = 0.4;
    static final double secondArrowRadius = 0.9;

    public static void main(String[] args) {
        StdDraw.setYscale(-1,1);
        StdDraw.setXscale(-1,1);
        StdDraw.enableDoubleBuffering();
        while (true){
            gc = new GregorianCalendar();
            hour = gc.get(Calendar.HOUR);
            minute = gc.get(Calendar.MINUTE);
            second = gc.get(Calendar.SECOND);
            refreshHour();
            refreshMinute();
            refreshSeconds();
            StdDraw.clear();
            drawFace();
            StdDraw.filledPolygon(xHour, yHour);
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledPolygon(xMinute, yMinute);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(0,0,xSeconds,ySeconds);
            StdDraw.show();
            StdDraw.pause(1000);
        }
    }
    static void refreshHour(){
        int degrees = 90 - 30 * hour - (minute >> 1);
        double aAngle = Math.toRadians(degrees);
        double bAngle = Math.toRadians(degrees - 8);
        double cAngle = Math.toRadians(degrees + 8);
        xHour[1] = hourArrowLessRadius *  Math.cos(bAngle);
        xHour[2] = hourArrowRadius * Math.cos(aAngle);
        xHour[3] = hourArrowLessRadius * Math.cos(cAngle);
        yHour[1] = hourArrowLessRadius *  Math.sin(bAngle);
        yHour[2] = hourArrowRadius * Math.sin(aAngle);
        yHour[3] = hourArrowLessRadius * Math.sin(cAngle);
    }
    static void refreshMinute(){
        int degrees = 90 - 6 * minute;
        double aAngle = Math.toRadians(degrees);
        double bAngle = Math.toRadians(degrees - 5);
        double cAngle = Math.toRadians(degrees + 5);
        xMinute[1] = minuteArrowLessRadius *  Math.cos(bAngle);
        xMinute[2] = minuteArrowRadius * Math.cos(aAngle);
        xMinute[3] = minuteArrowLessRadius * Math.cos(cAngle);
        yMinute[1] = minuteArrowLessRadius *  Math.sin(bAngle);
        yMinute[2] = minuteArrowRadius * Math.sin(aAngle);
        yMinute[3] = minuteArrowLessRadius * Math.sin(cAngle);
    }
    static void refreshSeconds(){
        int degrees = 90 - 6 * second;
        double angle = Math.toRadians(degrees);
        xSeconds = secondArrowRadius * Math.cos(angle);
        ySeconds = secondArrowRadius * Math.sin(angle);
    }
    static void drawFace(){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(0,0,1);
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i = 0; i < 360; i += 6) {
            double angle = Math.toRadians(i);
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double x0 = 0.95 * cos;
            double y0 = 0.95 * sin;
            double x = 0.98 * cos;
            double y = 0.98 * sin;
            StdDraw.line(x0,y0,x,y);
        }
        for (int i = 0; i < 360; i += 30) {
            double angle = Math.toRadians(i);
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double x0 = 0.90 * cos;
            double y0 = 0.90 * sin;
            double x = 0.98 * cos;
            double y = 0.98 * sin;
            StdDraw.line(x0,y0,x,y);
        }
    }
}
