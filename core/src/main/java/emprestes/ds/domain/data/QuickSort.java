package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;

import java.util.Comparator;

import static java.util.Comparator.naturalOrder;

/**
 * In-place quick sort implementation.
 */
public class QuickSort implements ISort {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer[] sort(Integer... integers) {
        return quickSort(integers, naturalOrder());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character[] sort(Character... characters) {
        return quickSort(characters, naturalOrder());
    }

    /**
     * Sorts an array using quick sort.
     *
     * @param values values to sort
     * @param comparator ordering strategy
     * @param <T> value type
     * @return sorted array reference
     */
    private <T> T[] quickSort(T[] values, Comparator<T> comparator) {
        if (values == null || values.length < 2) {
            return values;
        }

        sort(values, 0, values.length - 1, comparator);
        return values;
    }

    /**
     * Recursively sorts the range [left, right].
     *
     * @param values source array
     * @param left left bound
     * @param right right bound
     * @param comparator ordering strategy
     * @param <T> value type
     */
    private <T> void sort(T[] values, int left, int right, Comparator<T> comparator) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(values, left, right, comparator);
        sort(values, left, pivotIndex - 1, comparator);
        sort(values, pivotIndex + 1, right, comparator);
    }

    /**
     * Partitions the range using the left value as pivot.
     *
     * @param values source array
     * @param left left bound
     * @param right right bound
     * @param comparator ordering strategy
     * @param <T> value type
     * @return final pivot index
     */
    private <T> int partition(T[] values, int left, int right, Comparator<T> comparator) {
        T pivot = values[left];
        int lt = left + 1;
        int rt = right;

        while (lt <= rt) {
            while (lt <= right && comparator.compare(values[lt], pivot) <= 0) {
                lt++;
            }

            while (rt >= left && comparator.compare(values[rt], pivot) > 0) {
                rt--;
            }

            if (lt < rt) {
                swap(values, lt, rt);
            }
        }

        if (left != rt) {
            swap(values, left, rt);
        }

        return rt;
    }

    /**
     * Swaps two values inside the array.
     *
     * @param values source array
     * @param i first index
     * @param j second index
     * @param <T> value type
     */
    private <T> void swap(T[] values, int i, int j) {
        var tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
}
