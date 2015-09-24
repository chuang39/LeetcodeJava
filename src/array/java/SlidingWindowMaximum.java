package array.java;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by khuang on 9/21/15.
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // initialization
        ArrayList<Integer> res = new ArrayList<Integer>();
        Deque<Integer> dq = new LinkedList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            // remove elements in end of queue
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            // insert and remove first if needed
            dq.offer(i);
            if (i >= k && i - dq.peekFirst() > k-1) {
                dq.pollFirst();
            }
            // add larget is i >= k-1
            if (i >= k-1)
                res.add(nums[dq.peekFirst()]);
        }

        int[] r = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            r[i] = res.get(i);

        return r;
    }

    public static void main(String [] args) {
        SlidingWindowMaximum s = new SlidingWindowMaximum();
        int[] nums = {7, 2, 4};
        int[] res = s.maxSlidingWindow(nums, 2);
    }
}
