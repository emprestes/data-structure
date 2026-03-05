package emprestes.ds.domain.algorithm;

/**
 * Iterative binary search for sorted integer arrays.
 */
public class BinarySearch {

    /**
     * Returns the index of the target value in a sorted array.
     *
     * @param sortedValues sorted source array
     * @param target target value to find
     * @return index of target, or {@code -1} when not found
     */
    public int indexOf(int[] sortedValues, int target) {
        if (sortedValues == null || sortedValues.length == 0) {
            return -1;
        }

        int left = 0;
        int right = sortedValues.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int value = sortedValues[middle];

            if (value == target) {
                return middle;
            }
            if (value < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }
}
