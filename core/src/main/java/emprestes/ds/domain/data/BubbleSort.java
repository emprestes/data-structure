package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;

import java.util.Comparator;

/**
 * Simple stable bubble sort for demonstration and small inputs.
 */
public class BubbleSort implements ISort {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer[] sort(Integer... integers) {
        return bubbleSort(integers, Comparator.naturalOrder());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character[] sort(Character... characters) {
        return bubbleSort(characters, Comparator.naturalOrder());
    }

    /**
     * Sorts an array using bubble sort with the provided comparator.
     *
     * @param values values to sort
     * @param comparator ordering strategy
     * @param <T> value type
     * @return sorted array reference
     */
    private <T> T[] bubbleSort(T[] values, Comparator<T> comparator) {
        if (values == null || values.length < 2) {
            return values;
        }

        boolean swapped;
        int n = values.length;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(values[i - 1], values[i]) > 0) {
                    var tmp = values[i - 1];
                    values[i - 1] = values[i];
                    values[i] = tmp;
                    swapped = true;
                }
            }
            n--; // last element is in place
        } while (swapped);

        return values;
    }
}
