package dfsbfs.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by khuang on 9/24/15.
 */
public class FactorCombinations {
    ArrayList<ArrayList<Integer>> getComb(int x) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (x == 0) return res;
        // assume x > 0

        //res.add(new ArrayList<Integer>(Arrays.asList(1, x)));
        getCombRec(x, 1, new ArrayList<Integer>(), res);

        return res;
    }

    void getCombRec(int val, int prev, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res) {
        if (val < 2) {
            //ArrayList<Integer> newPath = new ArrayList<Integer>(path);
            //res.add(newPath);
            return;
        }

        int len = (int) Math.ceil(Math.sqrt(val));
        for (int i = 2; i <= len; i++) {
            if (val % i == 0) {
                if (i >= prev && val / i >= i) {            // we use  val/i >=i to prevent duplicate like 6 -> 2*3, 3*2, then 3*2 won't be considered.
                    path.add(i);
                    ArrayList<Integer> newPath = new ArrayList<Integer>(path);
                    newPath.add(val/i);
                    res.add(newPath);

                    getCombRec(val/i, i, path, res);
                    path.remove(path.size()-1);
                }
            }
        }
    }

}
