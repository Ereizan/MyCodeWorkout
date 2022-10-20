import java.util.Scanner;
import java.util.Arrays;
/*
https://acm.timus.ru/problem.aspx?space=1&num=1005
The task looks simple to disgrace, but it is not.
Thanks for the sleepless night.
*/
public class StonesPile {
    static int bigCount = 0;
    static int n;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = Integer.parseInt(s.nextLine());
        String[] input = s.nextLine().split(" ");

        int[] stones = new int[n];
        for (int i = 0; i < n; i++){
            stones[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(stones);

        int stonesSum = getCheckSum(stones);

        int distance = stonesSum;

        label:
        for (int i = 1; i <= n / 2; i++){
        
            long m = getFactorial(n);
            long k = getFactorial(i);
            long r = getFactorial(n - i);

            int[] serves = new int[i];
            int[][] indexes = new int[(int)(m / (k * r))][serves.length];
            indexes = getIndexes(indexes, serves, 0, 0);
            bigCount = 0;
            for (int[] index : indexes) {
                int[] checks = new int[i];
                for (int x = 0; x < i; x++) {
                    checks[x] = stones[index[x]];
                }
                int sum = getCheckSum(checks);
                int sumOther = stonesSum - sum;
                if (Math.abs(sumOther - sum) < distance) distance = Math.abs(sumOther - sum);
                if (distance == 0) break label;
            }
        }

        System.out.println(distance);

    }

    private static int getCheckSum(int[] stones) {
        int checkSum = 0;
        for (int stone : stones){
            checkSum += stone;
        }
        return checkSum;
    }

    public static long getFactorial(int f) {
        long result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
    static int[][] getIndexes(int[][] indexes, int[] serves, int count, int adder){

        if (count == serves.length){
            indexes[bigCount++] = Arrays.copyOf(serves, serves.length);
            return indexes;
        }
        for (int i = adder; i < n; i++){
            serves[count] = i;
            indexes = getIndexes(indexes, serves, count + 1, i + 1);
        }
        return indexes;

    }
}