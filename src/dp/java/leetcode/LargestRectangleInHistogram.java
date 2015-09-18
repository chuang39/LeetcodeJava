/*
    Leetcode: https://leetcode.com/problems/largest-rectangle-in-histogram/
    Solution: http://kevinhliftlove.blogspot.com/2014/05/largest-rectangle-in-histogram.html?q=rectangle
 */

package dp.java.leetcode;

import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();

        int cur = 0;
        int res = 0;
        while (cur < height.length) {
            if (stack.empty() || height[cur] >= height[stack.peek()]) {
                stack.push(cur++);
            } else {
                int pre = stack.pop();
                res = Math.max(res, height[pre] * (stack.isEmpty() ? cur : (cur - stack.peek() - 1)));
            }
        }
        while (!stack.empty()) {
            int pre = stack.pop();
            res = Math.max(res, height[pre] * (stack.isEmpty() ? cur : (cur - stack.peek() - 1)));
        }
        return res;
    }
}
