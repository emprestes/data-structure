package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerInsertSortTest {

    private final ISort engine = new InsertSort();

    @Test
    void testIntegerRandomSort() {
        var integers = new Integer[] { 5, 8, 9, 6, 1, 4, 2, 7, 3 };
        var expected = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        var result = engine.sort(integers);

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testIntegerInvertedSort() {
        var integers = new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        var expected = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        var result = engine.sort(integers);

        Assertions.assertArrayEquals(expected, result);
    }
}
