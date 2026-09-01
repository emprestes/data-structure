package emprestes.ds.binarysearch.java;

import java.util.List;

/**
 * Binary search over sorted, random-access values using natural ordering.
 *
 * <p>At every step, the algorithm maintains a closed candidate interval
 * {@code [left, right]}. Comparing the target with the middle value either
 * finds the target or proves that one half cannot contain it. The remaining
 * interval is therefore at most half as large after each comparison.</p>
 *
 * <p>The input must be sorted in ascending order and must not contain null
 * elements. A null input list or target is treated as absent and returns
 * {@code -1}. When duplicates exist, any matching index may be returned.</p>
 *
 * <p>Time complexity is O(log n). The iterative variant uses O(1) additional
 * space; the recursive variant uses O(log n) call-stack space.</p>
 */
public final class BinarySearch {
    /** Creates a reusable binary search instance with no mutable state. */
    public BinarySearch() {
    }

    /**
     * Finds {@code target} using an iterative candidate interval.
     *
     * @param sortedValues values sorted in ascending natural order
     * @param target value to find
     * @param <E> comparable value type
     * @return a matching index, or {@code -1} when absent or input is null
     */
    public <E extends Comparable<? super E>> int iterativeIndexOf(
            List<E> sortedValues, E target) {
        if (sortedValues == null || target == null) {
            return -1;
        }

        int left = 0;
        int right = sortedValues.size() - 1;
        while (left <= right) {
            int middle = midpoint(left, right);
            int comparison = sortedValues.get(middle).compareTo(target);
            if (comparison == 0) {
                return middle;
            }
            if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    /**
     * Finds {@code target} by recursively reducing the candidate interval.
     *
     * @param sortedValues values sorted in ascending natural order
     * @param target value to find
     * @param <E> comparable value type
     * @return a matching index, or {@code -1} when absent or input is null
     */
    public <E extends Comparable<? super E>> int recursiveIndexOf(
            List<E> sortedValues, E target) {
        if (sortedValues == null || target == null) {
            return -1;
        }
        return recursiveIndexOf(sortedValues, target, 0, sortedValues.size() - 1);
    }

    private <E extends Comparable<? super E>> int recursiveIndexOf(
            List<E> sortedValues, E target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = midpoint(left, right);
        int comparison = sortedValues.get(middle).compareTo(target);
        if (comparison == 0) {
            return middle;
        }
        if (comparison < 0) {
            return recursiveIndexOf(sortedValues, target, middle + 1, right);
        }
        return recursiveIndexOf(sortedValues, target, left, middle - 1);
    }

    private int midpoint(int left, int right) {
        return left + (right - left) / 2;
    }
}
