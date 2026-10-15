import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** A small general ordered tree used only to run the PSS examples. */
public class LinkedTree<E> extends AbstractTree<E> {
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private final List<Node<E>> children = new ArrayList<>();
        private LinkedTree<E> owner;

        Node(E element, Node<E> parent, LinkedTree<E> owner) {
            this.element = element;
            this.parent = parent;
            this.owner = owner;
        }

        public E getElement() {
            if (owner == null) throw new IllegalStateException("Position is invalid");
            return element;
        }
    }

    private Node<E> root;
    private int size;

    @SuppressWarnings("unchecked")
    private Node<E> validate(Position<E> p) {
        if (!(p instanceof Node<?>)) throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p;
        if (node.owner != this) throw new IllegalArgumentException("Position is not in this tree");
        return node;
    }

    public Position<E> addRoot(E e) {
        if (root != null) throw new IllegalStateException("Tree already has a root");
        root = new Node<>(e, null, this);
        size = 1;
        return root;
    }

    public Position<E> addChild(Position<E> p, E e) {
        Node<E> parent = validate(p);
        Node<E> child = new Node<>(e, parent, this);
        parent.children.add(child);
        size++;
        return child;
    }

    public Position<E> root() { return root; }
    public Position<E> parent(Position<E> p) { return validate(p).parent; }
    public int numChildren(Position<E> p) { return validate(p).children.size(); }
    public int size() { return size; }

    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> answer = new ArrayList<>();
        answer.addAll(validate(p).children);
        return answer;
    }

    private void preorder(Node<E> node, List<Position<E>> result) {
        result.add(node);
        for (Node<E> child : node.children) preorder(child, result);
    }

    public Iterable<Position<E>> positions() {
        List<Position<E>> result = new ArrayList<>();
        if (root != null) preorder(root, result);
        return result;
    }

    public Iterator<E> iterator() {
        Iterator<Position<E>> positions = positions().iterator();
        return new Iterator<>() {
            public boolean hasNext() { return positions.hasNext(); }
            public E next() { return positions.next().getElement(); }
        };
    }
}
