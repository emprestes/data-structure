package emprestes.ds.domain.algorithm;

/**
 * Simple examples based on divide-and-conquer decomposition.
 */
public class DivideAndConquer {

    /**
     * Sums all values in the input array by recursively splitting the range.
     *
     * @param values input values
     * @return sum of all elements, or {@code 0} for null/empty input
     */
    public int sum(int[] values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        return sum(values, 0, values.length - 1);
    }

    /**
     * Recursively sums a sub-range of the array.
     *
     * @param values source array
     * @param left left bound (inclusive)
     * @param right right bound (inclusive)
     * @return partial sum for the specified range
     */
    private int sum(int[] values, int left, int right) {
        if (left == right) {
            return values[left];
        }

        int middle = left + (right - left) / 2;
        return sum(values, left, middle) + sum(values, middle + 1, right);
    }
}
