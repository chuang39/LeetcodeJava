package system.java.leetcode.iterator;

import java.util.*;

class ZigzagIterator1 {
    List<Iterator<Integer>> its = new ArrayList<Iterator<Integer>>();
    int idx;

    public ZigzagIterator1(List<Integer> v1, List<Integer> v2) {
        its.add(v1.iterator());
        its.add(v2.iterator());
        for (int i = 0; i < its.size(); i++) {
            if (its.get(i).hasNext()) {
                idx = i;
                break;
            }
        }
    }

    public int next() {
        Integer res = its.get(idx).next();
        int last = idx;
        do {
            idx++;
            if (idx >= its.size())
                idx = 0;
        } while (!its.get(idx).hasNext() && idx != last);
        return res;
    }

    public boolean hasNext() {
        return its.get(idx).hasNext();
    }
}

class ZigzagIterator2 {
    List<Iterator<Integer>> its = new ArrayList<Iterator<Integer>>();
    List<Stack<Iterator<Integer>>> stacks;
    int curStack = 0;

    public ZigzagIterator2(List<Integer> v1, List<Integer> v2) {
        stacks = new ArrayList<Stack<Iterator<Integer>>>();
        stacks.add(new Stack<Iterator<Integer>>());
        stacks.add(new Stack<Iterator<Integer>>());
        its.add(v1.iterator());
        its.add(v2.iterator());
        for (int i = its.size()-1; i >= 0; i--) {
            if (its.get(i).hasNext()) {
                stacks.get(curStack).push(its.get(i));
            }
        }
    }

    public ZigzagIterator2(List<List<Integer>> list) {
        for (List<Integer> l : list)
            its.add(l.iterator());
        stacks = new ArrayList<Stack<Iterator<Integer>>>();
        stacks.add(new Stack<Iterator<Integer>>());
        stacks.add(new Stack<Iterator<Integer>>());
        for (int i = its.size()-1; i >= 0; i--) {
            if (its.get(i).hasNext()) {
                stacks.get(curStack).push(its.get(i));
            }
        }
    }
    public int next() {
        if (stacks.get(curStack).empty())
            throw new RuntimeException();

        Iterator<Integer> stack = stacks.get(curStack).pop();
        Integer nextStack = curStack ^ 1;
        Integer res = stack.next();
        if (stack.hasNext()) {
            stacks.get(nextStack).push(stack);
        }

        // find next curStack
        if (stacks.get(curStack).empty()) {
            if (stacks.get(nextStack).size() > 1) {
               stacks.get(curStack).push(stacks.get(nextStack).pop());
            }
            curStack = nextStack;
        }

        return res;
    }

    public boolean hasNext() {
        return !stacks.get(0).empty() || !stacks.get(1).empty();
    }
}

public class ZigzagIterator {

    public static void main(String [] args) {
        List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        List<Integer> l2 = new ArrayList<Integer>(Arrays.asList(11, 12, 13, 14));
        List<Integer> l3 = new ArrayList<Integer>(Arrays.asList(21, 22));
        ZigzagIterator2 itr = new ZigzagIterator2(new ArrayList<List<Integer>>(Arrays.asList(l1, l2, l3)));
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
