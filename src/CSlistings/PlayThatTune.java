package CSlistings;

import tw.StdAudio;
import tw.StdIn;
/*
Listing 1.5.7 from "Computer Science" R.Sedgewick, K.Wayne, + task 1.5.23 + plus some additions.
 */
public class PlayThatTune {

    public static void main(String[] args) {
        crescendo(StdIn.readInt());

        while (!StdIn.isEmpty()) {
            int pitch = StdIn.readInt();
            double volume = StdIn.readDouble();
            int temp = StdIn.readInt();
            double duration = StdIn.readDouble();
            double hz = 440 * Math.pow(2, pitch / 12.0);
            int n = (int) (StdAudio.SAMPLE_RATE * duration / temp);
            double[] a = new double[n+1];
            for (int i = 0; i <= n; i++) {
                a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE) * volume;
            }
            StdAudio.play(a);
        }
    }
    static void crescendo(int octave){
        for (int i = 0; i < 8; i++) {
            double volume = (i + 1) * 0.01;
            int temp = 2;
            double duration = 3;
            for (int j = 0; j < 3; j++) {
                int pitch = -(2 * j + 1) - 8 * octave;
                double hz = 440 * Math.pow(2, pitch / 12.0);
                int n = (int) (StdAudio.SAMPLE_RATE * duration / temp);
                double[] a = new double[n+1];
                for (int f = 0; f <= n; f++) {
                    a[f] = Math.sin(2 * Math.PI * f * hz / StdAudio.SAMPLE_RATE) * volume;
                }
                StdAudio.play(a);
            }
        }
    }
}
