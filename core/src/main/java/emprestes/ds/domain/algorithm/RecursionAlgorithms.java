package emprestes.ds.domain.algorithm;

/**
 * Recursive algorithm examples.
 */
public class RecursionAlgorithms {

    /**
     * Computes n! recursively.
     *
     * @param n non-negative integer
     * @return factorial value
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * Returns the sum of all integers from 1 to n.
     *
     * @param n non-negative integer
     * @return arithmetic sum
     */
    public int sumToN(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n == 0) {
            return 0;
        }
        return n + sumToN(n - 1);
    }

    /**
     * Computes the nth Fibonacci number with naive recursion.
     *
     * @param n sequence index
     * @return Fibonacci value
     */
    public long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
