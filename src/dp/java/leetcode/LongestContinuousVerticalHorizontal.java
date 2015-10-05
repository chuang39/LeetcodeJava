package dp.java.leetcode;

import java.util.ArrayList;
import java.util.List;

class Node {
    int horizontal;
    int vertical;

    public Node(int x, int y) {
        horizontal = x;
        vertical = y;
    }
}

public class LongestContinuousVerticalHorizontal {
    int findLongest(int[][] nums) {
        int leni = nums.length;
        int lenj = nums[0].length;

        int res = 0;

        List<List<Node>> dp = new ArrayList<List<Node>>();

        for (int i = 0; i < leni; i++) {
            dp.add(new ArrayList<Node>());

            for (int j = 0; j < lenj; j++) {
                dp.get(i).add(new Node(0, 0));

                if (j == 0 || nums[i][j] != nums[i][j-1]) {
                    dp.get(i).get(j).horizontal = 0;
                } else {
                    dp.get(i).get(j).horizontal = dp.get(i).get(j-1).horizontal+1;
                }
                if (i == 0 || nums[i][j] != nums[i-1][j]) {
                    dp.get(i).get(j).vertical = 0;
                } else {
                    dp.get(i).get(j).vertical = dp.get(i-1).get(j).vertical+1;
                }
            }
        }

        for (int i = leni-1; i >=0; i--) {
            for (int j = lenj-1; j >=0; j--) {
                int temp = dp.get(i).get(j).horizontal+dp.get(i).get(j).vertical;

                if (j == lenj-1 || nums[i][j] != nums[i][j+1]) {
                    dp.get(i).get(j).horizontal = 0;
                } else {
                    dp.get(i).get(j).horizontal = dp.get(i).get(j+1).horizontal+1;
                }
                if (i == leni-1 || nums[i][j] != nums[i+1][j]) {
                    dp.get(i).get(j).vertical = 0;
                } else {
                    dp.get(i).get(j).vertical = dp.get(i+1).get(j).vertical+1;
                }

                int sum = 1+temp + dp.get(i).get(j).horizontal+dp.get(i).get(j).vertical;
                if (sum > res)
                    res = sum;
            }
        }
        return res;
    }

    public static void main(String [] args) {
        LongestContinuousVerticalHorizontal s = new LongestContinuousVerticalHorizontal();
        int[][] nums = {{1,1,1,1}, {2,1,3,1}, {3,3,3,2}};
        System.out.println(s.findLongest(nums));
    }
}
