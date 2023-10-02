import java.util.Arrays;


/* -------- NOTE TO READER: --------

   My work is incomplete. I want to solve this problem later. 


 */

public class StableMatching {
    /**
     * Generates a stable matching.
     *
     * @param prefHorses Preferences of the horses. prefHorses[i] lists the indices
     * of the riders that the i-th horse prefers, in order of preference.
     *
     * @param prefRiders Preferences of the riders. prefHorses[i] lists the
     * indices of the horses that the i-th rider prefers, in order of preference.
     *
     * @param horseOptimal if true, the generated stable matching should be most
     * optimal for the horses. Otherwise, it should be most optimal for the riders.
     *
     * @return Computed stable matching. It is a 1D array, where arr[i]=j means the
     * i-th horse is matched to the j-th rider.
     **/
    public static int[] findStableMatching(int[][] prefHorses, int[][] prefRiders, boolean horseOptimal) {
        assert prefHorses.length == prefRiders.length;
        // TODO: Remove the following lines and complete this method.
        int[] result = { -1 };
        return result;
    }

    /*
     * A short sanity check is provided to help you see what the input and output
     * look like :)
     *
     * You can also modify the provided main method for your own test cases. This
     * method will not be graded.
     */
    public static void main(String[] args) {
        int[][] prefHorses = { { 0, 1 }, // Preferences of h0
                { 1, 0 }, // Preferences of h1
        };
        int[][] prefRiders = { { 0, 1 }, // Preferences of r0
                { 1, 0 }, // Preferences of r1
        };

        System.out.printf("Horse-optimal: ");
        System.out.println(Arrays.toString(findStableMatching(prefHorses, prefRiders, true)));
        System.out.printf("Rider-optimal: ");
        System.out.println(Arrays.toString(findStableMatching(prefHorses, prefRiders, false)));
    }
}