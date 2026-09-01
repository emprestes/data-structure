package emprestes.ds.binarysearch.java;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTest {
    private final BinarySearch search = new BinarySearch();

    @Test
    void findsValuesAtBeginningMiddleAndEndIteratively() {
        var values = List.of(1, 3, 7, 9, 14);

        assertEquals(0, search.iterativeIndexOf(values, 1));
        assertEquals(2, search.iterativeIndexOf(values, 7));
        assertEquals(4, search.iterativeIndexOf(values, 14));
    }

    @Test
    void findsValuesAtBeginningMiddleAndEndRecursively() {
        var values = List.of("Ada", "Edsger", "Grace", "Linus", "Margaret");

        assertEquals(0, search.recursiveIndexOf(values, "Ada"));
        assertEquals(2, search.recursiveIndexOf(values, "Grace"));
        assertEquals(4, search.recursiveIndexOf(values, "Margaret"));
    }

    @Test
    void returnsMinusOneForAbsentEmptyAndNullInputs() {
        assertEquals(-1, search.iterativeIndexOf(List.of(1, 3, 5), 4));
        assertEquals(-1, search.recursiveIndexOf(List.of(1, 3, 5), 4));
        assertEquals(-1, search.iterativeIndexOf(List.<Integer>of(), 4));
        assertEquals(-1, search.recursiveIndexOf(List.<Integer>of(), 4));
        assertEquals(-1, search.iterativeIndexOf(null, 4));
        assertEquals(-1, search.recursiveIndexOf(null, 4));
        assertEquals(-1, search.iterativeIndexOf(List.of(1), null));
        assertEquals(-1, search.recursiveIndexOf(List.of(1), null));
    }

    @Test
    void returnsAnIndexContainingTheTargetWhenDuplicatesExist() {
        var values = List.of(1, 2, 2, 2, 3);

        int iterativeIndex = search.iterativeIndexOf(values, 2);
        int recursiveIndex = search.recursiveIndexOf(values, 2);

        assertTrue(iterativeIndex >= 1 && iterativeIndex <= 3);
        assertTrue(recursiveIndex >= 1 && recursiveIndex <= 3);
        assertEquals(2, values.get(iterativeIndex));
        assertEquals(2, values.get(recursiveIndex));
    }

    @Test
    void handlesSingleElementAndEvenLengthInputs() {
        assertEquals(0, search.iterativeIndexOf(List.of(42), 42));
        assertEquals(0, search.recursiveIndexOf(List.of(42), 42));
        assertEquals(3, search.iterativeIndexOf(List.of(1, 2, 3, 4), 4));
        assertEquals(3, search.recursiveIndexOf(List.of(1, 2, 3, 4), 4));
    }
}
