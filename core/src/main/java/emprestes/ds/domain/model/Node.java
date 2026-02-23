package emprestes.ds.domain.model;

import static java.util.Optional.ofNullable;

public class Node<T> {

    private final T value;
    private Node<T> next;
    private Node<T> previous;

    public Node(T value) {
        this(null, value);
    }

    public Node(Node<T> previous, T value) {
        super();

        this.value = value;
        this.previous = previous;
    }

    public T value() {
        return value;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Node<T> next() {
        return next;
    }

    public Node<T> next(T value) {
        return next = ofNullable(next)
                .map(n -> n.next(value))
                .orElseGet(() -> new Node<>(this, value));
    }

    public Node<T> previous() {
        if (!isRoot()) {
            previous.next = null;
        }

        return previous;
    }

    public boolean isRoot() {
        return previous == null;
    }

    public int count() {
        if (!isRoot()) {
            return 1 + previous.count();
        }

        return 1;
    }
}
