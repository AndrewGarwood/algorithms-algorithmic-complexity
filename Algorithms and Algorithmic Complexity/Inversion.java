import java.util.Arrays;

public class Inversion {
    /*
     * Counts the number of inversions of a given permutation using
     * the Divide and Conquer paradigm.
     * 
     * @param n The length of the permutation.
     * 
     * @param perm A permutation of the elements [0, 1, ..., n-1]. 
     * That is, those elements 0,1,..., n-1 in some order.
     * 
     * @return The number of inversions of perm.
     */
    public static int countInversions(int n, int[] perm) {
        assert perm.length == n;
        // TODO: Remove the following lines and complete this method.

        int start = 0;
        int stop = perm.length - 1;


        int result = inversionCounter(perm, start, stop);
        return result;
    }

    public static int inversionCounter(int[] perm, int start, int stop) {
        int inversions = 0;

        if (start >= stop) {
            return 0;
        }
        int midpoint = (stop - start) / 2 + start;



        inversions = inversions + inversionCounter(perm, start, midpoint); // left halves

        inversions = inversions + inversionCounter(perm, midpoint + 1, stop); // right halves

        inversions = inversions + mergeAndCount(perm, start, stop); // counting


        return inversions;
    }

    public static int mergeAndCount (int[] perm, int start, int stop) {
        int inversions = 0;
        int n = perm.length;
        int[] temp = new int[n];
        int left = start;

        if (start >= stop) {
            return 0;
        }
        int mid = start + (stop - start) / 2;
        int right = mid + 1; //
        int curr = start;

        while(left <= mid && right <= stop) {

            if (perm[left] < perm[right]) {

                temp[curr++] = perm[left++];

            } else {

                temp[curr++] = perm[right++];
                inversions = inversions + (mid - left) + 1;
            }
        }
        while (left <= mid) {

            temp[curr++] = perm[left++];
        }
        while (right <= stop) {

            temp[curr++] = perm[right++];
            inversions = inversions + (mid - left) + 1;


        }
        for(int i = start; i <= stop; i++) { // temp is sorted
            perm[i] = temp[i];
        }


        return inversions;

    }

    /*
     * If you want to write your own tests, put them here.
     */
    public static void main(String[] args) {

        int n = 10;
        int[] testArray = {8, 2, 91, 22, 57, 1, 10, 6, 7, 4};


        System.out.println("inversions: " + countInversions(n, testArray));



    }
}
