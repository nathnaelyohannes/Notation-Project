import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
    private ArrayList<T> stack;
    private int maxSize;
    private int top;

    public MyStack(int size) {
        this.maxSize = size;
        this.stack = new ArrayList<>(size);
        this.top = -1;
    }

    public MyStack() {
        this(100);
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == maxSize - 1;
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        return stack.get(top--);
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        return stack.get(top);
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException("Stack is full");
        }
        stack.add(++top, e);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            result.append(stack.get(i)).append(" ");
        }
        return result.toString();
    }

    @Override
    public String toString(String delimiter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            result.append(stack.get(i));
            if (i < top) {
                result.append(delimiter);
            }
        }
        return result.toString();
    }

    @Override
    public void fill(ArrayList<T> list) throws StackOverflowException {
        for (T item : list) {
            push(item);
        }
    }
}

