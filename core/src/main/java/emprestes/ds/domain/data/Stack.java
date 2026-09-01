package emprestes.ds.domain.data;

import emprestes.ds.domain.IStack;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Linked-node LIFO stack with encapsulated storage.
 *
 * <p>The node type is private so clients interact with stack behavior rather
 * than navigating or mutating its representation. Push, pop, size, and
 * emptiness checks are O(1). Creating a snapshot or removing all values is
 * O(n).</p>
 *
 * @param <T> stored value type
 */
public final class Stack<T> implements IStack<T> {
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class Node<T> {
        private final T value;
        private final Node<T> previous;
    }

    private Node<T> head;
    private int size;

    /** Creates an empty stack. */
    public Stack() {
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc} Null values are ignored. Complexity: O(k) for k values. */
    @Override
    @SafeVarargs
    public final IStack<T> push(T... values) {
        if (values == null) {
            return this;
        }
        for (var value : values) {
            if (value != null) {
                head = new Node<>(value, head);
                size++;
            }
        }
        return this;
    }

    /** {@inheritDoc} Complexity: O(1). */
    @Override
    public T pop() {
        if (head == null) {
            return null;
        }
        var value = head.value;
        head = head.previous;
        size--;
        return value;
    }

    /** {@inheritDoc} Complexity: O(n). */
    @Override
    public List<T> popAll() {
        var values = new ArrayList<T>(size);
        while (!isEmpty()) {
            values.add(pop());
        }
        return values;
    }

    /** {@inheritDoc} Complexity: O(1). */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The traversal does not mutate or rebuild this stack. Complexity: O(n).</p>
     */
    @Override
    public List<T> toList() {
        var values = new ArrayList<T>(size);
        for (var current = head; current != null; current = current.previous) {
            values.add(current.value);
        }
        return List.copyOf(values);
    }
}
