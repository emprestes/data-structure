package emprestes.ds.domain.algorithm;

import org.junit.jupiter.api.Test;

import static java.util.List.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GreedyAlgorithmsTest {

    private final GreedyAlgorithms algorithm = new GreedyAlgorithms();

    @Test
    void shouldReturnGreedyCoinCombination() {
        var result = algorithm.coinChange(87, new int[] { 50, 25, 10, 5, 1 });

        assertEquals(of(50, 25, 10, 1, 1), result);
    }

    @Test
    void shouldReturnEmptyWhenNoExactComposition() {
        assertEquals(of(), algorithm.coinChange(3, new int[] { 2 }));
    }

    @Test
    void shouldThrowWhenAmountIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> algorithm.coinChange(-1, new int[] { 1 }));
    }
}
