package dfsbfs.java.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c: s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c)+1);
            }
        }

        int oddcount = 0;
        for (Character c : map.keySet()) {
            if ((map.get(c) & 1) != 0) {
                oddcount++;
                if (oddcount > 1)
                    return false;
            }
        }
        return true;
    }

    // Palindrome Permutation II
    public List<String> generatePalindromes(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<String> res = new ArrayList<>();
        int oddcount = 0;
        Character oddchar = null;
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            Character c = entry.getKey();
            Integer n = entry.getValue();
            if ((n & 1) != 0) {
                oddcount++;
                oddchar = c;
                if (oddcount > 1)
                    return res;
            }

            // Be careful "aaa", so we don't put logic below in else part
            for (int i = 0; i < n / 2; i++) {
                sb.append(c);
            }
        }
        // permutation is the same as the problem Permutations II
        permutate(sb, 0, res);

        ArrayList<String> realres = new ArrayList<>();
        for (String ss : res) {
            String reversed = (new StringBuilder(ss)).reverse().toString();
            if (oddcount == 1)
                realres.add(ss+String.valueOf(oddchar)+reversed);
            else
                realres.add(ss+reversed);
        }
        return realres;
    }

    void permutate(StringBuilder sb, int start, List<String> res) {
        if (start >= sb.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < sb.length(); i++) {
            if (isDuplicate(sb, start, i))
                continue;
            swap(sb, start, i);
            permutate(sb, start+1, res);
            swap(sb, start, i);
        }
    }

    void swap(StringBuilder sb, int a, int b) {
        char temp = sb.charAt(a);
        sb.setCharAt(a, sb.charAt(b));
        sb.setCharAt(b, temp);
    }

    boolean isDuplicate(StringBuilder sb, int start, int target) {
        // "aabb" -> a only swap with first b, not 2nd b which is duplicate.
        for (int i = start; i < target; i++) {
            if (sb.charAt(i) == sb.charAt(target))
                return true;
        }
        return false;
    }

    public static void main(String [] args) {
        PalindromePermutation s = new PalindromePermutation();

        List<String> res = s.generatePalindromes("aabb");

        for (String str : res) {
            System.out.println(str);
        }
    }
}
