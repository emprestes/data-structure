package emprestes.ds.domain.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Dynamic programming examples using memoization and tabulation styles.
 */
public class DynamicProgramming {

    /**
     * Computes the nth Fibonacci number using top-down memoization.
     *
     * @param n sequence index
     * @return Fibonacci value for n
     */
    public long fibonacciMemo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        return fibonacciMemo(n, new HashMap<>());
    }

    /**
     * Internal memoized Fibonacci implementation.
     *
     * @param n sequence index
     * @param cache computed values cache
     * @return Fibonacci value for n
     */
    private long fibonacciMemo(int n, Map<Integer, Long> cache) {
        if (n <= 1) {
            return n;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long value = fibonacciMemo(n - 1, cache) + fibonacciMemo(n - 2, cache);
        cache.put(n, value);
        return value;
    }

    /**
     * Computes how many distinct ways there are to climb n steps,
     * moving one or two steps at a time.
     *
     * @param n number of steps
     * @return number of valid step combinations
     */
    public int climbStairs(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n <= 1) {
            return 1;
        }

        int prev2 = 1;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
