package others.java.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Airbnb
// 实现一个mini parser, 输入是以下格式的string:”324″ or”[123,456,[788,799,833],[[]],10,[]]”
// 要求输出:324 or [123,456,[788,799,833],[[]],10,[]]

class NestedList {
    private int value;
    private List<NestedList> list;
    private boolean isNumber;

    public NestedList() {
        isNumber = false;
        list = new ArrayList<>();
    }

    public NestedList(int v) {
        value = v;
        isNumber = true;
    }

    public void add(NestedList l) {
        list.add(l);
    }

    public String toString() {
        if (isNumber) {
            return ""+value;
        } else {
            return list.toString();
        }
    }
}

public class ParseNestedList {
    static public NestedList parse(String s) {



        NestedList res = null;
        Stack<NestedList> stack = new Stack<NestedList>();
        int i = 0;
        int left = 1; // record the left boundary
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '[') {
                NestedList newNL = new NestedList();
                // if stack is not empty, we also push
                // it to last List. So stack is like
                // Stack: [1, 2, [new nested list]]->[same new nested list]
                if (!stack.isEmpty())
                    stack.peek().add(newNL);
                stack.push(newNL);
                left = i+1;       // !!
            } else if (c == ',' || c == ']') {
                // for the case empty list "[]"
                if (left != i) {
                    int v = Integer.parseInt(s.substring(left, i));
                    NestedList num = new NestedList(v);
                    stack.peek().add(num);

                }
                left = i+1;

                if (c == ']') res = stack.pop(); // Get resule here!!
            }
            i++;
        }
        return res;
    }
    public static void main(String[] args) {
        NestedList list = ParseNestedList.parse("[123,456,[788,799,833],[[]],10,[]]");
        System.out.println(list);
    }
}
