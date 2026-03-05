package emprestes.ds.domain.algorithm;

import java.util.Objects;

/**
 * Linear search over arrays.
 */
public class LinearSearch {

    /**
     * Returns the index of the first occurrence of target in the array.
     *
     * @param values source array
     * @param target value to find
     * @param <T> value type
     * @return index of first occurrence, or {@code -1} when absent
     */
    public <T> int indexOf(T[] values, T target) {
        if (values == null || values.length == 0) {
            return -1;
        }

        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], target)) {
                return i;
            }
        }
        return -1;
    }
}
