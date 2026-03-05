package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.naturalOrder;

/**
 * Stable merge sort implementation.
 */
public class MergeSort implements ISort {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer[] sort(Integer... integers) {
        return mergeSort(integers, naturalOrder());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character[] sort(Character... characters) {
        return mergeSort(characters, naturalOrder());
    }

    /**
     * Sorts an array using merge sort.
     *
     * @param values values to sort
     * @param comparator ordering strategy
     * @param <T> value type
     * @return sorted array reference
     */
    private <T> T[] mergeSort(T[] values, Comparator<T> comparator) {
        if (values == null || values.length < 2) {
            return values;
        }

        T[] temp = Arrays.copyOf(values, values.length);
        mergeSort(values, temp, 0, values.length - 1, comparator);
        return values;
    }

    /**
     * Recursively sorts the range [left, right].
     *
     * @param values source array
     * @param temp temp buffer
     * @param left left bound
     * @param right right bound
     * @param comparator ordering strategy
     * @param <T> value type
     */
    private <T> void mergeSort(T[] values, T[] temp, int left, int right, Comparator<T> comparator) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid, comparator);
        mergeSort(values, temp, mid + 1, right, comparator);
        merge(values, temp, left, mid, right, comparator);
    }

    /**
     * Merges two sorted partitions [left, mid] and [mid+1, right].
     *
     * @param values source array
     * @param temp temp buffer
     * @param left left bound
     * @param mid middle index
     * @param right right bound
     * @param comparator ordering strategy
     * @param <T> value type
     */
    private <T> void merge(T[] values, T[] temp, int left, int mid, int right, Comparator<T> comparator) {
        var length = right - left + 1;
        System.arraycopy(values, left, temp, left, length);

        int i = left, k = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (comparator.compare(temp[i], temp[j]) <= 0) {
                values[k++] = temp[i++];
            } else {
                values[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            values[k++] = temp[i++];
        }
        // remaining right half already in place
    }
}
