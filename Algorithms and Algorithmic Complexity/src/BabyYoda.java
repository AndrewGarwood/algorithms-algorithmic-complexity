import java.util.Arrays;

/* -------- NOTE TO READER: --------

   My work is incomplete. I want to solve this problem later. 


 */

public class BabyYoda {
    /*
     * Finds the optimal amount of eggs Baby Yoda can collect when travelling from
     * (c-1, r-1) (lower right) to (0, 0) (upper left).
     * 
     * @param rocks If rocks[i][j] is true, then a rock (obstacle) is present there.
     * Otherwise false.
     * 
     * @param eggs If eggs[i][j] is true, then Baby Yoda can collect one egg there.
     * Otherwise if there is no egg to collect, false.
     * 
     * @param force Number of times that Baby Yoda can break a rock using the Force.
     * 
     * @return Maximum amount of eggs that can be collected. If Baby Yoda cannot get to
     * the origin (too many rocks, perhaps), return -1.
     */
    public static int maxEggs(boolean[][] rocks, boolean[][] eggs, int force) {
        // TODO): Finish this method.

        // Instantiate 3D array arr[i][j][k]
        // such that arr[i] = possible points if move left
        //           arr[j] = possible points if move above
        //           arr[k] = represents uses of force left,
        //                    therefore dictates how many points obtainable for i and j

        // let's do row x col => m x n
        int m = rocks.length;
        int n = rocks[0].length;
        int F = force;

        int[][][] possibleEggs = new int[m + 1][n + 1][F + 1];
        // System.out.println(Arrays.deepToString(possibleEggs));
        // [F + 1] to account for 0 and force [1, . , F]
        // [m + 1] and [n + 1] to make padding column and row with values 10000-

//        boolean[][] paddedRocks = new boolean[m + 1][n + 1];
//
//        for (int i = 0; i < m + 1; i++) {
//            paddedRocks[i][0] = false;
//        }
//        for (int i = 0; i < n + 1; i++) {
//            paddedRocks[0][i] = false;
//        }
//
//        boolean[][] paddedEggs = new boolean[m + 1][n + 1];

        // populate 3D array
        // -- Fill out Padding rows
        for (int col = 0; col < m + 1; col++) { // padding row of negative values
            for(int f = 0; f < F + 1; f++)
            possibleEggs[0][col][f] = -10000;
        }
        // System.out.println(Arrays.deepToString(possibleEggs));

        for (int row = 0; row < n + 1; row++) { // padding column of negative values
            for(int f = 0; f < F + 1; f++)
                possibleEggs[row][0][f] = -10000;
        }

       //  System.out.println(Arrays.deepToString(possibleEggs));

        // PADDING ROWS MIGHT BE EXTREMELY UNNECESSARY

        // print debug to check padding is correct
//        System.out.println("Padding Row; row = 0: " + "\n");
//        System.out.println(Arrays.deepToString(possibleEggs));
//        for (int c = 0; c < n + 1; c++) {
//            for (int f = 0; f < F + 1; f++) {
//                System.out.println(possibleEggs[c][f]);
//            }
//            System.out.println();
//        }


        // start from bottom right w/ movements left and above => populate starting from top left
        // let's populate each index[i][j] for each force value [0 -> force]
        // Consider Cases we will face:
        // 1. at origin           (1, 1) => can escape if no rock
        // 2. at left-most column (r, 1) => can only move up
        // 3. at top-most row     (1, c) => can only move left
        // 4. at a general case   (r, c) => consider moving left (r, c - 1) or up (r - 1, c)
        // int f = F;

        for (int r = 1; r < m + 1; r++) {
            for (int c = 1; c < n + 1; c++) {
                for (int f = 0; f <= F; f++) {

                    // ----------------------------------------------------------------------------------------
                    // Case 1: at origin 1, 1) => can escape if no rock
                    if (r == 1 && c == 1 && f>=0) {
                        possibleEggs[1][1][f] = 0;
                    } else {

                        // compare number of eggs obtainable by either moving left or right
                        int upVal;
                        int leftVal;
                        int currLocationVal = possibleEggs[r][c][f];


                        // check moving up first
                        // if rock above
                        if (r > 1 && rocks[r - 2][c - 1]) {
                            // if have force remaining
                            if (f > 0) {
                                upVal = currLocationVal + possibleEggs[r - 1][c][f - 1]; //  + (eggs[r - 2][c - 1] ? 1 : 0);
                                // use force => decrement Force
                            } else { // else no force remaining, moving up is -inf b/c rock
                                upVal = -10000;
                            }
                        } else if (r <= 1) { // r - 2 is out of bounds
                            upVal = -10000;
                        } else { // no rock above
                            upVal = currLocationVal + possibleEggs[r - 1][c][f]
                                    + (eggs[r - 2][c - 1] ? 1 : 0);
                        }

                        // check moving left next
                        // if rock to left
                        if (c > 1 && rocks[r - 1][c - 2]) {
                            //if have force remaining
                            if (f > 0) {
                                leftVal = currLocationVal + possibleEggs[r][c - 1][f - 1];
                                // use force => decrement Force
                            } else { // else no force remaining, moving left is -inf
                                leftVal = -10000;
                            }
                        } else if (c <= 1) { // c - 2 is out of bounds
                            leftVal = -10000;
                        } else { // no rock to left
                            leftVal = currLocationVal + possibleEggs[r][c - 1][f]
                                    + (eggs[r - 1][c - 2] ? 1 : 0);
                        }

                        // if rock at both up and left
//                        if (rocks[r - 2][c - 1] && rocks[r - 1][c - 2]) {
//                            if (possibleEggs[r][c][f] <= 0) {
//
//                            }


                        // take max to get val for current index
                        possibleEggs[r][c][f] = Math.max(upVal, leftVal);
                    }

                }
            }
        }
        for (int row = 0; row < m + 1; row++) {
            System.out.println(Arrays.deepToString(possibleEggs[row]));
            System.out.println();
        }

        int result = possibleEggs[m][n][force];
        if (result < 0) {
            return -1;
        }
        return result;
    }

    private static boolean OOB(int row, int col) {
        return (row < 0 || col < 0);
    }

    public static void main(String[] args) {
        // Renaming true/false so they're the same length :)
        final boolean F = false;
        final boolean T = true;

        // Consider writing your own test cases to debug!
        // [Example 1]
        boolean[][] rocks1 = {
                { F, F, F },
                { F, T, T },
                { F, T, F // <- baby yoda starts here
        }};
        boolean[][] eggs1 = {
            { T, T, F },
            { F, F, F },
            { F, F, F },
        };
        int force1 = 1;
        // Baby Yoda can go up by two (breaking a rock at rocks[1][2])
        // Then, go left by two to the origin (collecting eggs at eggs[0][0] and [0][1]).
        int expected1 = 2;
        System.out.println(String.format("1: Expected %d, got %d", expected1, maxEggs(rocks1, eggs1, force1)));

        // [Example 2]
        boolean[][] rocks2 = {
            { T, T, T },
            { T, T, T },
            { T, T, F },
        };
        boolean[][] eggs2 = {
            { T, T, T },
            { T, T, F },
            { T, T, F },
        };
        int force2 = 1;
        // Baby Yoda tragically finds himself surrounded in too many rocks, so he is unable to get to the origin.
        // We expect -1, despite many eggs available to be collected.
        int expected2 = -1;
        System.out.println(String.format("2: Expected %d, got %d", expected2, maxEggs(rocks2, eggs2, force2)));

    }
}