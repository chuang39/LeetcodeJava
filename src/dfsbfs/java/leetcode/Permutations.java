package dfsbfs.java.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khuang on 9/22/15.
 */
public class Permutations {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        permuteUniqueRec(nums, 0, res);
        return res;
    }

    void permuteUniqueRec(int[] nums, int start, List<List<Integer>> res) {
        if (start >= nums.length) {
            ArrayList<Integer> resPath = new ArrayList<Integer>();
            for (int i: nums)
                resPath.add(i);
            res.add(resPath);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (isDuplicate(nums, start, i))
                continue;
            swap(nums, i, start);
            permuteUniqueRec(nums, start+1, res);
            swap(nums, i, start);
        }

    }

    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    boolean isDuplicate(int[] nums, int a, int b) {
        for (int i = a; i < b; i++) {
            if (nums[i] == nums[b])
                return true;
        }
        return false;
    }

    public static void main(String [] args) {
        Permutations s = new Permutations();
        int[] nums = {0,3,3};
        List<List<Integer>> res = s.permuteUnique(nums);
        for (List<Integer> l : res) {
            for (Integer i: l)
                System.out.print(i+" ");
            System.out.println();
        }
    }
}
