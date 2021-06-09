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
        if (size() != 0) {
            if (get(pointer).equals(element)) {
                System.out.println("HELLO");
                return;
            }
        }

        push(element);
        pointer++;
    }

    public E undo() {
        if (pointer - 1 < 0) {
            return get(0);
        }
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
