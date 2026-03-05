package emprestes.ds.domain.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary min-heap implementation backed by a dynamic array.
 *
 * @param <T> comparable value type
 */
public class MinHeap<T extends Comparable<T>> {

    private final List<T> heap = new ArrayList<>();

    /**
     * Inserts a value and restores the heap property.
     *
     * @param value value to insert
     * @return current heap instance
     */
    public MinHeap<T> insert(T value) {
        if (value == null) {
            return this;
        }

        heap.add(value);
        siftUp(heap.size() - 1);
        return this;
    }

    /**
     * Returns the smallest value without removing it.
     *
     * @return heap root, or {@code null} when empty
     */
    public T peek() {
        return heap.isEmpty() ? null : heap.getFirst();
    }

    /**
     * Removes and returns the smallest value.
     *
     * @return removed root, or {@code null} when empty
     */
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }

        var first = heap.getFirst();
        var last = heap.removeLast();

        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }

        return first;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return heap size
     */
    public int size() {
        return heap.size();
    }

    /**
     * Indicates whether the heap has no elements.
     *
     * @return {@code true} when empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns an immutable snapshot of the internal heap array.
     *
     * @return heap contents
     */
    public List<T> toList() {
        return List.copyOf(heap);
    }

    /**
     * Moves a value up until heap order is satisfied.
     *
     * @param index inserted value index
     */
    private void siftUp(int index) {
        int current = index;
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (heap.get(current).compareTo(heap.get(parent)) >= 0) {
                break;
            }
            swap(current, parent);
            current = parent;
        }
    }

    /**
     * Moves a value down until heap order is satisfied.
     *
     * @param index root index of the subtree to fix
     */
    private void siftDown(int index) {
        int current = index;
        int size = heap.size();

        while (true) {
            int left = (current * 2) + 1;
            int right = left + 1;
            int smallest = current;

            if (left < size && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < size && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest == current) {
                break;
            }

            swap(current, smallest);
            current = smallest;
        }
    }

    /**
     * Swaps two positions inside the heap array.
     *
     * @param a first index
     * @param b second index
     */
    private void swap(int a, int b) {
        var tmp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, tmp);
    }
}
