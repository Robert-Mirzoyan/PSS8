/** Course circular-array queue implementation used by Problem 3. */
public class ArrayQueue<E> implements Queue<E> {
    private final E[] data;
    private int front;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayQueue() {
        this(1000);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void enqueue(E e) {
        if (size == data.length) throw new IllegalStateException("Queue is full");
        data[(front + size) % data.length] = e;
        size++;
    }

    public E first() {
        return isEmpty() ? null : data[front];
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E answer = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return answer;
    }
}
