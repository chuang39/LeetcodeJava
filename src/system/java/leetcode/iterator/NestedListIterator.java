package system.java.leetcode.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NestedListIterator implements Iterator<Integer> {
    List<List<Integer>> list;
    int curList;
    int curPos;
    int lastList;
    int lastPos;

    public NestedListIterator(List<List<Integer>> l) {
        list = l;
        // Initialize the pointer to the first valid position.
        curList = 0;
        curPos = 0;
        while (list.get(curList).isEmpty()) {
            curList++;
        }
    }

    @Override
    public boolean hasNext() {
        return list.size() > curList && list.get(curList).size() > curPos;
    }

    @Override
    public Integer next() {
        // 1. get the value return num
        int res = list.get(curList).get(curPos);
        lastList = curList;
        lastPos = curPos;

        // 2. find the next valid element
        curPos++;
        if (curPos >= list.get(curList).size()) {
            curList++;
            curPos = 0;
            while (curList < list.size() && list.get(curList).isEmpty()) {
                curList++;
            }
        }
        return res;
    }

    @Override
    public void remove() {
        // remove is not correct. We should re-calculate the curList and curPos after deletion
        list.get(lastList).remove(lastPos);
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<List<Integer>>();
        //[[],[1,2,3],[],[4,5]]
        input.add(new ArrayList<Integer>());
        input.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        input.add(new ArrayList<Integer>());
        input.add(new ArrayList<Integer>(Arrays.asList(4, 5)));

        NestedListIterator itr = new NestedListIterator(input);
        itr.next();
        itr.next();
        itr.next();
        itr.remove();
        itr.next();
        itr.remove();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
