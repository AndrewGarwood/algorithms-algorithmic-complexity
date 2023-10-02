public class Fibonacci {
    /*
     * Returns the nth term in the Fibonacci Sequence, with
     * F(0) = 0, F(1) = 1, F(n) = F(n-1) + F(n-2) for n > 1
     *
     * @param n The term index
     *
     * @return The nth Fibonacci number
     */
    public static int F(int n) {
        // TODO: Remove the following lines and complete the method.
        int prev1 = 0; // = F(n - 1)
        int prev2 = 0; // = F(n - 2)
        int currFib = 0; // = current Fibonacci number

        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else {
            prev1 = 1;
        }

        for (int i = 0; i < n - 1; i++) {
            currFib = prev1 + prev2;
            prev2 = prev1;
            prev1 = currFib;
        }

        return currFib;
    }

    /*
     *  If you want to write your own tests, put them here.
     */
    public static void main(String[] args) {
        System.out.println("n = 10; expect 55; currFib = " + F((10)));
    }
}
