package emprestes.ds.domain;

import java.util.List;

/**
 * Read-only view of a LIFO stack.
 *
 * <p>This role-specific interface lets observers depend on stack state without
 * receiving mutation operations they do not need.</p>
 *
 * @param <T> stored element type
 */
public interface StackView<T> {
    /**
     * Checks for an empty stack.
     *
     * @return whether the stack contains no elements
     */
    boolean isEmpty();

    /**
     * Reports the stack size.
     *
     * @return number of stored elements
     */
    int size();

    /**
     * Captures current values without mutation.
     *
     * @return immutable snapshot from top to bottom
     */
    List<T> toList();
}
