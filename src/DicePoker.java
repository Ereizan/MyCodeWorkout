import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/*
Just home version of Dice Poker (a.k.a. "Yahtzee", but with some different rules) for console
 */
public class DicePoker {
    static int count = 0;
    static final int[] dice = new int[5];
    static final Random random = new Random(System.nanoTime());
    static Scanner scanner = new Scanner(System.in);
    static final int[] check = new int[7];
    static boolean finalTurn = false;

    public static void main(String[] args) {
        System.out.println("It's a dice poker! Press enter to your first drop");
        while (true){
            int playCount = 0;
            int AICount = 0;
            while (true){
                getReady();
                dropAll();
                playersDecide();
                if (!finalTurn) combinationChecking();
                else {
                    System.out.println("Your combination is " + finalCombinationCheck());
                }
                playCount += getCount();
                System.out.println();
                System.out.println("Your count now is " + playCount);
                System.out.println();
                System.out.println("Now your opponent dropping");
                dropAll();
                AIDropping();
                if (!finalTurn) combinationChecking();
                else {
                    System.out.println("Your opponent's combination is " + finalCombinationCheck());
                }
                AICount += getCount();
                System.out.println();
                System.out.println("Your count now is " + playCount);
                System.out.println("Your opponent's count now is " + AICount);
                System.out.println();
                if (finalTurn){
                    if (AICount == playCount) {
                        System.out.println("Draw!");
                    } else if (AICount > playCount) {
                        System.out.println("You lose!");
                    } else {
                        System.out.println("You WIN!");
                    }
                    break;
                }
                finalTurn = true;
                System.out.println("Second stage! Press enter to drop");
            }
            finalTurn = false;
            System.out.println("Play more? Enter any key for play, or N for exit.");
            scanner = new Scanner(System.in);
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("N")) break;
        }
    }

    private static void getReady() {
        try{System.in.read();}
        catch(Exception e){e.printStackTrace();}
    }

    private static String finalCombinationCheck(){
        reCheckDices();
        String combinationName = "";
        label:
        for (int i = 0; i < 7; i++){
            switch (check[i]){
                case 0, 1 :
                    break;
                case 2 :
                    if (combinationName.equals("Pair")){
                        combinationName = "Two Pair";
                        break label;
                    } else if (combinationName.equals("Set")) {
                        combinationName = "Full House";
                        break label;
                    } else {
                        combinationName = "Pair";
                    }
                    break;
                case 3 :
                    if (combinationName.equals("Pair")){
                        combinationName = "Full House";
                        break label;
                    } else {
                        combinationName = "Set";
                    }
                    break;
                case 4 :
                    combinationName = "Four Of A Kind";
                    break label;
                case 5 :
                    combinationName = "Poker";
                    break label;

            }
        }
        if (combinationName.isEmpty()){
            if (dice[0] == 1 && dice[4] == 5){
                combinationName = "Small Straight";
            } else if (dice[0] == 2 && dice[4] == 6) {
                combinationName = "Large Straight";
            } else {
                combinationName = "Chance";
            }
        }

        int sumOfCombination = 0;
        for (int d : dice) sumOfCombination += d;
        if (combinationName.equals("Poker")) sumOfCombination *= 2;
        count += sumOfCombination;
        return combinationName;
    }

    private static int getCount() {
        if (count >= 0 && !finalTurn){
            count = count + 50;
        }

        int counter = count;
        count = 0;
        return counter;
    }

    private static void AIDropping() {
        for (int r = 0; true; r++) {
            Arrays.sort(dice);
            System.out.println("Your opponent's drop is:");
            System.out.printf("[%d][%d][%d][%d][%d] \n", dice[0], dice[1], dice[2], dice[3], dice[4]);
            if(r==2 || combinationDone()){
                if (r == 0 && finalTurn) count = 50;
                break;
            }
            System.out.println(Arrays.toString(AIDecide()));
            for (int j : AIDecide()) {
                dice[j] = random.nextInt(5) + 1;
            }
        }
    }

    static void dropAll(){
        for (int n = 0; n < 5; n++ ) {
            dice[n] = random.nextInt(5) + 1;
        }
    }
    static void playersDecide(){
        for (int r = 0; true; r++) {
            Arrays.sort(dice);
            System.out.println("Your drop is:");
            System.out.println("(number of dice:)");
            System.out.println(" 1  2  3  4  5");
            System.out.printf("[%d][%d][%d][%d][%d] \n", dice[0], dice[1], dice[2], dice[3], dice[4]);
            if(r==2 || combinationDone()){
                if (r == 0 && finalTurn) count = 50;
                break;
            }
            System.out.println("Set numbers of dices, which you wont to dropping (5 max). Set zero for finish");
            for (int i = 0; i < 5; i++) {
                int rd = scanner.nextInt();
                if (rd == 0) {
                    break;
                } else if (rd < 0 | rd > 5) {
                    System.out.println("Just 0 to 5, pls");
                    i--;
                    continue;
                }
                rd--;
                dice[rd] = random.nextInt(5) + 1;
            }
        }
    }

    static void combinationChecking(){
        // а вот тут типа "правильная", но трудночитаемая дичь
        reCheckDices();
        int value_of_count = 0;
        for (int r = 0; r < check.length; r++){
            switch (check[r]) {
                case 3 -> value_of_count++;
                case 4 -> value_of_count += (r + 1);
                case 5 -> value_of_count += ((r * 2) + 1);
                default -> {
                }
            }
        }
        if (value_of_count == 0){
            int twins = 0;
            while (twins < check.length) {
                if(check[twins] == 2){
                    value_of_count -= twins;
                    break;
                }
                twins++;
            }
            if (value_of_count == 0){
                value_of_count -= (DicePoker.dice[0] * 3);
            }
        } else {
            value_of_count--;
        }
        count = value_of_count;
    }
    static boolean combinationDone(){
        reCheckDices();
        if (finalTurn) {
            boolean b = !finalCombinationCheck().equals("Chance");
            count = 0;
            return b;
        }
        for (int m : check){
            if (m > 2){
                return true;
            }
        }
        return false;
    }

    private static void reCheckDices() {
        for (int i = 0; i < 7; i++){
            check[i] = 0;
        }
        for (int j : dice) {
            check[j]++;
        }
    }

    static int[] AIDecide () {
        reCheckDices();
        for (int i = 0; i < 7; i++){
            if(check[i] > 1){
                int[] solution = new int[3];
                int tumbler = 0;
                for (int j = 0; j < 5; j++){
                    if (dice[j] != i) solution[tumbler++] = j;
                }
                return solution;
            }
        }
        return new int[]{2, 3, 4};
    }
}
