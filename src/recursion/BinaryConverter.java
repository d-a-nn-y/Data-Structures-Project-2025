package recursion;

public class BinaryConverter {
    public static void foo(int x) {
        if (x == 0) {
            return;  // Base case: stop when x is 0
        }
        foo(x / 2);  // Recursive call with x divided by 2
        System.out.print(x % 2);  // Print remainder (binary digit)
    }

    public static void main(String[] args) {
        int number = 2468;
        System.out.print("Binary representation of " + number + ": ");
        foo(number);
        System.out.println(); // Move to a new line after printing binary number
    }
}
