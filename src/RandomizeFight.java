import java.util.Random;
import java.util.Scanner;
/*
WoD fight statistics calculator.
 */

public class RandomizeFight {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random(System.nanoTime());
    static boolean spec; 
    

    public static void main(String[] args) {
        int npc1count = 0;
        int npc2count = 0;
        spec = true;
        int number = scanner.nextInt(); //          1
        int dif = scanner.nextInt(); //             2
        int dicePoolSkill1 = scanner.nextInt();//   3
        int dicePoolSkill2 = scanner.nextInt();//   4
        int dicePoolPower1 = scanner.nextInt();//   5
        int dicePoolPower2 = scanner.nextInt();//   6     
        int dicePoolArm1 = scanner.nextInt();//     7
        int dicePoolArm2 = scanner.nextInt();//     8
        int celerity1 = scanner.nextInt();//        9
        int celerity2 = scanner.nextInt();//        10
        int potence1 = scanner.nextInt();//         11
        int potence2 = scanner.nextInt();//         12
        int difCelerity = Math.abs(celerity1 - celerity2);
        int celerity = Math.max(celerity1, celerity2);
        int lowCelerity = Math.min(celerity1, celerity2);
        for (int i = 0; i < number; i++){
            int npc1lives = 7;
            int npc2lives = 7;
            int celerityCount = 0;
            while (npc1lives > 0 || npc2lives > 0){
                int fine1 = getFine(npc1lives);
                int fine2 = getFine(npc2lives);
                int drop1 = dropCheck(dif, getDices(dicePoolSkill1 - fine1));
                int drop2 = dropCheck(dif, getDices(dicePoolSkill2 - fine2));
                if (difCelerity !=0){
                        if (celerityCount > 0){
                            if (celerityCount > lowCelerity){
                                if (celerity1 > celerity2) drop2 = 0;
                                else drop1 = 0;
                            }
                            if (celerityCount == celerity) celerityCount = -1;
                        }   
                        celerityCount++;
                }
                if ((drop1 < 1 && drop2 < 1) || drop1 == drop2) continue;
                int winSuc = Math.max(drop1, drop2) - Math.min(drop1, drop2) - 1;
                int power;
                int armor;
                int insPotence;
                if (drop1 > drop2){
                    power = dicePoolPower1 - fine1;
                    armor = dicePoolArm2;
                    insPotence = potence1;
                } else {
                    power = dicePoolPower2 - fine2;
                    armor = dicePoolArm1;
                    insPotence = potence2;
                }
                int powerDrop = Math.max(dropCheck(dif, getDices(power + winSuc)), 1) + insPotence;
                int deffenceDrop = Math.max(dropCheck(dif, getDices(armor)), 0);
                int damage = powerDrop - deffenceDrop;
                if (damage < 1) continue;
                if (drop1 > drop2){
                    npc2lives -= damage;
                } else {
                    npc1lives -= damage;
                }
            }
            if (npc1lives > npc2lives) {
                npc1count++;
            } else if (npc1lives < npc2lives) {
                npc2count++;
            }
        }
        System.out.println("NPC 1 count = " + npc1count);
        System.out.println("NPC 2 count = " + npc2count);
        double winrate = (double)(npc2count + npc1count) / 100;
        double winrate1 = (double) npc1count / winrate;
        double winrate2 = (double) npc2count / winrate;
        System.out.printf("Winrate %.2f / %.2f \n", winrate1, winrate2);
    }
    
    static int[] getDices(int number){
        int[] n = new int[]{1};
        if (number == 0) return n;
        int[] dices = new int[number--];
        while (number >= 0){
            dices[number--] = random.nextInt(10) + 1;
        }
        return dices;
    }
    
    static int dropCheck (int dif, int[] dices){
        int check = 0;
        for (int dice : dices){
            if (spec && dice == 10) check++;
            if (dice >= dif) check++;
            if (dice == 1) check--;
        }
        return check;
    }
    
    static int getFine(int live){
        return switch (live) {
            case 7, 6 -> 0;
            case 5, 4 -> -1;
            case 3, 2 -> -2;
            default -> -5;
        };
    }
    
    
}