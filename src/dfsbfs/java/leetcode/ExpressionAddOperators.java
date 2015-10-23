package dfsbfs.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();

        //dfs(num, 0, target, new StringBuilder(), 0, res);
        return res;
    }

    Character[] ops = {'+', '-', '*'};
    void dfs(String num, String path, int target, long diff, int pathVal, List<String> res) {
        if (pathVal == target) {
            res.add(path.toString());
            return;
        }
        String left, right;
        for (int i = 1; i <=num.length(); i++) {
            left = num.substring(0, i);
            right = num.substring(i-1);
            if (left.length() > 1 && left.startsWith("0"))
                return;
            if (path.length() == 0) {
                dfs(right, left, target, Integer.parseInt(left), Integer.parseInt(left), res);
            } else {
                dfs(right, path+"+"+left, target, Long.parseLong(left), Integer.parseInt(left), res);
                dfs(right, path+"-"+left, target, -Long.parseLong(left), Integer.parseInt(left), res);
                dfs(right, path+"*"+left, target, Integer.parseInt(left), Integer.parseInt(left), res);

            }
        }

    }
    public  static void main(String[] args) {
        ExpressionAddOperators s = new ExpressionAddOperators();
        //String num = "123";
        //List<String> res = s.addOperators(num, 6);
    }
}
