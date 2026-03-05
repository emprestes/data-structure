package emprestes.ds.domain.model;

import static java.util.Optional.ofNullable;

/**
 * Doubly linked node used by stack internals.
 *
 * @param <T> node value type
 */
public class Node<T> {

    private final T value;
    private Node<T> next;
    private Node<T> previous;

    /**
     * Creates a root node with no previous reference.
     *
     * @param value node value
     */
    public Node(T value) {
        this(null, value);
    }

    /**
     * Creates a node linked to a previous node.
     *
     * @param previous previous node
     * @param value node value
     */
    public Node(Node<T> previous, T value) {
        super();

        this.value = value;
        this.previous = previous;
    }

    /**
     * Returns the node value.
     *
     * @return node value
     */
    public T value() {
        return value;
    }

    /**
     * Indicates whether a next node exists.
     *
     * @return {@code true} when a next node exists
     */
    public boolean hasNext() {
        return next != null;
    }

    /**
     * Returns the next node reference.
     *
     * @return next node, or {@code null}
     */
    public Node<T> next() {
        return next;
    }

    /**
     * Appends a new value at the end of the chain.
     *
     * @param value value to append
     * @return appended node
     */
    public Node<T> next(T value) {
        return next = ofNullable(next)
                .map(n -> n.next(value))
                .orElseGet(() -> new Node<>(this, value));
    }

    /**
     * Returns the previous node and detaches current from it.
     *
     * @return previous node, or {@code null} for root
     */
    public Node<T> previous() {
        if (!isRoot()) {
            previous.next = null;
        }

        return previous;
    }

    /**
     * Indicates whether this node is the root node.
     *
     * @return {@code true} when no previous node exists
     */
    public boolean isRoot() {
        return previous == null;
    }

    /**
     * Counts nodes from current back to root.
     *
     * @return chain size including current node
     */
    public int count() {
        if (!isRoot()) {
            return 1 + previous.count();
        }

        return 1;
    }
}
