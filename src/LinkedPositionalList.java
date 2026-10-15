public class LinkedPositionalList<E> implements PositionalList<E> {
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            if (next == null) {
                throw new IllegalStateException("Position is no longer valid");
            }
            return element;
        }
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size;

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    @SuppressWarnings("unchecked")
    private Node<E> validate(Position<E> p) {
        if (!(p instanceof Node<?>)) {
            throw new IllegalArgumentException("Invalid position");
        }
        Node<E> node = (Node<E>) p;
        if (node.next == null) {
            throw new IllegalArgumentException("Position is no longer valid");
        }
        return node;
    }

    private Position<E> position(Node<E> node) {
        return node == header || node == trailer ? null : node;
    }

    private Position<E> addBetween(E e, Node<E> prev, Node<E> next) {
        Node<E> newest = new Node<>(e, prev, next);
        prev.next = newest;
        next.prev = newest;
        size++;
        return newest;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public Position<E> first() { return position(header.next); }
    public Position<E> last() { return position(trailer.prev); }
    public Position<E> before(Position<E> p) { return position(validate(p).prev); }
    public Position<E> after(Position<E> p) { return position(validate(p).next); }
    public Position<E> addFirst(E e) { return addBetween(e, header, header.next); }
    public Position<E> addLast(E e) { return addBetween(e, trailer.prev, trailer); }

    public Position<E> addBefore(Position<E> p, E e) {
        Node<E> node = validate(p);
        return addBetween(e, node.prev, node);
    }

    public Position<E> addAfter(Position<E> p, E e) {
        Node<E> node = validate(p);
        return addBetween(e, node, node.next);
    }

    public E set(Position<E> p, E e) {
        Node<E> node = validate(p);
        E old = node.element;
        node.element = e;
        return old;
    }

    public E remove(Position<E> p) {
        Node<E> node = validate(p);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        E answer = node.element;
        node.element = null;
        node.prev = null;
        node.next = null;
        return answer;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Position<E> p = first();
        while (p != null) {
            result.append(p.getElement());
            p = after(p);
            if (p != null) result.append(", ");
        }
        return result.append("]").toString();
    }
}
