package game;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoList<E> {

    private List<E> list = new ArrayList<>();
    private int pointer = 0;

    public void add(E element) {
        if (pointer != list.size()) {
            deleteElementsAfterPointer();
        }

        list.add(element);
        pointer++;
    }

    public E undo() {
        if (pointer - 1 < 0) {
            return list.get(0);
        }
        pointer--;
        return list.get(pointer);
    }

    public E redo() {
        if(pointer == list.size()) return list.get(list.size()-1);

        pointer++;
        return list.get(pointer-1);
    }

    public void deleteElementsAfterPointer() {
        if (list.size() < 1) return;

        if (pointer == 0) {
            list.clear();
        }

        list = list.subList(0,pointer);
    }

    public void reset() {
        pointer = 0;
        list.clear();
    }

    public int getPointer() {
        return pointer;
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
