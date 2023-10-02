// import java.util.Arrays;
import java.util.*;

public class GeometricSequence {
    /*
     * Finds the longest geometrically increasing (strictly) subsequence of an
     * array.
     * 
     * You may copy code from lis() and modify it. You may also start from scratch, but we think LIS will be useful.
     * 
     * @param nums A list of distinct numbers.
     * 
     * @returns The longest geometrically increasing subsequence itself. If there
     * are multiple, then any will work.
     */
    public static int[] lgis(int[] nums) {
        // TODO: complete this method.
        int n = nums.length;
        int maxLength = 1;

        // Memoize
        Map<Integer, Stack<Integer>> indexToLGIS = new HashMap<>();
        // can get to each elements LGIS of the given array

        for (int i = 0; i < n; i++) {
            Stack<Integer> lgisStack = new Stack<Integer>(); // for each index of nums, put stack initially w/ nums[i]
            lgisStack.push(nums[i]);
            indexToLGIS.put(i, lgisStack);
        }

        // Note: LGIS of index 0 will be one because there are no values before it
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {

                // for each index i in nums, see if previous elements 0 -> j are (maybe i mix up i and j
                // elements of an increasing geometric subsequence with nums[i] being the max/last value

                Stack<Integer> currSequence = indexToLGIS.get(i);

                // will now kinda take of the lid and see what's on top/ beneath the lid where lid is maybe nums[i]
                int lid = currSequence.pop();
                if (currSequence.size() + 1 > 1) {

                    int penultimate = currSequence.peek(); // value just beneath lid

                    if (nums[j] < penultimate) { // there is a better value for penultimate, automatically valid
                        currSequence.pop();
                        currSequence.push(nums[j]);


                    } else if (nums[j] > 3 * penultimate && nums[j] * 3 < lid) { // valid element of sequence

                        currSequence.push(nums[j]); // sequence element that fits between penultimate and lid

                    }
                } else {
                    if (3 * nums[j] < lid) {
                        // System.out.println("first value of nums to be seen if geo seq: " + nums[i]);
                        currSequence.push(nums[j]);
                    }
                }
                currSequence.push(lid); // put the lid back on

            }
        }

        // get (a) geo sequence with highest length
        int indexOfMax = 0;
        for (int i = 0; i < n; i ++) {
            Stack<Integer> currSequence = indexToLGIS.get(i);

            if (currSequence.size() > maxLength) {
                maxLength = currSequence.size();
                indexOfMax = i;
            }
        }

        Stack<Integer> longestLGIS = indexToLGIS.get(indexOfMax);
        int[] result = new int[longestLGIS.size()];

        for (int i = longestLGIS.size() - 1; i >= 0; i--) {
            result[i] = longestLGIS.pop();
        }

        return result;
    }

    /*
     * Reference: longest increasing (strictly) subsequence code.
     * 
     * @param nums A list of distinct numbers.
     * 
     * @returns the LENGTH of the longest increasing subsequence.
     */
    public static int lis(int[] nums) {
        int[][] vals = new int[nums.length][nums.length];
        int m = -417;
        for (int j = 0; j < nums.length; j++) {
            vals[0][j] = (nums[0] < nums[j]) ? 1 : 0;
            m = Math.max(m, vals[0][j]);
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] >= nums[j])
                    vals[i][j] = vals[i - 1][j];
                else {
                    vals[i][j] = Math.max(1 + vals[i - 1][i], vals[i - 1][j]);
                }
                m = Math.max(m, vals[i][j]);
            }
        }
        return m;
    }

    public static void main(String[] args) {
        

        // expected: [1, 4]
        run(new int[] { 1, 2, 4 });

        // expected: [1, 34] or [2, 34]
        run(new int[] { 1, 2, 34 });

        // expected: [1, 34] or [2, 34]
        run(new int[] { 100, 1, 2, 34 });

        // add more test cases below!
        run(new int[] {100, 4, 44, 444, 4444, 44444, 444444});

    }

    public static void run(int[] arr) {
        System.out.println(Arrays.toString(lgis(arr)));
    }
}
