package dp.java.leetcode;

/**
 * Created by khuang on 9/8/15.
 */
public class MaximalSquare {

    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1)
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1])) + 1;
                else
                    dp[i + 1][j + 1] = 0;
                if (res < dp[i + 1][j + 1])
                    res = dp[i + 1][j + 1];
            }
        }
        return res * res;
    }

    public static void main(String [] args) {
        MaximalSquare s = new MaximalSquare();
        int[][] arr = {
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0}
        };
        int res = s.maxSquare(arr);
        System.out.println(res);
    }
}
