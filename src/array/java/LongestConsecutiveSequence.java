/*
Leetcode: https://leetcode.com/problems/longest-consecutive-sequence/
Solution: http://kevinhliftlove.blogspot.com/2013/10/leetcode-longest-consecutive-sequence.html

*/

package array.java;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        Map<Integer, Integer> dict = new HashMap<Integer, Integer>();
        int res = 1;

        for (int a : num) {
            if (dict.containsKey(a))
                continue;

            dict.put(a, 1);
            if (dict.containsKey(a-1)) {
                res = Math.max(res, 1+dict.get(a-1));
                int len = dict.get(a-1);
                dict.put(a-len, len+1);
                dict.put(a, 1+len);
            }
            if (dict.containsKey(a+1)) {
                res = Math.max(res, dict.get(a)+dict.get(a+1));
                int len = dict.get(a+1);
                dict.put(a+len, dict.get(a)+len);
                dict.put(a-dict.get(a)+1, dict.get(a)+len);
            }
        }
        return res;
    }
    public static void main(String [] args) {
        LongestConsecutiveSequence s = new LongestConsecutiveSequence();
        int[] data = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        s.longestConsecutive(data);
    }
}
