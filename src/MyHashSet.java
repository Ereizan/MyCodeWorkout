import java.util.HashSet;
import java.util.Scanner;

public class MyHashSet {
    public static void main(String[] args) {
        HashSet<String> persons = new HashSet<>();
        Scanner console = new Scanner(System.in);
        System.out.println("Введите имя персонажа. Введите конец чтобы закончить");
        while (true){
            String name = console.nextLine();
            if (name.equals("конец")) break;
            persons.add(name);
        }
        System.out.println("В списке лиц: " + persons.size());
        System.out.println("Проверьте есть ли персонаж в списке. Ввыедите имя. Введите конец для окончания работы");
        while (true){
            String name = console.nextLine();
            if (name.equals("конец")) break;
            if (persons.contains(name)){
                System.out.printf("%s есть в списке \n", name );
            } else {
                System.out.printf("%s нет в списке \n", name );
            }
        }
    }
}
