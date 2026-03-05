package emprestes.ds.domain.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Thin wrapper around {@link java.util.HashSet} for educational purposes.
 *
 * @param <T> stored value type
 */
public class SimpleSet<T> {

    private final Set<T> values = new HashSet<>();

    /**
     * Adds a value to the set.
     *
     * @param value value to add
     * @return current set instance
     */
    public SimpleSet<T> add(T value) {
        if (value != null) {
            values.add(value);
        }
        return this;
    }

    /**
     * Removes a value from the set.
     *
     * @param value value to remove
     * @return {@code true} when value was present
     */
    public boolean remove(T value) {
        return values.remove(value);
    }

    /**
     * Checks whether the set contains a value.
     *
     * @param value value to search
     * @return {@code true} when present
     */
    public boolean contains(T value) {
        return values.contains(value);
    }

    /**
     * Returns the number of values in the set.
     *
     * @return set size
     */
    public int size() {
        return values.size();
    }

    /**
     * Indicates whether the set has no values.
     *
     * @return {@code true} when empty
     */
    public boolean isEmpty() {
        return values.isEmpty();
    }

    /**
     * Returns an immutable snapshot of the set values.
     *
     * @return set values
     */
    public Set<T> toSet() {
        return Set.copyOf(values);
    }
}
