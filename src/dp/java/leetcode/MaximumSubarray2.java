package dp.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by khuang on 9/7/15.
 */
public class MaximumSubarray2 {
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        int size = nums.size();
        int[] left = new int[size];
        int[] right = new int[size];
        int sum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            left[i] = max;
        }
        sum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        for(int i = size - 1; i >= 0; i--){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            right[i] = max;
        }
        max = Integer.MIN_VALUE;

        for (int a : left) {
            System.out.print(a+" ");
        }
        System.out.println();
        for (int a : right) {
            System.out.print(a+" ");
        }

        for(int i = 0; i < size - 1; i++){
            max = Math.max(max, left[i] + right[i + 1]);
        }
        return max;
    }

    public static void main(String [] args) {
        MaximumSubarray2 s = new MaximumSubarray2();
        s.maxTwoSubArrays(new ArrayList<Integer>(Arrays.asList(1, 3, -6, 2, -1, 2)));
    }

}
