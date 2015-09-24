package array.java;

public class FindMinimumInRotatedSortedArray {
    // Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (l == r)
                return nums[l];
            // (1) if nums[mid] < nums[r], since it's ordered, we search left part
            // but the mid can be minimum which should be included in left part
            // (2) else: search right part, mid cannot be minimum
            if (nums[mid] < nums[r])
                r = mid;
            else
                l = mid+1;
        }
        return nums[l];
    }


    // Find Minimum in Rotated Sorted Array II
    // case1: 3, 3, 1, 3
    // case2: both mid is 3, end=start=3
    // [3, 1, 2, 3, 3, 3, 3]
    // [3, 3, 3, 3, 1, 2, 3]
    public int findMin2(int[] nums) {
        int l = 0, r = nums.length-1;
        int mid = 0;
        while (l < r) {
            mid = (l + r) / 2;
            if (nums[mid] < nums[r])
                r = mid;
            else if (nums[mid] > nums[r])
                l = mid+1;
            else
                r--;
        }
        return nums[l];
    }

    public static void main(String [] args) {
        FindMinimumInRotatedSortedArray s = new FindMinimumInRotatedSortedArray();
        int[] nums = {3, 1};
        s.findMin2(nums);
    }
}
