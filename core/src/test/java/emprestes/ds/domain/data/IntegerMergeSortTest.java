package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntegerMergeSortTest {

    private final ISort engine = new MergeSort();

    @Test
    void testNullInputReturnsNull() {
        assertNull(engine.sort((Integer[]) null));
    }

    @Test
    void testEmptyArrayStaysEmpty() {
        var values = new Integer[] {};
        var result = engine.sort(values);
        assertArrayEquals(new Integer[] {}, result);
    }

    @Test
    void testSingleElementUnchanged() {
        var values = new Integer[] { 42 };
        var result = engine.sort(values);
        assertArrayEquals(new Integer[] { 42 }, result);
    }

    @Test
    void testIntegerRandomSort() {
        var values = new Integer[] { 5, 8, 9, 6, 1, 4, 2, 7, 3 };
        var expected = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        var result = engine.sort(values);

        assertArrayEquals(expected, result);
    }

    @Test
    void testIntegerInvertedSort() {
        var values = new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        var expected = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        var result = engine.sort(values);

        assertArrayEquals(expected, result);
    }

    @Test
    void testIntegerWithDuplicates() {
        var values = new Integer[] { 4, 2, 4, 3, 2, 1, 3, 1 };
        var expected = new Integer[] { 1, 1, 2, 2, 3, 3, 4, 4 };

        var result = engine.sort(values);

        assertArrayEquals(expected, result);
    }
}
