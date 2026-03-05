package emprestes.ds.domain.data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Simple resizable array structure.
 *
 * @param <T> stored element type
 */
public class DynamicArray<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] values = new Object[DEFAULT_CAPACITY];
    private int size;

    /**
     * Appends a value at the end of the array.
     *
     * @param value value to append
     * @return current array instance
     */
    public DynamicArray<T> add(T value) {
        if (value == null) {
            return this;
        }
        ensureCapacity(size + 1);
        values[size++] = value;
        return this;
    }

    /**
     * Returns the value at the given index.
     *
     * @param index zero-based index
     * @return stored value
     */
    public T get(int index) {
        validateIndex(index);
        return elementAt(index);
    }

    /**
     * Replaces a value at the given index.
     *
     * @param index zero-based index
     * @param value new value
     * @return current array instance
     */
    public DynamicArray<T> set(int index, T value) {
        validateIndex(index);
        values[index] = value;
        return this;
    }

    /**
     * Removes and returns the value at the given index.
     *
     * @param index zero-based index
     * @return removed value
     */
    public T removeAt(int index) {
        validateIndex(index);
        var removed = elementAt(index);
        int moveCount = size - index - 1;
        if (moveCount > 0) {
            System.arraycopy(values, index + 1, values, index, moveCount);
        }
        values[--size] = null;
        return removed;
    }

    /**
     * Checks whether the array contains the value.
     *
     * @param value value to search
     * @return {@code true} when present
     */
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the current number of elements.
     *
     * @return array size
     */
    public int size() {
        return size;
    }

    /**
     * Indicates whether no values are stored.
     *
     * @return {@code true} when empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current array values as an immutable list.
     *
     * @return list snapshot
     */
    public List<T> toList() {
        var copy = Arrays.copyOf(values, size);
        @SuppressWarnings("unchecked")
        var typed = (T[]) copy;
        return List.of(typed);
    }

    /**
     * Expands internal capacity to fit at least minCapacity elements.
     *
     * @param minCapacity required minimum capacity
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= values.length) {
            return;
        }
        int nextCapacity = values.length * 2;
        while (nextCapacity < minCapacity) {
            nextCapacity = nextCapacity * 2;
        }
        values = Arrays.copyOf(values, nextCapacity);
    }

    /**
     * Returns the typed element at a valid index.
     *
     * @param index validated index
     * @return element value
     */
    @SuppressWarnings("unchecked")
    private T elementAt(int index) {
        return (T) values[index];
    }

    /**
     * Validates index bounds for the current size.
     *
     * @param index index to validate
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
