package emprestes.ds.domain.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearSearchTest {

    private final LinearSearch search = new LinearSearch();

    @Test
    void shouldReturnIndexWhenFound() {
        assertEquals(2, search.indexOf(new Integer[] { 10, 20, 30, 40 }, 30));
    }

    @Test
    void shouldReturnMinusOneWhenNotFoundOrInvalidInput() {
        assertEquals(-1, search.indexOf(new Integer[] { 1, 2, 3 }, 9));
        assertEquals(-1, search.indexOf(new Integer[] {}, 1));
        assertEquals(-1, search.indexOf(null, 1));
    }
}
