package recursion;

public class Tribonacci {
    public static int tribonacci(int n) {
        if (n <= 1) {
            return 0;
        }
        else if (n == 2) {
            return 1;
        }
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    public static void main(String[] args) {
        int n = 8; // ninth index value is 44, 9th value is 24
        long startTime = System.currentTimeMillis();
        int result = tribonacci(n);
        long endTime = System.currentTimeMillis();

        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
