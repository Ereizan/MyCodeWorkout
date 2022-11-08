package CSlistings;

import tw.StdAudio;
import tw.StdDraw;
/*
Listing 1.5.6 from "Computer Science" R.Sedgewick, K.Wayne, + task 1.5.34 + task 1.5.35 + some additions
 */
public class BouncingBall {
    public static void main(String[] args) {
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        double rx = 0.480, ry = 0.860;
        double vx = 0.015, vy = 0.023;
        double radius = 0.05;
        double previousRx = rx;
        double previousRy = ry;

        StdDraw.clear(StdDraw.LIGHT_GRAY);

        do {
            if (Math.abs(rx + vx) > 1.0 - radius) {
                vx = -vx;
                wallTic();
            }
            if (Math.abs(ry + vy) > 1.0 - radius) {
                vy = -vy;
                floorTic();
            }
            vy -= 0.00196133;

            rx += vx;
            ry += vy;

            if (StdDraw.isMousePressed()) {
                rx = StdDraw.mouseX();
                ry = StdDraw.mouseY();
                vx = 0.015;
                vy = 0.023;
            }

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(previousRx, previousRy, radius);

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(rx, ry, radius);

            StdDraw.show();

            StdDraw.pause(20);
            previousRx = rx;
            previousRy = ry;
        } while (ry > -1.0);
    }
    static void wallTic(){
        int pitch = -8;
        double hz = 440 * Math.pow(2, pitch / 12.0);
        int n = (int) (StdAudio.SAMPLE_RATE * 0.05);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE) * 0.3;
        }
        StdAudio.play(a);
    }
    static void floorTic(){
        int pitch = -16;
        double hz = 440 * Math.pow(2, pitch / 12.0);
        int n = (int) (StdAudio.SAMPLE_RATE * 0.05);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE) * 0.3;
        }
        StdAudio.play(a);
    }
}