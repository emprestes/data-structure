package emprestes.ds.domain.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Greedy strategy examples.
 */
public class GreedyAlgorithms {

    /**
     * Attempts to compose an amount using the largest available coins first.
     *
     * @param amount target amount
     * @param availableCoins available coin denominations
     * @return chosen coins in selection order, or empty list when exact change is not possible
     */
    public List<Integer> coinChange(int amount, int[] availableCoins) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be >= 0");
        }
        if (amount == 0 || availableCoins == null || availableCoins.length == 0) {
            return List.of();
        }

        var coins = Arrays.stream(availableCoins)
                .filter(value -> value > 0)
                .boxed()
                .sorted((a, b) -> Integer.compare(b, a))
                .toList();

        int remaining = amount;
        var result = new ArrayList<Integer>();
        for (var coin : coins) {
            while (remaining >= coin) {
                remaining -= coin;
                result.add(coin);
            }
        }

        if (remaining != 0) {
            return List.of();
        }
        return result;
    }
}
