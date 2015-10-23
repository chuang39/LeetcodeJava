package tree.java.leetcode;

import others.java.leetcode.Conversion.IntegerToEnglishWords;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        return 0;
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<Integer> stackPre = new Stack<Integer>();
        Stack<Integer> stackSuc = new Stack<Integer>();

        inorder(root, target, false, stackPre);
        inorder(root, target, true, stackSuc);

        while (k-- > 0) {
            if (stackPre.isEmpty()) {
                res.add(stackSuc.pop());
            } else if (stackSuc.isEmpty()) {
                res.add(stackPre.pop());
            } else if (Math.abs(stackPre.peek()-target) <= Math.abs(stackSuc.peek()-target)) {
                res.add(stackPre.pop());
            } else {
                res.add(stackSuc.pop());
            }
        }
        return res;
    }

    void inorder(TreeNode root, double target, boolean largerTarget, Stack<Integer> stack) {
        if (root == null) return;

        // for inorder traversal
        // for target's left tree, push left child first since right is closer to target
        // for target's right tree, push right child first since left is closer to target
        inorder(largerTarget ? root.right : root.left, target, largerTarget, stack);

        if ((largerTarget && root.val <= target) || (!largerTarget && root.val > target)) {
            return;
        }
        stack.push(root.val);

        inorder(largerTarget ? root.left : root.right, target, largerTarget, stack);
    }

}
