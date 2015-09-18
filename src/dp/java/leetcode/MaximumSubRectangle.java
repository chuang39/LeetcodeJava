/*
    Non-Leetcode
    Solution: http://kevinhliftlove.blogspot.com/2014/05/find-max-sum-rectangle-in-2-d-matrix.html?q=rectangle
*/

package dp.java.leetcode;

public class MaximumSubRectangle {
    public int maxSquare(int[][] matrix) {
        // write your code here
        int left = 0, right = 0;
        int res = 0;
        for (left = 0; left < matrix[0].length; left++) {
            int[] sum = new int[matrix.length];
            for (right = left; right < matrix[0].length; right++) {
                for (int i = 0; i < matrix.length; i++) {
                    sum[i] += matrix[i][right];
                }
                int curMax = findMaxSubarray(sum);
                if (curMax > res)
                    res = curMax;
            }
        }
        return res;
    }

    int findMaxSubarray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int num: nums) {
            curSum += num;
            if (curSum > sum)
                sum = curSum;
            if (curSum < 0)
                curSum = 0;
        }
        if (sum >= 0)
            return sum;
        sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > sum)
                sum = nums[i];
        }
        return sum;
    }
}
