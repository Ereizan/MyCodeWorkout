import java.util.Arrays;

/*
Geometric calculator. It will be supplemented.
 */
public class EasyGeometry {
    public static void main(String[] args) {
        Cell cell1 = new Cell(5, 6);
        Cell cell2 = new Cell(-7, 9);
        cell1.sayCoordinates();
        cell2.sayCoordinates();
        StraightLine sl = new StraightLine (cell1, cell2);
        System.out.println(sl.lineLength);
        System.out.println(Arrays.toString(cell1.getMiddle(cell2)));
        Cell cell3 = new Cell(0, 5);
        Triangle triangle = new Triangle(cell3, cell2, cell1);
        System.out.println(triangle.getArea());
    }
}
class Cell {
    double x;
    double y;

    Cell (double x, double y){
        this.x = x;
        this.y = y;
    }

    public void sayCoordinates(){
        System.out.println("x = " + x + "; y = " + y);
    }

    public double[] getMiddle(Cell otherCell){
        double x = (this.x + otherCell.x) / 2;
        double y = (this.y + otherCell.y) / 2;
        return new double[]{x, y};
    }

}

class StraightLine {
    Cell begin;
    Cell end;
    double lineLength;
    double vertical;
    double horizontal;

    StraightLine (Cell begin, Cell end){
        this.begin = begin;
        this.end = end;
        vertical = Math.max(begin.y, end.y) - Math.min(begin.y, end.y);
        horizontal = Math.max(begin.x, end.x) - Math.min(begin.x, end.x);
        lineLength = Math.sqrt(Math.pow(vertical, 2) + Math.pow(horizontal, 2));
    }
}

class Triangle {
    Cell A;
    Cell B;
    Cell C;
    StraightLine AB;
    StraightLine BC;
    StraightLine AC;
    double area;

    Triangle (Cell A, Cell B, Cell C){
        this.A = A;
        this.B = B;
        this.C = C;
        AB = new StraightLine(A, B);
        BC = new StraightLine(B, C);
        AC = new StraightLine(C, A);
    }

    public double getArea(){
        double semiPerimeter = (AB.lineLength + BC.lineLength + AC.lineLength) / 2;
        area = Math.sqrt(semiPerimeter * (semiPerimeter - AB.lineLength) * (semiPerimeter - BC.lineLength) * (semiPerimeter - AC.lineLength));
        return area;
    }
}