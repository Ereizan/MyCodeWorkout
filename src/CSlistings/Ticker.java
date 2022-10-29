package CSlistings;

import tw.StdDraw;
import tw.StdIn;
/*
Unit 1.5.22 from "Computer Science" R.Sedgewick, K.Wayne, solution.
*/
public class Ticker {
    public static void main(String[] args) {
        String string = StdIn.readLine();
        int speed = StdIn.readInt();
        int length = string.length() * (120 / speed);
        speed = 6000 / (speed * length);
        StdDraw.setXscale(0, length);
        StdDraw.setYscale(0, length);
        int y = length >> 1;
        StdDraw.enableDoubleBuffering();
        while (true){
            for (int i = length << 1; i >= -length; i--) {
                StdDraw.clear();
                StdDraw.text(i,y, string);
                StdDraw.show();
                StdDraw.pause(speed);
            }
        }

    }
}
