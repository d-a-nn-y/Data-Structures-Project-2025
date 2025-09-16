package recursion;

public class McCarthy91 {
    public static int M(int n) {
        if (n > 100) {
            return n - 10;
        }
        return M(M(n + 11));
    }

    public static void main(String[] args) {
        int n = 87;
        System.out.println("M(" + n + ") = " + M(n));
    }
}
