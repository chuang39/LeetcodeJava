package dfsbfs.java.leetcode;

import javax.swing.*;
import java.util.*;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by khuang on 10/9/15.
 */

/*
    Question:
    A coding map A->1, B->2, ..., Z->26
    Given a number, find all possible combinations. E.g. 32: {"CB"}, 26: {"Z", "BF"}
 */
public class EncodingCombination {
    List<String> findCombinations(int num) {
        List<String> res = new ArrayList<String>();

        int i = 1;
        Map<String, Character> map = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++, i++) {
            map.put(Integer.toString(i), c);
        }
        dfs(map, String.valueOf(num), 0, new StringBuilder(), res);

        return res;
    }

    void dfs(Map<String, Character> map, String num, int pos, StringBuilder sb, List<String> res) {
        if (pos >= num.length()) {
            res.add(sb.toString());
            return;
        }

        String one = num.substring(pos, pos+1);
        if (map.containsKey(one)) {
            sb.append(map.get(one));
            dfs(map, num, pos+1, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }
        if (pos < num.length()-1) {
            String two = num.substring(pos, pos+2);
            if (map.containsKey(two)) {
                sb.append(map.get(two));
                dfs(map, num, pos + 2, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

    @Test
    public void test() {
        EncodingCombination s = new EncodingCombination();
        List<String> res = s.findCombinations(32);
        List<String> expected = new ArrayList<String>();
        expected.add("CB");
        Assert.assertThat(res, is(expected));
    }

    @Test
    public void test2() {
        EncodingCombination s = new EncodingCombination();
        List<String> res = s.findCombinations(26);
        List<String> expected = new ArrayList<String>();
        expected.add("Z");
        expected.add("BF");
        Assert.assertThat(res, is(expected));
    }
}
