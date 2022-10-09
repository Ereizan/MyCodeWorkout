import java.util.ArrayList;
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
    static ArrayList<Integer[]> path = new ArrayList<>();

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
        getGraph();
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
        addPath(y, x);
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
                addPath(y, x);
                break;
            }
            if (area[b + 1][a] && area[b - 1][a] && area[b][a + 1] && area [b][a - 1]) {
                deadEnd = true;
                break;
            }
            if (area[y][x]){
                x = a;
                y = b;
                continue;
            }
            addPath(y, x);
        }
    }
    public static void addPath(int y, int x){
        Integer[] step = {y, x};
        path.add(step);
    }

    public static void paintArea(boolean[][] area){
        System.out.println();
        for (boolean[] booleans : area){
            for (boolean cell : booleans){
                if (cell) {
                    System.out.print("o");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    static void getGraph(){
        int graphLength = area.length * 3;
        boolean[][] graph = new boolean[graphLength][graphLength];
        for (int i = 0; i < graphLength; i++){
            for (int j = 0; j < graphLength; j++){
                graph[i][j] = false;
            }
        }
        for (int n = 0; n < graphLength; n++){
            graph[n][0] = true;
            graph[n][graphLength - 1] = true;
            graph[0][n]= true;
            graph[graphLength - 1][n] = true;
        }
        for (int m = 0; m < path.size(); m++){
            int y = path.get(m)[0] * 3 + 1;
            int x = path.get(m)[1] * 3 + 1;
            graph[y][x] = true;
            if (m == 0) continue;
            if (path.get(m)[0] < path.get(m - 1)[0]){
                graph[y+1][x] = true;
                graph[y+2][x] = true;
            }
            if (path.get(m)[0] > path.get(m - 1)[0]){
                graph[y-1][x] = true;
                graph[y-2][x] = true;
            }
            if (path.get(m)[1] < path.get(m - 1)[1]){
                graph[y][x+1] = true;
                graph[y][x+2] = true;
            }
            if (path.get(m)[1] > path.get(m - 1)[1]){
                graph[y][x-1] = true;
                graph[y][x-2] = true;
            }
        }
        paintArea(graph);
    }
}
