import java.util.Scanner;
import java.util.Arrays;
/*
WA 13!!!
https://acm.timus.ru/problem.aspx?space=1&num=1039
*/
public class AnniversaryParty {
    static Scanner s = new Scanner(System.in);
    static Mate[] mates;

    public static void main(String[] args) {
        short number = s.nextShort();

        byte[] table = new byte[number];
        for (short i = 0; i < number; i++){
            table[i] = s.nextByte();
        }
        mates = new Mate[number];
        while(true){
            short id = s.nextShort();
            if (id == 0) break;
            id--;
            short boss = (short) (s.nextShort() - 1);
            mates[id] = new Mate(id, boss, table[id]);
        }
        short bigBoss = 0;
        for (short i = 0; i < number; i++){
            if (mates[i] == null){
                mates[i] = new Mate(i, (short)-1, table[i]);
                bigBoss = i;
                continue;
            }
            if (mates[i].boss == -1) continue;
            if (mates[mates[i].boss] == null){
                mates[mates[i].boss] = new Mate(mates[i].boss, (short)-1, table[mates[i].boss]);
                bigBoss = mates[i].boss;
            }
            mates[mates[i].boss].addSlave(i);
        }
        welcome(mates[bigBoss]);
        int sum = 0;
        for (Mate mate : mates){
//             System.out.println(mate.party + " " + mate.id);
            if (mate.party) {
                sum += mate.smile;
            }
        }
// System.out.println();
        System.out.println(sum);
    }
    static void welcome(Mate mate){
        if (mate.slaves.length != 0){
            for (short sl : mate.slaves){
                welcome(mates[sl]);
            }
            if (mate.party){
                int sum = 0;
                for (short sl : mate.slaves){
                    if (mates[sl].party) sum += mates[sl].smile;
                }
                if (sum < mate.smile) {
                    for (short sl : mate.slaves){
                       mates[sl].party = false;
                    }
                } else {
                    mate.party = false;
                }
            }
        }
    }

}
class Mate {
    short id;
    short boss;
    byte smile;
    boolean party;
    short[] slaves = new short[0];

    public Mate (short id, short boss, byte smile){
        this.id = id;
        this.boss = boss;
        this.smile = smile;
        party = smile > 0;
    }

    public void addSlave(short slave){
        slaves = Arrays.copyOf(slaves, slaves.length + 1);
        slaves[slaves.length - 1] = slave;
    }


}
