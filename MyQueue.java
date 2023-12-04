import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {
    private ArrayList<T> queue;
    private int maxSize;
    private int front;
    private int rear;
    private int currentSize;

    public MyQueue(int size) {
        this.maxSize = size;
        this.queue = new ArrayList<>(size);
        this.front = 0;
        this.rear = -1;
        this.currentSize = 0;
    }

    public MyQueue() {
        this(100);
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean isFull() {
        return currentSize == maxSize;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException("Queue is empty");
        }
        T item = queue.get(front);
        front = (front + 1) % maxSize;
        currentSize--;
        return item;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException("Queue is full");
        }
        rear = (rear + 1) % maxSize;
        queue.add(rear, e);
        currentSize++;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        int index = front;
        while (count < currentSize) {
            result.append(queue.get(index)).append(" ");
            index = (index + 1) % maxSize;
            count++;
        }
        return result.toString();
    }

    @Override
    public String toString(String delimiter) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        int index = front;
        while (count < currentSize) {
            result.append(queue.get(index));
            if (count < currentSize - 1) {
                result.append(delimiter);
            }
            index = (index + 1) % maxSize;
            count++;
        }
        return result.toString();
    }

    @Override
    public void fill(ArrayList<T> list) throws QueueOverflowException {
        for (T item : list) {
            enqueue(item);
        }
    }
}

