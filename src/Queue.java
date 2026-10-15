/** Course Queue ADT used by the round-robin iterator in Problem 3. */
public interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E first();
    E dequeue();
}
