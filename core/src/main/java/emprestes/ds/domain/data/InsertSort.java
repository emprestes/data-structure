package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class InsertSort implements ISort {

    @Override
    public Integer[] sort(Integer... integers) {
        return insertSort(integers, (a, b) -> a > b);
    }

    @Override
    public Character[] sort(Character... characters) {
        return insertSort(characters, (a, b) -> a > b);
    }

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
