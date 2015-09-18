package array.java;

public class FindMiniDistTwoSortedArray {
    int findMinimumDistanceTwoSortedArray(int[] source, int[] target) {
        int res = Integer.MAX_VALUE;
        int i = 0, j =0;
        while (i < source.length || j < target.length) {
            res = Math.min(res, Math.abs(source[i]-target[j]));

            if (i == source.length-1 && j == target.length-1)
                break;

            if (i == (source.length-1))
                j++;
            else if (j == target.length-1)
                i++;
            else if (Math.abs(source[i]-target[j+1]) < Math.abs(source[i+1]-target[j]))
                j++;
            else
                i++;
        }
        return res;
    }

    public static void main(String [] args) {
        FindMiniDistTwoSortedArray s = new FindMiniDistTwoSortedArray();
        int[] arr1 = {3, 7, 12, 16};
        int[] arr2 = {0, 9, 11};
        System.out.println(s.findMinimumDistanceTwoSortedArray(arr1, arr2));
    }
}
