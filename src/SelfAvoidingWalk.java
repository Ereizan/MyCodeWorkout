import java.util.Random;
import java.util.Scanner;
/*
Listing 1.4.4 from "Computer Science" R.Sedgewick, K.Wayne, in my own interpretation.
 */

public class SelfAvoidingWalk {
    static boolean[][] area;
    static Scanner scanner = new Scanner(System.in);
    static int y = 0;
    static int x = 0;
    static boolean deadEnd = false;
    static boolean winEnd = false;
    static Random random = new Random(System.nanoTime());

    public static void main(String[] args) {
        System.out.println("Set length of Area");
        areaInitialization(scanner.nextInt());
        move();
        if (deadEnd){
            System.out.println("It's dead end!");
        }
        if (winEnd){
            System.out.println("It's escape!");
        }
        paintArea();
    }
    public static void areaInitialization(int areaLength){
        area = new boolean[areaLength][areaLength];
        for (int i = 0; i < areaLength; i++){
            for (int j = 0; j < areaLength; j++){
                area[i][j] = false;
            }
        }
        y = areaLength / 2;
        x = areaLength / 2;
    }
    public static void move(){
        while (true){
            area[y][x] = true;
            int a = x;
            int b = y;
            if (random.nextBoolean()){
                if (random.nextBoolean()){
                    x++;
                } else x--;
            } else {
                if (random.nextBoolean()){
                    y++;
                } else y--;
            }
            if (x == 0 || y == 0 || x == area.length - 1 || y == area.length - 1){
                winEnd = true;
                area[y][x] = true;
                break;
            }
            if (area[b + 1][a] && area[b - 1][a] && area[b][a + 1] && area [b][a - 1]) {
                deadEnd = true;
                break;
            }
            if (area[y][x]){
                x = a;
                y = b;
            }
        }
    }

    public static void paintArea(){
        System.out.println();
        for (boolean[] booleans : area){
            for (boolean cell : booleans){
                if (cell) {
                    System.out.print("+");
                } else {
                    System.out.print("o");
                }
            }
            System.out.println();
        }
    }
}
