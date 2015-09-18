/*
    Leetcode: https://leetcode.com/problems/maximal-rectangle/
    Solution: http://kevinhliftlove.blogspot.com/2014/05/max-rectangle.html?q=rectangle
 */

package dp.java.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;


        int[] heights = new int[matrix[0].length];
        Arrays.fill(heights, 0);
        int res =0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }
            res = Math.max(res, findMaxInHist(heights));
        }
        return res;
    }

    public int findMaxInHist(int[] nums) {

        Stack<Integer> stack = new Stack<Integer>();
        int cur = 0;
        int res = 0;
        while (cur < nums.length) {
            if (stack.empty() || nums[stack.peek()] < nums[cur]) {
                stack.push(cur++);
            } else {
                // we know every bar before stack top is higher or equal to it.
                int pre = stack.pop();
                res = Math.max(res, nums[pre] * (stack.empty() ? cur : cur - stack.peek() - 1));  // height * width
            }
        }
        while (!stack.empty()) {
            int pre = stack.pop();
            res = Math.max(res, nums[pre]*(stack.empty() ? cur : cur - stack.peek() - 1)); // cur == nums.length
        }
        return res;
    }
}
