package emprestes.ds.domain;

import java.util.List;

/**
 * Mutation role of a LIFO stack.
 *
 * @param <T> stored element type
 */
public interface MutableStack<T> {
    /**
     * Pushes one or more values.
     *
     * @param values values to push
     * @return this mutation interface
     */
    @SuppressWarnings("unchecked")
    MutableStack<T> push(T... values);

    /**
     * Removes the top value.
     *
     * @return removed top value, or {@code null} when empty
     */
    T pop();

    /**
     * Removes every value.
     *
     * @return all removed values in pop order
     */
    List<T> popAll();
}
