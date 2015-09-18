package dp.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSubarray3 {
    public int maxSubArray(ArrayList<Integer> nums, int k) {
        /*
            dp[i][j] means the maximum sum we can get by selecting j subarrays from the first i elements.

            dp[i][j] = max{dp[p][j-1] + maxSubarray(p+1, i)}    p=j-1 to i-1
         */
        int len = nums.size();
        int[][] dp = new int[len+1][k+1];

        return 0;
    }

    public static void main(String [] args) {
        //MaximumSubarray3 s = new MaximumSubarray3();
        //s.maxSubArray(new ArrayList<Integer>(Arrays.asList(2, -1, 3, 1, -6, 4, -2, 1)), 2);
    }
}
