import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
/*
https://acm.timus.ru/problem.aspx?space=1&num=1015
*/

public class TestTheDifference {
    static Scanner s = new Scanner(System.in);
    static int diceAmount;
    static ArrayList<Cube> cubs = new ArrayList<>();

    public static void main(String[] args) {
        
        diceAmount = Integer.parseInt(s.nextLine());
        for (int i = 0; i < diceAmount; i++){
            String[] nextDice = s.nextLine().split(" ");
            int[] arrayTest = new int[6];
            for (int j = 0; j < 6; j++){
                arrayTest[j] = Integer.parseInt(nextDice[j]);
            }
            Cube cube = new Cube(arrayTest, i + 1);
            if (containsCube(cube)) {
                addIndex(cube);
            } else {
                cubs.add(cube);
            }
        }
        System.out.println(cubs.size());
        for (Cube cube : cubs){
            for (Integer index: cube.getIndexes()){
                System.out.print(index.toString() + " ");
            }
            System.out.println();
        }
    }

    private static void addIndex(Cube newCube) {
        for (Cube cube: cubs){
            if (cube.equals(newCube)) {
                cube.addToIndexes(newCube.getIndexes().get(0));
                break;
            }
        }
    }

    static boolean containsCube(Cube newCube){
        for (Cube cube: cubs){
            if (cube.equals(newCube)) return true;
        }
        return false;
    }

}
class Cube {
    private final int[][] pairs;
    private final ArrayList<Integer> indexes = new ArrayList<>();

    public ArrayList<Integer> getIndexes() {
        return indexes;
    }
    public void addToIndexes(int index){
        indexes.add(index);
    }

    public int[][] getPairs() {
        return pairs;
    }

    public Cube(int[] array, int index){
        int[][] pairs = new int[3][2];
        pairs[0] = new int[]{array[0], array[1]};
        pairs[1] = new int[]{array[2], array[4]};
        pairs[2] = new int[]{array[3], array[5]};
        this.pairs = pairs;
        indexes.add(index);
    }

    public boolean equals(Cube newCube){
        int spin = 0;
        int count = 0;
        int rotate = 0;
        for (int[] pair: newCube.getPairs()){
            if (pairEquality(pair, pairs[count++])) rotate++;
            if (spinEquality(pair)) spin++;
            if (containsPair(pair)) continue;
            return false;
        }
        spin += (rotate == 1)? 1 : 0;
        return (spin & 1) == 1;
    }

    private boolean spinEquality(int [] pair){
        for (int[] myPair : pairs){
            if (pairEquality(pair, myPair)) {
                return pair[0] == myPair[0];
            }
        }
        return false;
    }
    private boolean containsPair(int [] pair){
        for (int[] myPair : pairs){
            if (pairEquality(pair, myPair)) return true;
        }
        return false;
    }

    private boolean pairEquality(int[] newPair, int[] myPair){
        return      (myPair[0] == newPair[0] && myPair[1] == newPair[1])
                || (myPair[0] == newPair[1] && myPair[1] == newPair[0]);
    }

}
