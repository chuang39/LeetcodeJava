package dp.java.leetcode;

/**
 * Created by khuang on 10/7/15.
 */
public class Backpack {

    /*
        Given n items with size Ai, an integer m denotes the size of a backpack.
        How full you can fill this backpack?

        dp[i][j] indicates the closest value we can reach to j with first i items.
        dp[0][0] = 0
        dp[i][j] = max(dp[i-1][j], dp[i-1][j-A[i]]+A[i]  // without ith item, with ith item
        Note: for with ith item, we still use last row's value because each item only has one.
     */
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backpack(int m, int[] A) {
        int[] dp = new int[m+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-A[i]]+A[i]);
            }
        }
        return dp[m];
    }

    // Same idea as backpack I, the only difference is at the value calculation.
    public int backPackII(int m, int[] A, int V[]) {
        int[] dp = new int[m+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-A[i]] + V[i]);
            }
        }
        return dp[m];
    }
}
