package sample;

import java.util.Stack;

public class StackWithPointer<E> extends Stack<E> {
    public int pointer = 0;

    public StackWithPointer() {

    }

    public void insertWithPointer(E element) {
        deleteElementsAfterPointer();
        pushWithPointer(element);
    }

    public void pushWithPointer(E element) {
        push(element);
        pointer++;
    }

    public E undo() {
        pointer--;
        return get(pointer);
    }

    public E redo() {
        if(pointer == size() - 1) return null;

        pointer++;
        return get(pointer);
    }

    public void deleteElementsAfterPointer() {
        if (size() < 1) return;

        for (int i = size()-1; i > pointer; i--) {
            pop();
        }
    }

}
