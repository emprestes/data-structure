package emprestes.ds.domain;

import java.util.List;

/**
 * Defines a LIFO stack contract.
 *
 * @param <T> stored element type
 */
public interface IStack<T> {

    /**
     * Pushes one or more values to the top of the stack.
     *
     * @param value values to push
     * @return current stack instance
     */
    IStack<T> push(T... value);

    /**
     * Removes and returns the current top element.
     *
     * @return top element, or {@code null} when empty
     */
    T pop();

    /**
     * Removes and returns all elements from top to bottom.
     *
     * @return removed elements in pop order
     */
    List<T> popAll();

    /**
     * Indicates whether the stack has no elements.
     *
     * @return {@code true} when empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements currently stored.
     *
     * @return stack size
     */
    int size();

    /**
     * Returns the current elements from top to bottom without mutating the stack.
     */
    List<T> toList();
}
