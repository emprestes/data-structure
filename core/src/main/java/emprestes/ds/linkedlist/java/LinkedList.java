package emprestes.ds.linkedlist.java;

import java.util.List;

/**
 * Defines basic operations for a mutable linked list.
 *
 * @param <T> stored element type
 */
public interface LinkedList<T> extends Iterable<T> {

    /**
     * Inserts a value at the beginning.
     *
     * @param value value to insert
     * @return current list instance
     */
    LinkedList<T> addFirst(T value);

    /**
     * Inserts a value at the end.
     *
     * @param value value to insert
     * @return current list instance
     */
    LinkedList<T> addLast(T value);

    /**
     * Inserts a value at a zero-based position.
     *
     * @param index insertion position from zero through {@link #size()}
     * @param value value to insert
     * @return current list instance
     * @throws IndexOutOfBoundsException when the index is outside the insertion range
     */
    LinkedList<T> add(int index, T value);

    /**
     * Removes and returns the first element.
     *
     * @return removed element, or {@code null} when empty
     */
    T removeFirst();

    /**
     * Removes and returns the last element.
     *
     * @return removed element, or {@code null} when empty
     */
    T removeLast();

    /**
     * Removes and returns the element at a zero-based index.
     *
     * @param index position to remove
     * @return removed value
     * @throws IndexOutOfBoundsException when the index does not identify an element
     */
    T removeAt(int index);

    /**
     * Removes the first occurrence of the given value.
     *
     * @param value value to remove
     * @return {@code true} when removed
     */
    boolean remove(T value);

    /**
     * Checks whether a value exists in the list.
     *
     * @param value value to search
     * @return {@code true} when present
     */
    boolean contains(T value);

    /**
     * Returns the element at a zero-based index.
     *
     * @param index position
     * @return value at index
     */
    T get(int index);

    /**
     * Returns the number of elements stored.
     *
     * @return list size
     */
    int size();

    /**
     * Indicates whether the list is empty.
     *
     * @return {@code true} when empty
     */
    boolean isEmpty();

    /** Removes every node from the list. */
    void clear();

    /**
     * Returns the current list values in order.
     *
     * @return list snapshot
     */
    List<T> toList();
}
