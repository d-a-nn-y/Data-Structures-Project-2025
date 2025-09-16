package recursion;

public class FibonacciRecursive {
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = 42; //biggest value i could get was 42
        long startTime = System.currentTimeMillis();
        int result = fibonacci(n);
        long endTime = System.currentTimeMillis();

        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}


