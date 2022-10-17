package CSlistings;
import tw.StdIn;
import tw.StdOut;
/*
I just spent 2 hours figuring out how to include a library from the book.
The JAR file from the book's site did not allow classes to be used in internal packages, only by default.
I had to repack JAR in my own way.
Sometimes programming is just a couple of hours of butt-hurt
 */

public class test {
    public static void main(String[] args) {
        int r = StdIn.readInt();
        StdOut.println(r);

    }

}
