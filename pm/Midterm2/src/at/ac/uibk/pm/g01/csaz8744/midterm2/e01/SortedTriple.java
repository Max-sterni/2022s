package at.ac.uibk.pm.g01.csaz8744.midterm2.e01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedTriple<T extends Comparable<T>> {

    private List<T> triple = new ArrayList<>();

    public SortedTriple(T first, T second, T third){
        triple.add(first);
        triple.add(second);
        triple.add(third);
        Collections.sort(triple);
    }

    public SortedTriple(T first, T second, T third, Comparator<T> comparator){
        triple.add(first);
        triple.add(second);
        triple.add(third);
        triple.sort(comparator);
    }

    public void resort(Comparator<T> comparator){
        triple.sort(comparator);
    }

    public void reverse(){
        T tmp = triple.get(0);
        triple.set(0, triple.get(2));
        triple.set(2, tmp);
    }

    public T getFirst(){
        return triple.get(0);
    }

    public T getSecond(){
        return triple.get(1);
    }

    public T getThird(){
        return triple.get(2);
    }

    @Override
    public String toString() {
        return "1: " + triple.get(0) + " 2: " + triple.get(1) + " 3: " + triple.get(2);
    }
}
