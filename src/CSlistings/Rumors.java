package CSlistings;

import java.util.Random;
import java.util.Scanner;
/*
Unit 1.4.30 from "Computer Science" R.Sedgewick, K.Wayne, solution.
 */
public class Rumors {
    public static void main(String[] args) {
        boolean[] peopleKnow;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(System.nanoTime());
        int knowAboutParty = 0;
        int doNotKnow = 0;

        peopleKnow = new boolean[scanner.nextInt()];
        // peopleKnow [0] - it's Bob!
        peopleKnow[0] = true;
        int previousIndex = 0;
        int myIndex = 0;
        while (true){
            int index = random.nextInt(peopleKnow.length);
            if (index == previousIndex || index == myIndex) continue;
            if (peopleKnow[index]) break;
            peopleKnow[index] = true;
            previousIndex = myIndex;
            myIndex = index;
        }
        for (boolean b : peopleKnow){
            if (b) knowAboutParty++;
            else doNotKnow++;
        }
        System.out.println("People, who knows about Alice's party: " + knowAboutParty);
        System.out.println("People, who didn't know: "+ doNotKnow);
    }
}
