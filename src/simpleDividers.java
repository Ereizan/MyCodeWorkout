import java.util.ArrayList;
import java.util.Scanner;

public class simpleDividers {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long number = s.nextLong();
        ArrayList<Integer> simples = new ArrayList<>();
        simples.add(2);
        int counter = (int) Math.floor(Math.sqrt(number));
        boolean simple = true;
        
        label: 
        for (int i = 3; i <= counter; i++){
          for (Integer n : simples){
              if (i % n == 0) continue label;
          }
          simples.add(i);
        }
        
        ArrayList<Double> dividers = new ArrayList<>();
        
        for (Integer m : simples){
            if (number % m == 0){
                simple = false;
                dividers.add(m.doubleValue());
            } 
        }
        
        StringBuilder sb = new StringBuilder(" ");
        
        if (simple){
            System.out.printf("%s is simple! \n", number);
        } else{
            double lane = number;
            boolean newBl = true;
            for (Double div : dividers){
                lane = lane / div;
            }
            for (Integer m : simples){
                if (lane % m == 0) {
                    newBl = false;
                    break;
                }
                if (m > Math.sqrt(lane)) break;
            }
            if (newBl) dividers.add(lane);
            for (Double div : dividers){
                sb.append(String.format("%.0f", div));
                if (dividers.indexOf(div) + 1 != dividers.size()) sb.append(", ");
            }
            System.out.printf("%s isn't simple! This divided by %s \n", number, sb);
        }
    }
    
}