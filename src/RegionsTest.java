import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
/*
Фермер Василий выбирает землю для покупки. Предмет торгов – прямоугольное поле шириной n и высотой m,
которое состоит из участков, где 1 - плодородный участок, а 0 – неплодородный.
Василий может либо купить регион поля любого размера, либо отказаться от покупки,
если доступных для покупки регионов нет.
 */


public class RegionsTest {
    static int[][] field;
    static int m;
    static int n;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Random random = new Random(System.nanoTime());
        String sizeString = scanner.nextLine();
        String[] size = sizeString.split(" ");
      
        m = Integer.parseInt(size[1]);
        n = Integer.parseInt(size[0]);
        field = new int[m][n];
        for (int i = 0; i < m; i++){
            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            for (int j = 0; j < n; j++){
                field[i][j] = Integer.parseInt(numbers[j]);
            }
        }
       // showField();// тестовый
       // System.out.println();//тестовый

        // получаем массив с регионами и их эффективностью
        int[][] regions = findRegions();
        int maxEffectivity = 0; // максимальная эффективность
        int maxSize = 0; // максимальный размер
        // находим максимальную эффективность для регионов с более чем одинм плодородным участком
        for (int i = 0; i < regions[0].length; i++){
            if (regions[1][i] >= maxEffectivity && regions[0][i] > 1){
                maxEffectivity = regions[1][i];
            }
        }
        for (int i = 0; i < regions[0].length; i++){
            if (regions[1][i] == maxEffectivity && regions[0][i] >= maxSize && regions[0][i] > 1){
                maxSize = regions[0][i];
            }
        }

        //покажем видоизменненное поле - в нем проще соорентироваться
//        showField(); // тестовый

//        System.out.println(); // тестовый
        //вывод решения
        System.out.println(maxSize);
    }

    private static void showField() {
        for (int[] line : field){
            for (int cell : line){
                System.out.printf("%2d", cell);
            }
            System.out.println();
        }
    }

    // ищет все регионы и возвращяет массив с их размерами и эффективностью
    static int[][] findRegions(){
        //счетчик регионов. 2 - потому, что 1, 0 - занято участками, которым пока не присвоен регион 
        int regionCount = 2; 
        // присваем каждому плоородному участку номер региона, который образует эта цепь участков  
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (field[i][j] == 1){
                    // ищем соседей, затем прокручиваем счетчик
                    findNeighbor(i, j, regionCount);
                    regionCount++;
                }
            }
        }
        //массив размеров регионов. 
        int[] regionSize = new int[regionCount - 2]; 
        //массив эффективности регионов
        int[] regionEffectivity = new int[regionCount - 2];
        //проходимся по каждому существующему региону
        for (int c = 2; c < regionCount; c++){
            int top = 0;    // отмечает верхнюю границу региона
            int bottom = 0; // нижнюю границу
            int left = 0;   // левый край
            int right = 0;  // правый край
            int good = 0; //плодородные участки
            int square; //площадь региона
            //следущим действием только определяем границы, пока не считая участки
            //first нужно, чтобы инициализировать начальное значение границ по первому найденому участку
            boolean first = true;
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    if (field[i][j] == c){
                        if (first) {
                            top = i;
                            bottom = i;
                            left = j;
                            right = j;
                            first = false;
                        } else {
                            top = Math.min(i, top);
                            bottom = Math.max(i, bottom);
                            left = Math.min(j, left);
                            right = Math.max(j, right);
                        }
                    }
                }
            }
            //определяем площадь 
            square = (bottom - top + 1) * (right - left + 1);
            //теперь проходимся только по региону, считая все плодородные участки
            //в том числе те, что относятся к другим цепочкам
            for (int i = top; i <= bottom; i++){
                for (int j = left; j <= right; j++){
                    if (field[i][j] > 0) good++;
                }
            }
            // в массивы добавляем характеристики регионов
            regionSize[c - 2] = square;
            regionEffectivity[c - 2] = (good * 100000) / square;
        }
        // возвращаем массив с размерами и эффетивностями регионов
        return new int[][]{regionSize, regionEffectivity};
    }
    
    //рекурсивный метод сел в лужу, теперь тут ламповый цикл с Эррай листом
    static void findNeighbor(int y, int x, int count){
        ArrayList<Integer[]> coordinates = new ArrayList<>();
        Integer[] start = {y, x};
        coordinates.add(start);
        int border = 1;
        for (int index = 0; index < border; index++){
            y = coordinates.get(index)[0];
            x = coordinates.get(index)[1];
            for (int i = Math.max(0, (y - 1)); i <= Math.min((y + 1), (m-1)); i++) {
                for (int j = Math.max(0, (x - 1)); j <= Math.min((x + 1), (n - 1)); j++) {
                    if (field[i][j] == 1) {
                        field[i][j] = count;
                        Integer[] pair = {i, j};
                        coordinates.add(pair);
                        border++;
                    }
                }
            }
        }
    }
}