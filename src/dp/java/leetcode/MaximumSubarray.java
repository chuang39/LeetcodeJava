/*
* Leetcode: https://leetcode.com/problems/maximum-subarray/
* */

package dp.java.leetcode;

import java.util.ArrayList;

public class MaximumSubarray {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(ArrayList<Integer> nums) {
        int sum = -1;
        int curSum = 0;
        for (int num : nums) {
            curSum += num;
            if (curSum > sum) {
                sum = curSum;
            }
            if (curSum < 0) {
                curSum = 0;
            }
        }
        if (sum >= 0)
            return sum;
        sum = nums.get(0);
        for (int num: nums) {
            if (sum < num)
                sum = num;
        }
        return sum;
    }
}
