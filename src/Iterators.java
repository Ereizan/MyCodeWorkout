import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Iterators {
    public static void main(String[] args) {
        Integer[] integers = {78, 1, 51, 68, 10, 51};
        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(integers));
        for (Integer i : ints){
            System.out.println(i);
        }
        System.out.println();
        ArrayIterator<Integer> ai = new ArrayIterator<>(integers);
        while (ai.hasNext()){
             System.out.println(ai.next());
        }
        String[][] strings = {{"ку","кю"},{"бе","ме"},{"хрю","му"}};
        TwoDimensionalArrayIterator<String> ais = new TwoDimensionalArrayIterator<>(strings);
        while (ais.hasNext()){
            System.out.println(ais.next());
        }
      
    }
}
class ArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int index = 0;
    
    public ArrayIterator(T[] array){
        this.array = array;
    }
    
    public boolean hasNext(){
        return index < array.length;
    } 
    
    public T next(){
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}

class TwoDimensionalArrayIterator<T> implements Iterator<T> {
    private final T[][] array;
    private int index = 0;
    private int indexTwo = 0;
    private final int al;
    
    public TwoDimensionalArrayIterator(T[][] array){
        this.array = array;
        al = array.length - 1;
    }
    
    public boolean hasNext(){
        if (index < al) return true;
        return indexTwo < array[al].length;
    } 
    
    public T next(){
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        if (indexTwo == array[index].length) {
            index++;
            indexTwo = 0;
        }
        return array[index][indexTwo++];
    }
}