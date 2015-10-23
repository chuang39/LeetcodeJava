package dp.java.leetcode;

public class MinimumNumberOfCoins {
    int minCoins(int[] coins, int value) {

        // dp indicates the minimum number of coins to achieve value i
        int[] dp = new int[value+1];
        dp[0] = 0;

        for (int i = 1; i <= value; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // compute min coins required for values from 1->value
        for (int i = 1; i <= value; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    int temp = dp[i-coins[j]];
                    if (temp != Integer.MAX_VALUE && temp+1 < dp[i])
                        dp[i] = temp + 1;
                }
            }
        }

        return dp[value];
    }

    public static void main(String[] args) {
        MinimumNumberOfCoins s = new MinimumNumberOfCoins();
        int[] coins = {9, 6, 5, 1};

        int res = s.minCoins(coins, 11);
        System.out.println(res);
    }
}
