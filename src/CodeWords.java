import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
/*
https://acm.timus.ru/problem.aspx?space=1&num=1007
the ugliest code I've ever written, but it's work
*/
public class CodeWords {
    static int n;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = Integer.parseInt(s.nextLine().split(" ")[0]);
        while (s.hasNext()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            char[] chars = line.toCharArray();
            int[] buffer = new int[n + 1];
            int size = 0;
            for (char c : chars){
                if (Character.isDigit(c)){
                    buffer[size++] = Integer.parseInt(Character.toString(c));
                }
            }
            int[] word = Arrays.copyOf(buffer, size);
            int sum = getSum(word);
            int[] answer;
            if (sum % (n + 1) == 0){
                answer = Arrays.copyOf(word, n);
            } else {
                if (size == n){
                    answer = repairOne(word, sum);
                } else if (size < n) {
                    answer = repairTwo(word, sum);
                } else {
                    answer = repairThree(word, sum);
                }
            }

            for (int w : answer){
                System.out.print(w);
            }
            System.out.println();
        }
    }

    static int getSum(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum += (array[i] * (i + 1));
        }
        return sum;
    }
    
    static int[] repairOne(int[] array, int sum){
        int dif = sum % (n + 1);
        array[dif - 1] = 0;
        return array;
    }
    
    static int[] repairTwo(int[] array, int sum){
        int[] answer = new int[n];
        int dif = n + 1 - (sum % (n + 1));
        int unitSum = 0;
        for (int i : array) unitSum += i;
        int count = 0;
        int cursor = Math.abs(dif - unitSum);
        boolean wt = dif > unitSum;
        boolean insert = false;
        for (int i = 0; i < n; i++){
            int sup = wt? (i + 1) : 0;
                if (Math.abs(sup - count) == cursor){
                    answer[i] = wt? 1 : 0;
                    if (!wt) count++;
                    insert = true;
                    continue;
                }
                int f = insert? (i - 1) : i;
                if (array[f] == 1) count++;
                answer[i] = array[f];
        }
        return answer;
    }

    static int[] repairThree(int[] array, int sum){
        int[] answer = new int[n];
        int dif = sum % (n + 1);
        int unitSum = 0;
        for (int i : array) unitSum += i;
        int count = 0;
        int cursor = Math.abs(dif - unitSum);
        boolean wt = (cursor == 0)? (array[0] == 1): (dif > unitSum);
        boolean insert = false;
        for (int i = 0; i < n + 1; i++){
            int value = wt? 1 : 0;
            int sup = wt? (i + 1) : 0;
            if (array[i] == 1) count++;
            if (Math.abs(sup - count) == cursor && array[i] == value && !insert){
                count++;
                insert = true;
                continue;
            }
            int f = (insert)? (i - 1) : i;
            answer[f] = array[i];
        }
        return answer;
    }




}