package recursion;

import java.util.HashMap;

public class FibonacciMemoization {
    private static HashMap<Integer, Long> memo = new HashMap<>();

    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        int n = 5800; //5800 largest value
        long startTime = System.currentTimeMillis();
        long result = fibonacci(n);
        long endTime = System.currentTimeMillis();

        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
