package dp.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0] = true;

        int lens = s.length();
        int lenp = p.length();
        for (int i = 1; i <= lenp; i++) {
            for (int j = 0; j <= lens; j++) {
                if (j > 0 && (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '.') && dp[i-1][j-1]) {
                    dp[i][j] = true;
                } else if (p.charAt(i-1) == '*' &&
                        (dp[i-2][j] || dp[i-1][j] || (j > 0 && dp[i][j-1] && (s.charAt(j-1) == p.charAt(i-2) || p.charAt(i-2) == '.')))
                        ) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[lenp][lens];
    }

    public static void main(String [] args) {
        RegularExpressionMatching s = new RegularExpressionMatching();
        boolean res = s.isMatch("aa", "a");
        assert res == true;
    }
}
