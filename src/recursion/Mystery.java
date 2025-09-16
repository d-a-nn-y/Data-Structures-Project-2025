package recursion;

public class Mystery {
    public static int mystery(int n, int a, int d) {
        if (n == 1)
            return a;
        else
            return d + mystery(n - 1, a, d);

    }


    public static void main(String[] args) {
        System.out.println(mystery(2,4,4));

    }
}

