import java.util.ArrayList;
import java.util.Scanner;

public class Factorization {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double number = s.nextDouble();
        ArrayList<Integer> simples = new ArrayList<>();
        ArrayList<Double> dividers = new ArrayList<>();
        double dividend = number;
        boolean simple = true;
        int counter = (int) Math.floor(Math.sqrt(dividend));
        label: 
        for (int i = 2; i <= counter; i++){
          for (Integer n : simples){
              if (i % n == 0) continue label;
          }
          simples.add(i);
          while (true){
              if (dividend % i == 0){
                  dividend /= i;
                  simple = false;
                  dividers.add((double) i);
              } else break;
          }
          if (i > Math.sqrt(dividend)) break;
        }
        dividers.add(dividend);

        if (simple){
            System.out.printf("%.0f is simple! \n", number);
        } else{
            StringBuilder sb = new StringBuilder(" ");
            for (Double div : dividers){
                sb.append(String.format("%.0f", div));
                if (dividers.indexOf(div) + 1 != dividers.size()) sb.append(", ");
            }
            System.out.printf("%.0f isn't simple! This divided by %s \n", number, sb);
        }
    }
    
}