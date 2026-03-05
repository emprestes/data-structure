package emprestes.ds.domain.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DynamicProgrammingTest {

    private final DynamicProgramming algorithm = new DynamicProgramming();

    @Test
    void shouldCalculateMemoizedFibonacci() {
        assertEquals(0, algorithm.fibonacciMemo(0));
        assertEquals(1, algorithm.fibonacciMemo(1));
        assertEquals(55, algorithm.fibonacciMemo(10));
    }

    @Test
    void shouldCalculateClimbingStairsWays() {
        assertEquals(1, algorithm.climbStairs(0));
        assertEquals(1, algorithm.climbStairs(1));
        assertEquals(8, algorithm.climbStairs(5));
    }

    @Test
    void shouldThrowOnNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> algorithm.fibonacciMemo(-1));
        assertThrows(IllegalArgumentException.class, () -> algorithm.climbStairs(-1));
    }
}
