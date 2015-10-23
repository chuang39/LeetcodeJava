package others.java.leetcode;

import java.util.Random;

public class RandomShuffle {

    // Fisher-Yates shuffle
    void shuffle(int[] nums) {
        Random rnd = new Random();
        // (1) 1/n
        // (2) ((n-1)/n) * (1/n)
        // (3) ((n-1)/n) * (n-2)/(n-1) * (1)/(n-1)
        // ...
        for (int i = nums.length-1; i > 0; i--) {
            int index = rnd.nextInt(i+1);
            swap(nums, index, i);
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        RandomShuffle s = new RandomShuffle();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        s.shuffle(nums);
        for (Integer i: nums) {
            System.out.print(i+" ");
        }
    }
}
