package dp.java.leetcode;

public class MaximumSumNoTwoElementsAreAdjacent {

    int FindMaxSum(int arr[]) {

        int incl = arr[0];
        int excl = 0;
        int exclNew;
        for (int i = 1; i < arr.length; i++) {
            exclNew = Math.max(incl, excl);
            incl = excl+arr[i];
            excl = exclNew;
        }

        return Math.max(incl, excl);
    }
}
