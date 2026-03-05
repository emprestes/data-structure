package emprestes.ds.domain.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    private final BinarySearch search = new BinarySearch();

    @Test
    void shouldReturnIndexWhenFound() {
        assertEquals(3, search.indexOf(new int[] { 1, 3, 7, 9, 14 }, 9));
    }

    @Test
    void shouldReturnMinusOneWhenNotFoundOrInvalidInput() {
        assertEquals(-1, search.indexOf(new int[] { 1, 3, 7, 9, 14 }, 10));
        assertEquals(-1, search.indexOf(new int[] {}, 10));
        assertEquals(-1, search.indexOf(null, 10));
    }
}
