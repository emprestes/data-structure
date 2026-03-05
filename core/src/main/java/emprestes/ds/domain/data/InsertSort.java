package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;

import java.util.function.BiPredicate;

/**
 * Insertion sort implementation.
 */
public class InsertSort implements ISort {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer[] sort(Integer... integers) {
        return insertSort(integers, (a, b) -> a > b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character[] sort(Character... characters) {
        return insertSort(characters, (a, b) -> a > b);
    }

    /**
     * Sorts an array using insertion sort and a comparison predicate.
     *
     * @param values values to sort
     * @param predicate comparison rule that indicates when to shift values
     * @param <T> value type
     * @return sorted array reference
     */
    private <T> T[] insertSort(T[] values, BiPredicate<T, T> predicate) {
        for (int i = 1; i < values.length; i++) {
            var current = values[i];
            var j = i - 1;

            while (j >= 0 && predicate.test(values[j], current)) {
                values[j + 1] = values[j];
                j--;
            }

            values[j + 1] = current;
        }

        return values;
    }
}
