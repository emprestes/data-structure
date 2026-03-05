package emprestes.ds.domain;

import java.util.List;

/**
 * Defines a FIFO queue contract.
 *
 * @param <T> stored element type
 */
public interface Queue<T> {

    /**
     * Adds one or more values to the queue tail.
     *
     * @param values values to enqueue
     * @return current queue instance
     */
    Queue<T> enqueue(T... values);

    /**
     * Removes and returns the value at the queue head.
     *
     * @return dequeued value, or {@code null} when empty
     */
    T dequeue();

    /**
     * Indicates whether the queue has no elements.
     *
     * @return {@code true} when empty
     */
    boolean isEmpty();

    /**
     * Returns the current number of elements.
     *
     * @return queue size
     */
    int size();

    /**
     * Returns the current queue values from head to tail.
     *
     * @return queue snapshot
     */
    List<T> toList();
}
