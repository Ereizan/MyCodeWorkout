import java.util.Arrays;
/*
Unit 1.4.22 from "Computer Science" R.Sedgewick, K.Wayne, solution.
 */
public class DiceRollerTest {
    public static void main(String[] args) {
        int[] frequencies = new int[13];
        int[] tests = new int[13];
        double[] results = new double[13];

        for (int i = 1 ; i <= 6 ; i++){
            for (int j = 1 ; j <= 6 ; j++ ){
                frequencies[i + j]++;
            }
        }
        double[] probabilities = new double[13];
        for (int k = 1 ; k <= 12 ; k++){
            probabilities[k] = frequencies[k] / 36.0;
        }
        int result = 0;

        for (int m = 0; m < Integer.MAX_VALUE; m++){
            int drop = rollD6() + rollD6();
            tests[drop]++;
            results[drop] = (double) tests[drop] / m;
            boolean b = true;
            for (int i = 0; i < 13; i++){
                b = b && (Math.round(probabilities[i] * 10000.0) == Math.round(results[i] * 10000.0));
            }
            if (b){
                result = m;
                break;
            }
        }
        System.out.println(Arrays.toString(probabilities));
        System.out.println(Arrays.toString(results));
        System.out.println(result);
    }

    public static int rollD6(){
        return (int) (Math.random() * 6) + 1;
    }

}
