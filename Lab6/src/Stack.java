import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack<T>{
    private ArrayList<T> data;
    private int top;

    public Stack() {
        this.data = new ArrayList<>();
        this.top = 0;
    }

    public void push(T element) {
        data.add(element);
        top += 1;
    }

    public T pop() {
        if (top == 0) {
            System.err.println("The stack is empty.");
            throw new NoSuchElementException("The stack is empty.");
        } else {
            top -= 1;
            return data.remove(top);
        }
    }

    public T peek() {
        if (top == 0) {
            throw new NoSuchElementException("The stack is empty.");
        } else {
            return data.get(top - 1);
        }
    }

    public void printStack() {
        if (top == 0) {
            System.out.println("The stack is empty.");
        } else {
            System.out.print("The stack is ");
            for (int i = 0; i < top; i++) {
                try {
                    System.out.print(data.get(i) + " ");
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Failed Entering of Element: " + e.getMessage());
                }
            }
            System.out.println();
        }
    }
}