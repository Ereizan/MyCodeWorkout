import java.util.Scanner;
/*
Tic-Tac-Toe game. AI is still weak
*/
public class TicTacToe {
    static int[][] field;
    static final String[] numerousField = new String[]{"[1][2][3]","[4][5][6]","[7][8][9]"};
    static boolean finish = false;
    static boolean win = false;
    static boolean AIWin = false;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        field = new int[3][3];
        display();
        while (!finish){
            showNumerousField();
            int x = -1;
            while (x > 8 || x < 0){
                System.out.println("Set cell number from 1 to 9");
                x = s.nextInt() - 1;
                if (field[x / 3][x % 3] != 0){
                    System.out.println("This cell is already taken!");
                    x = -1;
                }
            }
            field[x / 3][x % 3] = 1;
            display();
            check(getControl());
            if (finish) break;
            System.out.println("AI decides");
            AIDecide();
            display();
            check(getControl());
        }
        if (win) {
            System.out.println("YOU WIN");
        } else if (AIWin) {
            System.out.println("YOU LOSE");
        } else {
            System.out.println("DRAW");
        }
    }

    static void display(){
        System.out.println();
        for (int[] line: field){
            for (int c : line){
                System.out.print("[");
                System.out.print(c < 0? "0" : c > 0 ? "x" : "_");
                System.out.print("]");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void showNumerousField(){
        System.out.println();
        for (String s : numerousField){
            System.out.println(s);
        }
        System.out.println();
    }

    static void AIDecide(){
        int[] controlSum = getControl();
        boolean itsWin = false;
        int caseToHit = -1;
        boolean letsTryThis = true;
        label:
        for (int i = 0; i < 8; i++){
            switch (controlSum[i]){
                case -2:
                    itsWin = true;
                    hitHim(i);
                    break label;
                case 2:
                    caseToHit = i;
                    letsTryThis = false;
                    break;
                default:
                    break;
            }
        }
        if(!itsWin){
            if (!letsTryThis) {
                hitHim(caseToHit);
            } else {
                letsTry();
            }
        }
    }

    static void letsTry(){
        int sum = 0;
        for (int i : getControl()){
            if (i != 0) sum++;
        }
        int bestOfThe = -1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (field[i][j] != 0) continue;
                field[i][j] = -1;
                int newSum = 0;
                for (int n : getControl()){
                    if (n != 0) newSum++;
                }
                if (newSum <= sum){
                    bestOfThe = (i * 3) + j;
                    sum = newSum;
                }
                field[i][j] = 0;
            }
        }
        if (bestOfThe < 0){
            label:
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (field[i][j] == 0){
                        field[i][j] = -1;
                        break label;
                    }
                }
            }
        } else {
            field[bestOfThe / 3][bestOfThe % 3] = -1;
        }
    }


    static void hitHim(int i){
        if (i < 6) {
            for (int c = 0; c < 3; c++){
                int m = i < 3? i : c;
                int n = i < 3? c : i - 3;
                if (field[m][n] == 0){
                    field[m][n]--;
                    break;
                }
            }
        } else if (i == 6) {
            field[0][0] = field[0][0] == 0? -1 : field[0][0];
            field[1][1] = field[1][1] == 0? -1 : field[1][1];
            field[2][2] = field[2][2] == 0? -1 : field[2][2];
        } else {
            field[2][0] = field[2][0] == 0? -1 : field[2][0];
            field[1][1] = field[1][1] == 0? -1 : field[1][1];
            field[0][2] = field[0][2] == 0? -1 : field[0][2];
        }
    }

    static int[] getControl(){
        int[] controlSum = new int[8];
        boolean finished = true;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                controlSum[i] += field[i][j];
                controlSum[j + 3] += field[i][j];
                finished = finished && field[i][j] != 0;
                if (i == j) {
                    controlSum[6] += field[i][j];
                }
            }
        }
        finish = finished;
        controlSum[7] = field[0][2] + field[1][1] + field[2][0];
        return controlSum;
    }

    static void check(int[] controlSum){
        for (int c : controlSum){
            win = c == 3;
            AIWin = c == -3;
            if (win || AIWin) {
                finish = true;
                break;
            }
        }
    }


}
