public class GameOfLife {
    static int[][] field = new int[10][10];
    public static void main(String[] args) {
      field[0][1]++;
      field[1][2]++;
      field[2][0]++;
      field[2][1]++;
      field[2][2]++;
      
      for (int n = 0; n < 10; n++){
          getGraphics();
          toggleGameRound();
      }
      
      System.out.println("Sum of x+y = ");
    }
    
    static void getGraphics(){
        System.out.println();
        for (int[] ints : field) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    static void toggleGameRound(){
        int[][] fieldExemplar = new int[GameOfLife.field.length][GameOfLife.field[0].length];
        for (int i = 0; i < GameOfLife.field.length; i++){
            System.arraycopy(GameOfLife.field[i], 0, fieldExemplar[i], 0, GameOfLife.field[i].length);
        }
        for (int i = 0; i < GameOfLife.field.length; i++){
            for (int j = 0; j < GameOfLife.field[i].length; j++){
                int neighbors = findNeighbors(i, j, fieldExemplar);
                if (GameOfLife.field[i][j] == 1){
                    GameOfLife.field[i][j] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    GameOfLife.field[i][j] = (neighbors == 3) ? 1 : 0;
                }
            }
        }
    }
    static int findNeighbors(int column, int number, int[][] field){
        boolean notTopOfColumn = column > 0;
        boolean notTopOfLine = number > 0;
        boolean notBottomOfColumn = column < field.length - 1;
        boolean notBottomOfLine = number < field[column].length - 1;

        int neighbors = 0;
        
        if (notTopOfColumn) neighbors += field[column - 1][number];
        if (notTopOfLine) neighbors += field[column][number - 1];
        if (notBottomOfColumn) neighbors += field[column + 1][number];
        if (notBottomOfLine) neighbors += field[column][number + 1];
        
        if (notTopOfColumn && notTopOfLine) neighbors += field[column - 1][number - 1];
        if (notTopOfColumn && notBottomOfLine) neighbors += field[column - 1][number + 1];
        if (notBottomOfColumn && notBottomOfLine) neighbors += field[column + 1][number + 1];
        if (notBottomOfColumn && notTopOfLine) neighbors += field[column + 1][number - 1];

        return neighbors;
    }
    
}