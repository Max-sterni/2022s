package at.ac.uibk.pm.g01.csaz8744.s10.e02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListSet<T> {

    private List<T> elements;

    private int size = 0;

    public ListSet() {
        this.elements = new ArrayList<>();
    }

    public ListSet(List<T> elements) {
        this.elements = elements;
    }

    public void add(T element) {
        if (!contains(element)) {
            elements.add(element);
            size++;
        }
    }

    public void addAll(Collection<T> elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean contains(T element) {
        for (T t : elements) {
            if (t.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < elements.size(); i++) {
            result += elements.remove(i);
            result += " ";
        }
        return result;
    }

    public ListSet<T> clone() {
        return new ListSet<>(elements);
    }

    public void removeAll(Collection<T> toRemove) {
        for (T t : toRemove) {
            if(contains(t)){
                elements.remove(t);
                size--;
            }
        }
    }

    public int size() {
        return size;
    }


}
