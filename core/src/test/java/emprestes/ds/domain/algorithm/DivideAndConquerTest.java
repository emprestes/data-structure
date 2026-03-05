package emprestes.ds.domain.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideAndConquerTest {

    private final DivideAndConquer algorithm = new DivideAndConquer();

    @Test
    void shouldSumValues() {
        assertEquals(15, algorithm.sum(new int[] { 1, 2, 3, 4, 5 }));
    }

    @Test
    void shouldReturnZeroForInvalidOrEmptyInput() {
        assertEquals(0, algorithm.sum(new int[] {}));
        assertEquals(0, algorithm.sum(null));
    }
}
