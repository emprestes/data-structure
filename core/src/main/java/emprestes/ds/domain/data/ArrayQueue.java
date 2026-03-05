package emprestes.ds.domain.data;

import emprestes.ds.domain.Queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Queue implementation backed by {@link java.util.ArrayDeque}.
 *
 * @param <T> stored element type
 */
public class ArrayQueue<T> implements Queue<T> {

    private final ArrayDeque<T> values = new ArrayDeque<>();

    /**
     * {@inheritDoc}
     */
    @Override
    @SafeVarargs
    public final Queue<T> enqueue(T... items) {
        if (items == null) {
            return this;
        }

        for (var item : items) {
            if (item != null) {
                values.addLast(item);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T dequeue() {
        return values.pollFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return values.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> toList() {
        return new ArrayList<>(values);
    }
}
