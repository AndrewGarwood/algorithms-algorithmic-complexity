import java.util.*;
public class VerifyThreeSat {
	/*
	 * Input :
     *
	 * -boolean array X of length n
     *     
	 * -2d boolean array Y with m rows and 3 columns
	 * 
     * -2d int array Z with m rows and 3 columns,
     *  where each element is between 1 and n (inclusive)
	 * 
	 * Output: return true if all constraints are satisfied with the
     * specified variable settings; false otherwise
	 */
    public static boolean verify(boolean[] X, boolean[][] Y, int[][] Z) {

        // verify contents of Y are good
        int n = X.length;
        int m = Y.length;
        for (int i = 0; i < m; i++) { // for each row
            boolean[] currentTrio = Y[i]; // obtain first 3 values to check are consistent
            // int[] zValues = new int[3];
            int numTrue = 0;
            for (int j = 0; j < 3; j++) { // for each element j in row i
                // Z's element at index i, j
                // zValues[j] = Z[i][j];
                if (X[Z[i][j] - 1] == Y[i][j]) {
                    numTrue++;
                }
                if (numTrue > 0) {
                    break;
                }
            }
            if (numTrue == 0) {
                return false;
            }

        }

        return true;
    }

    /*
     * A couple of test cases are provided to help you see what the input and output
     * look like :)
     *
     * You can also modify the provided main method for your own test cases. The main
     * method will not be graded. You'll only be graded on VerifyThreeSat.
     */
    public static void main (String[] args) {
        final boolean T = true;
        final boolean F = false;
        
        // impossible to satisfy        
        boolean[][] Y1 = {{T, T, T},
                          {F, F, F}};

        int[][] Z1 = {{1, 1, 1},
                      {1, 1, 1}};

        System.out.println(verify(new boolean[] {T}, Y1, Z1)); // false
        System.out.println(verify(new boolean[] {F}, Y1, Z1)); // false

        // satisfied if and only if x_2 and x_3 are true
        boolean[][] Y2 = {{T, T, T},
                          {F, F, T},
                          {T, T, T}};

        int[][] Z2 = {{1, 1, 2},
                      {1, 1, 2},
                      {3, 3, 3}};
        
        System.out.println(verify(new boolean[] {F, T, T}, Y2, Z2)); // true
        System.out.println(verify(new boolean[] {T, T, T}, Y2, Z2)); // true
        System.out.println(verify(new boolean[] {T, T, F}, Y2, Z2)); // false
        System.out.println(verify(new boolean[] {T, F, T}, Y2, Z2)); // false
    }
}
