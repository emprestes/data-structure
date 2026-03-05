package emprestes.ds.domain.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursionAlgorithmsTest {

    private final RecursionAlgorithms algorithm = new RecursionAlgorithms();

    @Test
    void shouldCalculateFactorial() {
        assertEquals(1, algorithm.factorial(0));
        assertEquals(120, algorithm.factorial(5));
    }

    @Test
    void shouldCalculateSumToNAndFibonacci() {
        assertEquals(15, algorithm.sumToN(5));
        assertEquals(13, algorithm.fibonacci(7));
    }

    @Test
    void shouldThrowForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> algorithm.factorial(-1));
        assertThrows(IllegalArgumentException.class, () -> algorithm.sumToN(-1));
        assertThrows(IllegalArgumentException.class, () -> algorithm.fibonacci(-1));
    }
}
